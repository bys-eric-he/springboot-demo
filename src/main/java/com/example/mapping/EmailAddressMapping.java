package com.example.mapping;

import com.example.dto.EmailAddressDto;
import com.example.entity.EmailAddress;

public class EmailAddressMapping {
    public static EmailAddress toEntity(EmailAddressDto emailAddressDto) {
        EmailAddress emailAddress = null;
        if (emailAddressDto != null) {
            emailAddress = new EmailAddress();
            emailAddress.setId(emailAddressDto.getId());
            emailAddress.setAddress(emailAddressDto.getAddress());
        }
        return emailAddress;
    }
}
