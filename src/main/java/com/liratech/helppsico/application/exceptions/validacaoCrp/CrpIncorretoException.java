package com.liratech.helppsico.application.exceptions.validacaoCrp;

public class CrpIncorretoException extends RuntimeException{
    public CrpIncorretoException(String message) {
        super(message);
    }
}
