package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.entrypoint.dto.ValidacaoCrpDto;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class ValidacaoCrpMapperTest {

    private ValidacaoCrpMapper mapper;
    private ValidacaoCrp domainTest;
    private ValidacaoCrpDto dtoTest;

    @Test
    void testeValidacaoCrpDomainParaDto(){
        domainTest = ValidacaoCrpBuilder.criarValidacaoCrp();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeValidacaoCrpDtoParaDomain(){
        dtoTest = ValidacaoCrpBuilder.criarValidacaoCrpDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperEntry(domainTest, dtoTest);
    }
}
