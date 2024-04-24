package com.app.myorder.services;

import com.app.myorder.entities.Pub;
import com.app.myorder.exceptions.OrderNotFoundException;
import com.app.myorder.dtos.PubDto;
import com.app.myorder.repositories.PubRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
public class PubService implements IGenericService<Pub, PubDto, UUID> {

    private final PubRepository repository;

    public PubService(PubRepository repository) {
        this.repository = repository;
    }



    @Override
    public Pub create(PubDto pubDto) {
        Pub pub = new Pub();

        pub.setId(pubDto.getId());
        pub.setName(pubDto.getName());
        pub.setTableNumber(pubDto.getTableNumber());
        pub.setOrder(pubDto.getOrder());
        pub.setTax(19.0);
        pub.setTotal(calculateTotalWithTaxes(calculateTotalBill(pubDto.getOrder())));
        return repository.save(pub);

    }

    @Override
    public Optional<Pub> getOne(UUID id) {
        return Optional.of(repository.findById(id)).orElseThrow(()-> new OrderNotFoundException(id));
    }

    @Override
    public List<Pub> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Pub> update(PubDto pubDto, UUID id) {
        Optional<Pub> existingPub = repository.findById(id);
        if(existingPub.isPresent()){
            Pub existingPubEntity = existingPub.get();
            BeanUtils.copyProperties(pubDto, existingPubEntity, getNullPropertyNames(pubDto));

            existingPubEntity.setTotal(calculateTotalWithTaxes(calculateTotalBill(pubDto.getOrder())));
            return Optional.of(repository.save(existingPubEntity));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(UUID id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }


    public Double calculateTotalBill(Map<String,Double> order){
        Collection<Double> valueOrder = order.values();
        Optional<Double> totalOptional = valueOrder.stream().reduce(Double::sum);
        return totalOptional.get();
    }

    public Double calculateTotalWithTaxes(Double total){
        return total + (total * 0.19);
    }


    private String[] getNullPropertyNames(Object source) {
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
