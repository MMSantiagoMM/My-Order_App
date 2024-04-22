package com.app.myorder.dtos;

import java.util.Map;
import java.util.UUID;

public class PubDto {

    private UUID id;

    private String name;

    private Integer tableNumber;

    private Map<String,Double> order;

    private Double tax;



    public PubDto() {
    }

    public PubDto(UUID id, String name, Integer tableNumber, Map<String, Double> order, Double tax) {
        this.id = id;
        this.name = name;
        this.tableNumber = tableNumber;
        this.order = order;
        this.tax = tax;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Map<String, Double> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Double> order) {
        this.order = order;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }


}
