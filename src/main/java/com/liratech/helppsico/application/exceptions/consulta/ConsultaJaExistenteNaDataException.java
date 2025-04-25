package com.liratech.helppsico.application.exceptions.consulta;

public class ConsultaJaExistenteNaDataException extends RuntimeException {
    public ConsultaJaExistenteNaDataException(String mensagemConsultaJaExistenteNaData) {
        super(mensagemConsultaJaExistenteNaData);
    }
}
