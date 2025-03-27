package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

class PsicologoMapperTest {

    private final PsicologoMapper psicologoMapper = Mappers.getMapper(PsicologoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void testeTransformacaoPsicologoDtoParaDomain() {
        PsicologoDto psicologoDto = PsicologoBuilder.criarPsicologoDto();
        Psicologo psicologo = psicologoMapper.paraDomain(psicologoDto);

        Assertions.assertNotNull(psicologo);
        PsicologoValidator.validaPsicologoDtoParaDomain(psicologoDto, psicologo);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para Dto")
    void testeTransformacaoPsicologoDomainParaDto() {
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();
        PsicologoDto psicologoDto = psicologoMapper.paraDto(psicologo);

        Assertions.assertNotNull(psicologoDto);
        PsicologoValidator.validaPsicologoDomainParaDto(psicologo, psicologoDto);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para Dtos")
    void testeTransformacaoPsicologoDomainParaDtos() {
        List<Psicologo> psicologos = PsicologoBuilder.criarListaPsicologo();
        List<PsicologoDto> psicologoDtos = psicologoMapper.paraDtos(psicologos);

        Assertions.assertNotNull(psicologoDtos);
        Assertions.assertEquals(psicologos.size(), psicologoDtos.size());

        for(int i = 0; i < psicologoDtos.size(); i++){
            Psicologo psicologo = psicologos.get(i);
            PsicologoDto psicologoDto = psicologoDtos.get(i);

            PsicologoValidator.validaPsicologoDomainParaDto(psicologo, psicologoDto);
        }
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de PageDomain para PageDtos")
    void testeTransformacaoPagePsicologoDomainParaDto () {
        List<Psicologo> psicologos = PsicologoBuilder.criarListaPsicologo();
        Page<Psicologo> pageDomain = new PageImpl<>(psicologos, PageRequest.of(0, 2), psicologos.size());
        Page<PsicologoDto> pageDto = psicologoMapper.pageDto(pageDomain);

        Assertions.assertNotNull(pageDto);
        Assertions.assertEquals(pageDomain.getTotalElements(), pageDto.getTotalElements());
        Assertions.assertEquals(pageDomain.getSize(), pageDto.getSize());

        List<PsicologoDto> dtos = pageDto.getContent();
        for(int i = 0; i < pageDto.getSize(); i++){
            Psicologo psicologo = psicologos.get(i);
            PsicologoDto dto = dtos.get(i);

            PsicologoValidator.validaPsicologoDomainParaDto(psicologo, dto);
        }
    }
}