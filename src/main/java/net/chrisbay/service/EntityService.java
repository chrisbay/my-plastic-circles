package net.chrisbay.service;

import java.util.List;

public interface EntityService<T> {

    T get(Integer id);

    List<T> getAll();

    T save(T t);

    T delete(Integer id);

}
