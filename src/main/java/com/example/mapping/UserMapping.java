package com.example.mapping;

import com.example.dto.*;
import com.example.entity.Address;
import com.example.entity.EmailAddress;
import com.example.entity.Product;
import com.example.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMapping {
    public static User toEntity(UserDto userDto) {
        User user = null;
        if (userDto != null) {
            user = new User();
            user.setId(userDto.getId());
            user.setUsername(userDto.getUsername());
            user.setContent(userDto.getContent());
        }

        return user;
    }

    public static UserDto mappingToUserInfo(User user) {
        UserDto dto = new UserDto();
        if (user != null) {
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setContent(user.getContent());
            dto.setCreateDateTime(user.getCreatedDate());
            dto.setLastUpdateDateTime(user.getLastModifiedDate());
            dto.setCurrentDateTime(new Date());

            if (user.getAddresses() != null && user.getAddresses().size() > 0) {
                List<AddressDto> addressDtos = new ArrayList<>();
                for (Address address : user.getAddresses()) {
                    AddressDto addressDto = new AddressDto();
                    addressDto.setId(address.getId());
                    addressDto.setCity(address.getCity());
                    addressDto.setCountry(address.getCountry());
                    addressDto.setStreet(address.getStreet());
                    addressDtos.add(addressDto);
                }
                dto.setAddressDtos(addressDtos);
            }

            if (user.getProducts() != null && user.getProducts().size() > 0) {
                List<ProductDto> productDtos = new ArrayList<>();
                for (Product product : user.getProducts()) {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setPrice(product.getPrice());

                    productDtos.add(productDto);
                }
                dto.setProductDtos(productDtos);
            }

            if (user.getIdCard() != null) {
                IDCardDto idCardDto = new IDCardDto();
                idCardDto.setId(user.getId());
                idCardDto.setCardAddress(user.getIdCard().getCardAddress());
                idCardDto.setCardNumber(user.getIdCard().getCardNumber());

                dto.setIdCardDto(idCardDto);
            }

            if (user.getEmailAddresses() != null) {
                List<EmailAddressDto> emailAddressDtos = new ArrayList<>();
                for (EmailAddress emailAddress : user.getEmailAddresses()) {
                    EmailAddressDto emailAddressDto = new EmailAddressDto();
                    emailAddressDto.setId(emailAddress.getId());
                    emailAddressDto.setAddress(emailAddress.getAddress());

                    emailAddressDtos.add(emailAddressDto);
                }

                dto.setEmailAddressDtos(emailAddressDtos);
            }
        }

        return dto;
    }
}
