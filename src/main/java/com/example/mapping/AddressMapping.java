package com.example.mapping;

import com.example.dto.AddressDto;
import com.example.entity.Address;

public class AddressMapping {
    public static Address toEntity(AddressDto addressDto) {
        Address address = null;
        if (addressDto != null) {
            address = new Address();
            address.setId(addressDto.getId());
            address.setCity(addressDto.getCity());
            address.setCountry(addressDto.getCountry());
            address.setStreet(addressDto.getStreet());
        }
        return address;
    }
}
