package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.validators.VinculoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class VinculoMapperTest {

    private VinculoMapper mapper;
    private Vinculo domainTest;
    private VinculoDto dtoTest;

    @Test
    void testeVinculoDomainParaDto() {
        domainTest = VinculoBuilder.criarVinculo();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        VinculoValidator.validaVinculoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeVinculoDtoParaDomain() {
        dtoTest = VinculoBuilder.criarVinculoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        VinculoValidator.validaVinculoMapperEntry(domainTest, dtoTest);
    }
}