package com.timeks.issue.exception;

import com.timeks.base.exception.TimeksRuntimeException;

public class IssueNotFound extends TimeksRuntimeException {
    public IssueNotFound() {
        super("Issue not found");
    }

    public IssueNotFound(String message) {
        super(message);
    }
}
