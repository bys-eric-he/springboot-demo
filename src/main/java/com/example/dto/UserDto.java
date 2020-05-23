package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDateTime;

    private LocalDateTime lastUpdateDateTime;

    private Date currentDateTime;

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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public Date getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(Date currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}