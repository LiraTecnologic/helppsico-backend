package com.liratech.helppsico.validators;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.domain.documento.Documento;
import org.junit.jupiter.api.Assertions;

public class DocumentoValidator {
    public static void validaDocumentoDomain (Documento esperado, Documento resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getDataEmissao(), resultado.getDataEmissao());
        Assertions.assertEquals(esperado.getDataValidade(), resultado.getDataValidade());
        Assertions.assertEquals(esperado.getAssinaturaPsicologo(), resultado.getAssinaturaPsicologo());
        Assertions.assertEquals(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getPsicologo(), resultado.getPsicologo());
    }

    public static void validaAtestadoDomain (Atestado esperado, Documento resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getDataEmissao(), resultado.getDataEmissao());
        Assertions.assertEquals(esperado.getDataValidade(), resultado.getDataValidade());
        Assertions.assertEquals(esperado.getAssinaturaPsicologo(), resultado.getAssinaturaPsicologo());
        Assertions.assertEquals(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getPsicologo(), resultado.getPsicologo());
        Assertions.assertEquals(esperado.getDataAtendimento(), );
        Assertions.assertEquals();
        Assertions.assertEquals();
        Assertions.assertEquals();
        Assertions.assertEquals();
        Assertions.assertEquals();
        Assertions.assertEquals();
        Assertions.assertEquals();

    }
}
