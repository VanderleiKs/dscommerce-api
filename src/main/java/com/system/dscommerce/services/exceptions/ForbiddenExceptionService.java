package com.system.dscommerce.services.exceptions;

public class ForbiddenExceptionService extends RuntimeException {

    public ForbiddenExceptionService(String msg) {
        super(msg);
    }
}
