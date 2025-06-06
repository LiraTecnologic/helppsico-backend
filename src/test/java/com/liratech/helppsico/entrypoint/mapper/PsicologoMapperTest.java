package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class PsicologoMapperTest {

    private PsicologoMapper mapper;
    private Psicologo domainTest;
    private PsicologoDto dtoTest;

    @Test
    void testePsicologoDomainParaDto() {
        domainTest = PsicologoBuilder.criarPsicologo();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PsicologoValidator.validaPsicologoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testePsicologoDtoParaDomain() {
        dtoTest = PsicologoBuilder.criarPsicologoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PsicologoValidator.validaPsicologoMapperEntry(domainTest, dtoTest);
    }
}