package com.liratech.helppsico.application.exceptions;

public class PsicologoExistenteException extends RuntimeException {
    public PsicologoExistenteException(){
        super("Psicologo já está cadastrado");
    }
}
