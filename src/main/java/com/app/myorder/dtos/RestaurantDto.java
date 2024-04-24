package com.app.myorder.dtos;

import java.util.Map;
import java.util.UUID;

public class RestaurantDto extends GenericDto{

    public RestaurantDto() {
    }

    public RestaurantDto(UUID id, String name, Integer tableNumber, Map<String, Double> order) {
        super(id, name, tableNumber, order);
    }
}
