package com.timeks.base.enums;

import com.timeks.base.exception.IssueTypeNotFound;

import java.util.Arrays;

public enum IssueType {
    BUG(1, "Bug"),
    TASK(2, "Task");

    private final Integer code;
    private final String text;


    IssueType(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static IssueType getEnum(Integer code) {
        return code == null
                ? null
                : Arrays.stream(values())
                .filter(type -> type.code.equals(code))
                .findFirst()
                .orElseThrow(IssueTypeNotFound::new);
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
