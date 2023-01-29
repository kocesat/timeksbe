package com.timeks.base.enums.converters;

import com.timeks.base.enums.IssueType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IssueTypeConverter implements AttributeConverter<IssueType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(IssueType issueType) {
        return issueType.getCode();
    }

    @Override
    public IssueType convertToEntityAttribute(Integer integer) {
        return IssueType.getEnum(integer);
    }
}
