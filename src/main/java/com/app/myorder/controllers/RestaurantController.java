package com.app.myorder.controllers;

import com.app.myorder.dtos.RestaurantDto;
import com.app.myorder.entities.Restaurant;
import com.app.myorder.services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController implements IGenericController<Restaurant, RestaurantDto, UUID>{

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }


    @Override
    @PostMapping
    public ResponseEntity<Restaurant> create(RestaurantDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getOne(UUID id) {
        return service.getOne(id).map(restaurant -> new ResponseEntity<>(restaurant,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Restaurant> update(RestaurantDto dto, UUID id) {
        return service.update(dto,id)
                .map(field -> new ResponseEntity<>(field,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(UUID id) {
        if(service.delete(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
