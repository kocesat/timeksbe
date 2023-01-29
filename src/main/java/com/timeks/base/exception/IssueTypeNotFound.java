package com.timeks.base.exception;

import com.timeks.base.exception.TimeksRuntimeException;

public class IssueTypeNotFound extends TimeksRuntimeException {
    public static String MESSAGE = "Issue type code not found";

    public IssueTypeNotFound() {
        super(MESSAGE);
    }

    public IssueTypeNotFound(String message) {
        super(message);
    }
}
