package com.hasitha.orderservice.controller;

import com.hasitha.orderservice.dto.OrderRequestDTO;
import com.hasitha.orderservice.dto.OrderResponseDTO;
import com.hasitha.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //For implementing circuit breaker
    //@CircuitBreaker(name="inventory", fallbackMethod = "fallBackMethod")
    //For implementing timeout
    //@TimeLimiter(name = "inventory")
    //For implementing retry
    @Retry(name = "inventory")
    public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.placeOrder(orderRequestDTO);
    }


    //This is the fallback method for circuit breaker
    public OrderResponseDTO fallBackMethod(OrderRequestDTO orderRequestDTO , RuntimeException ex){
        OrderResponseDTO orderResponseDTO= new OrderResponseDTO();
        orderResponseDTO.setErrorMessage("Something went wrong. Please try again later.");
        return orderResponseDTO;
    }
}
