package com.liratech.helppsico.infrastructure.dataprovider.exceptions;

public class DataProviderException extends RuntimeException {
    public DataProviderException(String mensagemErroSalvar, Throwable cause) {
        super(mensagemErroSalvar, cause);
    }
}
