package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.entrypoint.dto.ValidacaoCrpDto;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidacaoCrpMapperTest {

    @Mock
    private PsicologoMapper psicologoMapper;

    @InjectMocks
    private ValidacaoCrpMapperImpl mapper;
    private ValidacaoCrp domainTest;
    private ValidacaoCrpDto dtoTest;

    @Test
    void testeValidacaoCrpDomainParaDto(){
        domainTest = ValidacaoCrpBuilder.criarValidacaoCrp();

        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeValidacaoCrpDtoParaDomain(){
        dtoTest = ValidacaoCrpBuilder.criarValidacaoCrpDto();

        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperEntry(domainTest, dtoTest);
    }
}
