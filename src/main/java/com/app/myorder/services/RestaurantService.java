package com.app.myorder.services;

import com.app.myorder.dtos.RestaurantDto;
import com.app.myorder.entities.Pub;
import com.app.myorder.entities.Restaurant;
import com.app.myorder.exceptions.OrderNotFoundException;
import com.app.myorder.repositories.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService implements IGenericService<Restaurant, RestaurantDto, UUID>{

    public final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant create(RestaurantDto dto) {
        Restaurant restaurant = new Restaurant();

        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());
        restaurant.setTableNumber(dto.getTableNumber());
        restaurant.setOrder(dto.getOrder());
        restaurant.setTax(19.0);
        restaurant.setTotal(calculateTotalWithTaxes(calculateTotalBill(dto.getOrder())));
        return repository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> getOne(UUID id) {
        return Optional.of(repository.findById(id)).orElseThrow(()-> new OrderNotFoundException(id));
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Restaurant> update(RestaurantDto dto, UUID id) {
        Optional<Restaurant> existingRestaurant = repository.findById(id);
        if(existingRestaurant.isPresent()){
            Restaurant existingRestaurantEntity = existingRestaurant.get();
            BeanUtils.copyProperties(dto, existingRestaurantEntity, getNullPropertyNames(dto));

            existingRestaurantEntity.setTotal(calculateTotalWithTaxes(calculateTotalBill(dto.getOrder())));
            return Optional.of(repository.save(existingRestaurantEntity));
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
