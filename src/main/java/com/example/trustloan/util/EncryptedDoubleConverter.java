package com.example.trustloan.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EncryptedDoubleConverter implements AttributeConverter<Double, String> {

    @Override
    public String convertToDatabaseColumn(Double attribute) {
        try {
            if (attribute == null) return null;
            return EncryptionUtil.encrypt(String.valueOf(attribute));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    @Override
    public Double convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) return null;
            return Double.parseDouble(EncryptionUtil.decrypt(dbData));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}