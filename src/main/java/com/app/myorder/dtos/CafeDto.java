package com.app.myorder.dtos;

import java.util.Map;
import java.util.UUID;

public class CafeDto extends GenericDto{

    public CafeDto() {
    }

    public CafeDto(UUID id, String name, Integer tableNumber, Map<String, Double> order) {
        super(id, name, tableNumber, order);
    }
}
