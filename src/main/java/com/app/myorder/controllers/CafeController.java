package com.app.myorder.controllers;


import com.app.myorder.dtos.CafeDto;
import com.app.myorder.entities.Cafe;
import com.app.myorder.services.CafeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cafe")
public class CafeController implements IGenericController<Cafe, CafeDto, UUID> {

    private final CafeService service;


    public CafeController(CafeService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<Cafe> create(CafeDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Cafe> getOne(UUID id) {
        return service.getOne(id).map(cafe -> new ResponseEntity<>(cafe,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Cafe>> getAll() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Cafe> update(CafeDto dto, UUID id) {
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
