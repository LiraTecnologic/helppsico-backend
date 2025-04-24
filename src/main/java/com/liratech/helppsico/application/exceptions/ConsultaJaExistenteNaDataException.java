package com.liratech.helppsico.application.exceptions;

public class ConsultaJaExistenteNaDataException extends RuntimeException {
    public ConsultaJaExistenteNaDataException(String mensagemConsultaJaExistenteNaData) {
        super(mensagemConsultaJaExistenteNaData);
    }
}
