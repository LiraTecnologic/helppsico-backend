package com.liratech.helppsico.application.exceptions.prontuarios;

public class ErroAtualizarCamposEspecificosExcpetion extends RuntimeException {
    public ErroAtualizarCamposEspecificosExcpetion(String s, ReflectiveOperationException e) {
        super(s, e);
    }
}
