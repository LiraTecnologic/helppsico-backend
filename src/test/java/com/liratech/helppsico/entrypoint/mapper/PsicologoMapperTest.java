package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.infrastructure.mapper.EnderecoMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapperInfraImp;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PsicologoMapperTest {

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private PsicologoMapperImp mapper;
    private Psicologo domainTest;
    private PsicologoDto dtoTest;

    @Test
    void testePsicologoDomainParaDto() {
        domainTest = PsicologoBuilder.criarPsicologo();

        Mockito.when(enderecoMapper.paraDto(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PsicologoValidator.validaPsicologoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testePsicologoDtoParaDomain() {
        dtoTest = PsicologoBuilder.criarPsicologoDto();

        Mockito.when(enderecoMapper.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PsicologoValidator.validaPsicologoMapperEntry(domainTest, dtoTest);
    }
}