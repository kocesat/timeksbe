package com.timeks.issue.exception;

import com.timeks.base.exception.TimeksRuntimeException;

public class ProjectNotFound extends TimeksRuntimeException {
    public ProjectNotFound() {
        super("Project not found");
    }

    public ProjectNotFound(String message) {
        super(message);
    }
}
