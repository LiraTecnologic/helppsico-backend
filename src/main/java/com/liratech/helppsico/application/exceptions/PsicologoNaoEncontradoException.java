package com.liratech.helppsico.application.exceptions;

public class PsicologoNaoEncontradoException extends RuntimeException {
    public PsicologoNaoEncontradoException() {
        super("Psicologo não encontrado");
    }
}
