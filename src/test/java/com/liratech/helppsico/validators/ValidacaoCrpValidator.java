package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.entrypoint.dto.ValidacaoCrpDto;
import com.liratech.helppsico.infrastructure.repositories.entities.ValidacaoCrpEntity;
import org.junit.jupiter.api.Assertions;

public class ValidacaoCrpValidator {
    public static void validaValidacaoCrpDomain(ValidacaoCrp esperado, ValidacaoCrp resultado){
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        Assertions.assertEquals(esperado.getPsicologo().getCrp(), resultado.getPsicologo().getCrp());
        Assertions.assertEquals(esperado.getMotivoReprova(), resultado.getMotivoReprova());
    }

    public static void validaValidacaoCrpMapperEntry(ValidacaoCrp domain, ValidacaoCrpDto dto){
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
        Assertions.assertEquals(domain.getPsicologo().getCrp(), dto.getPsicologo().getCrp());
        Assertions.assertEquals(domain.getMotivoReprova(), dto.getMotivoReprova());
    }

    public static void validaValidacaoCrpMapperInfra(ValidacaoCrp domain, ValidacaoCrpEntity entity){
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
        Assertions.assertEquals(domain.getPsicologo().getCrp(), entity.getPsicologo().getCrp());
        Assertions.assertEquals(domain.getMotivoReprova(), entity.getMotivoReprova());
    }
}
