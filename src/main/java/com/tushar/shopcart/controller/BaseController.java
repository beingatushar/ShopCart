package com.tushar.shopcart.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T, ID> {

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> create(T entity);

    ResponseEntity<T> update(ID id, T entity);

    ResponseEntity<Void> delete(ID id);

    ResponseEntity<T> get(ID id);
}