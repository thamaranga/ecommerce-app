package com.hasitha.orderservice.service;

import com.hasitha.orderservice.dto.*;
import com.hasitha.orderservice.event.OrderPlaceEvent;
import com.hasitha.orderservice.exception.InventoryNotEnoughException;
import com.hasitha.orderservice.model.Order;
import com.hasitha.orderservice.model.OrderLineItem;
import com.hasitha.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;

    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) throws InventoryNotEnoughException {
        Order order= new Order();
        OrderResponseDTO orderResponseDTO=new OrderResponseDTO();

        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItemList = orderRequestDTO.getOrderLineItemList().stream().
                    map(orderLineItem -> mapToOrderLineItem(orderLineItem)).collect(Collectors.toList());
        List<InventoryRequestDTO> inventoryRequestDTOList = orderLineItemList.stream().map(orderLineItem->{
            return InventoryRequestDTO.builder().skuCode(orderLineItem.getSkuCode()).quentity(orderLineItem.getQuantity()).build();
        }).collect(Collectors.toList());

        /*
         * Inside bodyToMono  we need to mention retrive data type.
         * Since web client by default asyn , we have to add block()
         * at the end of method for making this call as sync.
         * Finally below method will generate a get request as below.
         * http://localhost:8080/api/inventory?sku-code=iphone?sku-code=samsung"
         *
         *
         * */
        InventoryResponseDTO[] inventoryResponseDTOSArray = webClient.build().post().uri("http://inventory-service/api/inventory/checkInventory").
                body(Mono.just(inventoryRequestDTOList), new ParameterizedTypeReference<List<InventoryRequestDTO>>() {})
                .retrieve().
                bodyToMono(InventoryResponseDTO[].class).block();

                for(InventoryResponseDTO inv: Arrays.asList(inventoryResponseDTOSArray)){
                    if(inv.isInInventory()==false) {
                        orderResponseDTO.setOrderStatus("fail");
                        orderResponseDTO.setErrorMessage("Inventory not enough "+inv.getSkuCode() +" have only "+inv.getQuentity()+" items remaining.");
                        return orderResponseDTO;

                    }
                }

           order.setOrderLineItemList(orderLineItemList);
           Order savedOrder=orderRepository.save(order);
            if(savedOrder!=null){
                orderResponseDTO.setId(savedOrder.getId());
                orderResponseDTO.setOrderNumber(savedOrder.getOrderNumber());
                orderResponseDTO.setOrderLineItemList(order.getOrderLineItemList().stream().map(orderLineItem->mapToOrderLineItemDTO(orderLineItem)).collect(Collectors.toList()));
                orderResponseDTO.setOrderStatus("success");

                //Sending message to kafka server. (So, now this application becomes a kafka producer)
                try {
                    kafkaTemplate.send("notificationTopic", new OrderPlaceEvent(order.getOrderNumber()));
                }catch(Exception ex){
                    System.out.println("Kafka exception | "+ex.getMessage());
                }
            }



    return orderResponseDTO;

    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDTO orderLineItemDTO) {
        OrderLineItem orderLineItem= new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDTO.getPrice());
        orderLineItem.setQuantity(orderLineItemDTO.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDTO.getSkuCode());
        return orderLineItem;
    }

    private OrderLineItemDTO mapToOrderLineItemDTO(OrderLineItem orderLineItem) {
        OrderLineItemDTO orderLineItemDTO= new OrderLineItemDTO();
        orderLineItemDTO.setId(orderLineItem.getId());
        orderLineItemDTO.setPrice(orderLineItem.getPrice());
        orderLineItemDTO.setQuantity(orderLineItem.getQuantity());
        orderLineItemDTO.setSkuCode(orderLineItem.getSkuCode());
        return orderLineItemDTO;
    }
}
