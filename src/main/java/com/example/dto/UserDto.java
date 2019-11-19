package com.example.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private Long id;

    @NotNull(message = "用户名不允许为空！")
    private String username;

    private String content;

    private IDCardDto idCardDto;

    private List<AddressDto> addressDtos;

    private List<EmailAddressDto> emailAddressDtos;

    private List<ProductDto> productDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IDCardDto getIdCardDto() {
        return idCardDto;
    }

    public void setIdCardDto(IDCardDto idCardDto) {
        this.idCardDto = idCardDto;
    }

    public List<AddressDto> getAddressDtos() {
        return addressDtos;
    }

    public void setAddressDtos(List<AddressDto> addressDtos) {
        this.addressDtos = addressDtos;
    }

    public List<EmailAddressDto> getEmailAddressDtos() {
        return emailAddressDtos;
    }

    public void setEmailAddressDtos(List<EmailAddressDto> emailAddressDtos) {
        this.emailAddressDtos = emailAddressDtos;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}