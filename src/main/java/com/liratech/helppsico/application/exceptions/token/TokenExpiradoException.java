package com.liratech.helppsico.application.exceptions.token;

public class TokenExpiradoException extends RuntimeException {
    public TokenExpiradoException(String message) {
        super(message);
    }

    public TokenExpiradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
