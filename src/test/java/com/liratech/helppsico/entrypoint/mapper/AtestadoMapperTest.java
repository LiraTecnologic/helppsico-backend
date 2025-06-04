package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.entrypoint.dto.documento.AtestadoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class AtestadoMapperTest {
    private AtestadoMapper mapper;
    private Atestado domainTest;
    private AtestadoDto dtoTest;

    @Test
    void testeAtestadoDtoParaDomain(){
        domainTest = DocumentoBuilder.criarAtestado();
        dtoTest = mapper.paraDto(domainTest);

        DocumentoValidator.validaAtestadoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeAtestadoDomainParaDto(){
        dtoTest = DocumentoBuilder.criarAtestadoDto();
        domainTest = mapper.paraDomain(dtoTest);

        DocumentoValidator.validaAtestadoMapperEntry(domainTest, dtoTest);
    }
}
