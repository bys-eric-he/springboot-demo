package com.example.service.impl;

import com.example.entity.Address;
import com.example.repository.AddressRepository;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address insertAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Set<Address> insertAddress(Set<Address> addresses) {
        addressRepository.saveAll(addresses);
        return addresses;
    }

    @Override
    public Address selectAddress(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.orElse(null);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
