package com.liratech.helppsico.application.exceptions.consulta;

public class ConsultaNaoEncontradaException extends RuntimeException {
    public ConsultaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
