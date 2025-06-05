package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.validators.ConsultaValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class ConsultaMapperTest {

    private ConsultaMapper mapper;
    private ConsultaDto dtoTest;
    private Consulta domainTest;

    @Test
    void testeConsultaDtoParaDomain() {
        dtoTest = ConsultaBuilder.criarConsultaDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ConsultaValidator.validaConsultaMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeConsultaDomainParaDto() {
        domainTest = ConsultaBuilder.criarConsulta();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ConsultaValidator.validaConsultaMapperEntry(domainTest, dtoTest);
    }
}