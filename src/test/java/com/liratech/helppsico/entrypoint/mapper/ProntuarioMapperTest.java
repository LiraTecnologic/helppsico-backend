package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.validators.ProntuarioValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class ProntuarioMapperTest {

    private ProntuarioMapper mapper;
    private Prontuario domainTest;
    private ProntuarioDto dtoTest;

    @Test
    void testeProntuarioDomainParaDto() {
        domainTest = ProntuarioBuilder.criarProntuario();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ProntuarioValidator.validaProntuarioMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeProntuarioDtoParaDomain() {
        dtoTest = ProntuarioBuilder.criarProntuarioDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ProntuarioValidator.validaProntuarioMapperEntry(domainTest, dtoTest);
    }
}