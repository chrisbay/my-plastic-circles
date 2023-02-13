package net.chrisbay.dao;

import java.util.List;

public interface Dao<T> {

    T get(Integer id);

    List<T> getAll();

    T save(T t);

    T delete(Integer id);

}
