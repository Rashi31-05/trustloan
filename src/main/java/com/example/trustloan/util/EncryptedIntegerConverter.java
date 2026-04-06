package com.example.trustloan.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EncryptedIntegerConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer attribute) {
        try {
            if (attribute == null) return null;
            return EncryptionUtil.encrypt(String.valueOf(attribute));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    @Override
    public Integer convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) return null;
            return Integer.parseInt(EncryptionUtil.decrypt(dbData));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}