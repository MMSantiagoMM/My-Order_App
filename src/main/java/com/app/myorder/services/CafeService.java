package com.app.myorder.services;

import com.app.myorder.dtos.CafeDto;
import com.app.myorder.entities.Cafe;
import com.app.myorder.entities.Pub;
import com.app.myorder.exceptions.OrderNotFoundException;
import com.app.myorder.repositories.CafeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CafeService implements IGenericService<Cafe, CafeDto, UUID>{

    private final CafeRepository repository;

    public CafeService(CafeRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cafe create(CafeDto dto) {
        Cafe cafe = new Cafe();

        cafe.setId(dto.getId());
        cafe.setName(dto.getName());
        cafe.setTableNumber(dto.getTableNumber());
        cafe.setOrder(dto.getOrder());
        cafe.setTax(19.0);
        cafe.setTotal(calculateTotalWithTaxes(calculateTotalBill(dto.getOrder())));
        return repository.save(cafe);
    }

    @Override
    public Optional<Cafe> getOne(UUID id) {
        return Optional.of(repository.findById(id)).orElseThrow(()-> new OrderNotFoundException(id));
    }

    @Override
    public List<Cafe> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cafe> update(CafeDto dto, UUID id) {
        Optional<Cafe> existingCafe = repository.findById(id);
        if(existingCafe.isPresent()){
            Cafe existingCafeEntity = existingCafe.get();
            BeanUtils.copyProperties(dto, existingCafeEntity, getNullPropertyNames(dto));

            existingCafeEntity.setTotal(calculateTotalWithTaxes(calculateTotalBill(dto.getOrder())));
            return Optional.of(repository.save(existingCafeEntity));
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
}
