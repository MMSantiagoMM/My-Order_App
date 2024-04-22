package com.app.myorder.services;

import java.util.List;
import java.util.Optional;

public interface GenericService <E,D,I>{


    public E create(D d);

    public Optional<E>  getOne(I id);

    public List<E> getAll();

    public Optional<E> update(D d,I id);

    public Boolean delete(I id);













}
