package com.tushar.shopcart.service;

import java.util.List;

public interface BaseService<E, ID> {
    List<E> getAll();

    E create(E entity);

    E update(ID id, E entity);

    void delete(ID id);

    E get(Long id);
}
