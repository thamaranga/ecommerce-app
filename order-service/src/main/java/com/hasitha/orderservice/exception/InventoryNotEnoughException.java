package com.hasitha.orderservice.exception;

public class InventoryNotEnoughException extends  RuntimeException{

    public InventoryNotEnoughException(String message){
        super(message);
    }
}
