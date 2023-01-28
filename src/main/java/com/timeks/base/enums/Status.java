package com.timeks.base.enums;

import java.util.Arrays;

public enum Status {
    TODO(0, "Todo"),
    IN_PROGRESS(10,"In Progress"),
    IN_REVIEW(20,"In Review"),
    DONE(99,"Done");

    private final Integer code;
    private final String text;

    Status(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static Status getEnum(Integer code) {
        return code == null
                ? null
                : Arrays.stream(values())
                    .filter(s -> s.code.equals(code))
                    .findFirst().orElse(null);
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
