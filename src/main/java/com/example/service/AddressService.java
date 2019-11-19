package com.example.service;

import com.example.entity.Address;

import java.util.Set;

/**
 * Created by eric on 2017/2/28.
 */
public interface AddressService {
    Address insertAddress(Address address);

    Set<Address> insertAddress(Set<Address> addresses);

    Address selectAddress(Long id);

    void deleteAddress(Long id);
}
