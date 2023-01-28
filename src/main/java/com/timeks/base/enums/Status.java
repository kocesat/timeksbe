package com.timeks.base.enums;

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

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
