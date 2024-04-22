package com.app.myorder.services;

import com.app.myorder.exceptions.OrderNotFoundException;
import com.app.myorder.dtos.PubDto;
import com.app.myorder.entities.Pub;
import com.app.myorder.repositories.PubRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PubService implements GenericService<Pub, PubDto, UUID>{

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
        return null;
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


}
