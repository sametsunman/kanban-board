package com.canban.canbanapp.exception;

public class ServiceException extends Exception {
    private String code;
    private String message;

    public ServiceException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
