package com.example.orderhibfx.utils;


//DAO generico para ambas clases, Mensajes y Usuario
public interface DAO<T> {

    void save(T object);
    void update(T object);
    T get(Integer id);
    void delete(T object);
}