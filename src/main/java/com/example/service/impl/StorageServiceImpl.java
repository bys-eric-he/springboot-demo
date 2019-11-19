package com.example.service.impl;

import com.example.properties.StorageServiceProperties;
import com.example.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("storageService")
public class StorageServiceImpl implements StorageService {
    private static Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    private HashMap<String, Object> storage = new HashMap<String, Object>();

    public StorageServiceImpl(StorageServiceProperties properties) {
        super();
        String url = properties.getUrl();
        String username = properties.getUsername();
        String password = properties.getPassword();
        logger.info("---->init storage with url " + url + " name: " + username + " password: " + password);
    }

    @Override
    public void put(String key, Object val) {
        storage.put(key, val);
    }

    @Override
    public Object get(String key) {
        return storage.get(key);
    }
}
