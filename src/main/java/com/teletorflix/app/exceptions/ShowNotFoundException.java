package com.teletorflix.app.exceptions;

public class ShowNotFoundException extends RuntimeException {
    public static final String DESCRIPTION = "Show not found exception";
    private static final long serialVersionUID = 7830756676887746372L;

    public ShowNotFoundException() {
        super(DESCRIPTION);
    }

    public ShowNotFoundException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
