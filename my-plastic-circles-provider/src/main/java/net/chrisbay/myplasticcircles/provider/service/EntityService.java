package net.chrisbay.myplasticcircles.provider.service;

import java.util.List;

public interface EntityService<T> {

    T get(Integer id);

    List<T> getAll();

    void save(T t);

    void delete(Integer id);

}
