package com.timeks.base.enums.converters;

import com.timeks.base.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(Integer integer) {
        return Status.getEnum(integer);
    }
}
