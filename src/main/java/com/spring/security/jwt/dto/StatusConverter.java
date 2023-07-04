package com.spring.security.jwt.dto;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.name();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        return Status.valueOf(status);
    }
}