package com.liratech.helppsico.validator;

import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.TipoGeneroDto;
import org.junit.jupiter.api.Assertions;

public class TipoGeneroValidator {
    public static void validaTipoGenero(TipoGenero esperado, TipoGenero resultado) {
        Assertions.assertEquals(esperado, resultado);
    }

    public static void validaTipoGeneroDto(TipoGeneroDto esperado, TipoGeneroDto resultado) {
        Assertions.assertEquals(esperado, resultado);
    }

    public static void validaTipoGeneroDtoParaDomain(TipoGeneroDto esperado, TipoGenero resultado) {
        Assertions.assertEquals(TipoGenero.valueOf(esperado.name()), resultado);
    }

    public static void validaTipoGeneroDomainParaDto(TipoGenero esperado, TipoGeneroDto resultado) {
        Assertions.assertEquals(TipoGeneroDto.valueOf(esperado.name()), resultado);
    }
}
