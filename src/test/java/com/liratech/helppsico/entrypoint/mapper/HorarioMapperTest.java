package com.liratech.helppsico.entrypoint.mapper;


import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.validators.HorarioValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class HorarioMapperTest {

    private HorarioMapper mapper;
    private Horario domainTest;
    private HorarioDto dtoTest;

    @Test
    void testeHorarioDomainParaDto(){
        domainTest = HorarioBuilder.criarHorario();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        HorarioValidator.validaHorarioMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeHorarioDtoParaDomain(){
        dtoTest = HorarioBuilder.criarHorarioDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        HorarioValidator.validaHorarioMapperEntry(domainTest, dtoTest);
    }
}
