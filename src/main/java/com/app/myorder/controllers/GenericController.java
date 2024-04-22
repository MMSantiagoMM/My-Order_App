package com.app.myorder.controllers;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GenericController<E,D,I> {


    public ResponseEntity<E> create(@RequestBody D dto);

    public ResponseEntity<E> getOne(@PathVariable I id);

    public ResponseEntity<List<E>> getAll();

    public ResponseEntity<E> update(@RequestBody D dto, @PathVariable I id);

    public ResponseEntity<Void> delete(@PathVariable I id);










}
