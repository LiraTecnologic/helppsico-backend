package com.liratech.helppsico.application.exceptions;

public class ConsultaNaoEncontradaException extends RuntimeException {
    public ConsultaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
