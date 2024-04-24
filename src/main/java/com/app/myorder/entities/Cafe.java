package com.app.myorder.entities;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Document("cafe")
public class Cafe extends GenericClass{

    public Cafe() {
    }

    public Cafe(UUID id, String name, Integer tableNumber, Map<String, Double> order, Double tax, Double total) {
        super(id, name, tableNumber, order, tax, total);
    }
}
