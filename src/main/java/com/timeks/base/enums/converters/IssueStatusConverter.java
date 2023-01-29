package com.timeks.base.enums.converters;

import com.timeks.base.enums.IssueStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IssueStatusConverter implements AttributeConverter<IssueStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(IssueStatus issueStatus) {
        return issueStatus == null ? null : issueStatus.getCode();
    }

    @Override
    public IssueStatus convertToEntityAttribute(Integer integer) {
        return IssueStatus.getEnum(integer);
    }
}
