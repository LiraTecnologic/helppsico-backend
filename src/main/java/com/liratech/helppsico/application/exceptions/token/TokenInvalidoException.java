package com.liratech.helppsico.application.exceptions.token;

public class TokenInvalidoException extends RuntimeException {
    public TokenInvalidoException(String erroTokenTipoNaoEncontrado) {
    }

    public TokenInvalidoException(String erroTokenTipoNaoEncontrado, Throwable cause) {
    }
}
