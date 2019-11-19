package com.example.service;

public interface StorageService {
    void put(String key, Object val);

    Object get(String key);
}
