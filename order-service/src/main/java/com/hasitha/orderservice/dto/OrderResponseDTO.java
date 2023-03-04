package com.hasitha.orderservice.dto;

import com.hasitha.orderservice.model.OrderLineItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private long id;

    private String orderNumber;

    private List<OrderLineItemDTO> orderLineItemList;

    private String errorMessage=null;

    private String orderStatus=null;
}
