package com.example.mapping;

import com.example.dto.IDCardDto;
import com.example.entity.IDCard;

public class IDCardMapping {
    public static IDCard toEntity(IDCardDto idCardDto) {
        IDCard idCard = null;
        if (idCardDto != null) {
            idCard = new IDCard();
            idCard.setCardNumber(idCardDto.getCardNumber());
            idCard.setCardAddress(idCardDto.getCardAddress());
            idCard.setId(idCardDto.getId());
        }
        return idCard;
    }
}
