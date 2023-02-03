package net.chrisbay.dao;

import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(Integer id);

    Iterable<T> getAll();

    void save(T t);

    void delete(T t);

}
