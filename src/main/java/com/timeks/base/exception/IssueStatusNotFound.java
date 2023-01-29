package com.timeks.base.exception;

import com.timeks.base.exception.TimeksRuntimeException;

public class IssueStatusNotFound extends TimeksRuntimeException {
    public static String MESSAGE = "Issue status code not found";

    public IssueStatusNotFound() {
        super(MESSAGE);
    }

    public IssueStatusNotFound(String message) {
        super(message);
    }
}
