package com.app.myorder.services;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;

public interface IGenericService<E,D,I>{


    public E create(D dto);

    public Optional<E>  getOne(I id);

    public List<E> getAll();

    public Optional<E> update(D dto,I id);

    public Boolean delete(I id);

    public default Double calculateTotalBill(Map<String,Double> order){
        Collection<Double> valueOrder = order.values();
        Optional<Double> totalOptional = valueOrder.stream().reduce(Double::sum);
        return totalOptional.get();
    }

    public default Double calculateTotalWithTaxes(Double total){
        return total + (total * 0.19);
    }

    public default String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }













}
