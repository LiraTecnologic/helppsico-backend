package com.liratech.helppsico.application.exceptions.paciente;

public class PacienteExistenteException extends RuntimeException {
    public PacienteExistenteException(String message) {
        super(message);
    }
}
