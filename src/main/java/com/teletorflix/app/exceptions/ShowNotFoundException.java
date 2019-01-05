package com.teletorflix.app.exceptions;

public class ShowNotFoundException extends RuntimeException{
    public static final String DESCRIPTION = "Show Not Found Exception";
    private static final long serialVersionUID = 6830756676887746370L;

    public ShowNotFoundException() {
        super(DESCRIPTION);
    }

    public ShowNotFoundException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
