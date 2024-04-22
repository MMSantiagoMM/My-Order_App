package com.app.myorder.services;

import com.app.myorder.exceptions.OrderNotFoundException;
import com.app.myorder.dtos.PubDto;
import com.app.myorder.entities.Pub;
import com.app.myorder.repositories.PubRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        pub.setTax(pubDto.getTax());
        pub.setTotal(pubDto.getTotal());
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
}
