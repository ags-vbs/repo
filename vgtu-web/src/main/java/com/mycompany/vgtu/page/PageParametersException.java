package com.mycompany.vgtu.page;

public class PageParametersException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PageParametersException(String message) {
        super(message);
    }

    public PageParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
