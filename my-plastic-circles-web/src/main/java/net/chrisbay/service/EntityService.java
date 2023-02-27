package net.chrisbay.service;

public interface EntityService<T> {

    T get(Integer id);

    T[] getAll();

    T save(T t);

    void delete(Integer id);

}
