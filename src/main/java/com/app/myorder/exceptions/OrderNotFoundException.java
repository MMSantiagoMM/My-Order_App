package com.app.myorder.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(UUID id){
        super("The order with the id: " + id +" was not found");
    }
}
