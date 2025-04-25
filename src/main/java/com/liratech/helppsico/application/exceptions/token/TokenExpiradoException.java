package com.liratech.helppsico.application.exceptions.token;

public class TokenExpiradoException extends RuntimeException {
    public TokenExpiradoException(String erroTokenExpirado) {
    }

    public TokenExpiradoException(String erroTokenExpirado, Throwable cause) {
    }
}
