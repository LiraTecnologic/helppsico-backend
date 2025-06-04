package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

@AllArgsConstructor
class AvaliacaoMapperTest {

    private AvaliacaoMapper mapper;
    private Avaliacao domainTest;
    private AvaliacaoDto dtoTest;

    @Test
    void testeAvaliacaoDomainParaDto() {
        domainTest = AvaliacaoBuilder.criarAvaliacao();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeAvaliacaoDtoParaDomain() {
        dtoTest = AvaliacaoBuilder.criarAvaliacaoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperEntry(domainTest, dtoTest);
    }
}
