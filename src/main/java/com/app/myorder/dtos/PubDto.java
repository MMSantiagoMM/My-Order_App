package com.app.myorder.dtos;

import java.util.Map;
import java.util.UUID;

public class PubDto extends GenericDto {

    public PubDto() {
    }

    public PubDto(UUID id, String name, Integer tableNumber, Map<String, Double> order) {
        super(id, name, tableNumber, order);
    }
}
