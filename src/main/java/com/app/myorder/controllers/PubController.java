package com.app.myorder.controllers;


import com.app.myorder.dtos.PubDto;
import com.app.myorder.entities.Pub;
import com.app.myorder.services.PubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pub")
public class PubController implements GenericController<Pub, PubDto, UUID> {

    private final PubService service;


    public PubController(PubService service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<Pub> create(PubDto dto) {
        return new ResponseEntity<>(service.create(dto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Pub> getOne(UUID id) {
        return service.getOne(id).map(pub -> new ResponseEntity<>(pub,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Pub>> getAll() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Pub> update(PubDto dto, UUID id) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(UUID id) {
        if(service.delete(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
