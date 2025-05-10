package com.liratech.helppsico.application.exceptions.prontuarios;

public class ProntuarioNaoEncontradoException extends RuntimeException {
    public ProntuarioNaoEncontradoException() {
        super("Prontuario não encontrado pelo seu id.");
    }
}
