package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.entrypoint.mapper.ConsultaMapper;
import com.liratech.helppsico.validator.EnderecoValidator;
import com.liratech.helppsico.validator.PacienteValidator;
import com.liratech.helppsico.validator.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

class ConsultaMapperTest {

    private final ConsultaMapper consultaMapper = Mappers.getMapper(ConsultaMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void transformacaoConsultaDtoParaDomain() {
        ConsultaDto consultaDto = ConsultaBuilder.criarConsultaDto();
        Consulta consulta = consultaMapper.paraDomain(consultaDto);

        Assertions.assertNotNull(consulta);
        Assertions.assertEquals(consultaDto.getId(), consulta.getId());
        PsicologoValidator.validaPsicologoDtoParaDomain(consultaDto.getPsicologo(), consulta.getPsicologo());
        PacienteValidator.validaPacienteDtoParaDomain(consultaDto.getPaciente(), consulta.getPaciente());
        Assertions.assertEquals(consultaDto.getDataHora(), consulta.getDataHora());
        EnderecoValidator.validaEnderecoDtoParaDomain(consultaDto.getEndereco(), consulta.getEndereco());
        Assertions.assertEquals(consultaDto.getFinalizada(), consulta.getFinalizada());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para Dto")
    void transformacaoConsltaDomainParaDto() {
        Consulta consulta = ConsultaBuilder.criarConsulta();
        ConsultaDto consultaDto = consultaMapper.paraDto(consulta);

        Assertions.assertNotNull(consultaDto);
        Assertions.assertEquals(consulta.getId(), consultaDto.getId());
        PsicologoValidator.validaPsicologoDomainParaDto(consulta.getPsicologo(), consultaDto.getPsicologo());
        PacienteValidator.validaPacienteDomainParaDto(consulta.getPaciente(), consultaDto.getPaciente());
        Assertions.assertEquals(consulta.getDataHora(), consultaDto.getDataHora());
        EnderecoValidator.validaEnderecoDomainParaDto(consulta.getEndereco(), consultaDto.getEndereco());
        Assertions.assertEquals(consulta.getFinalizada(), consultaDto.getFinalizada());
    }

    @Test
    @DisplayName("Caso de sucesso nas transformações de Domains para Dtos")
    void transformacaoConsultaDomainsParaDtos() {
        List<Consulta> consultas = ConsultaBuilder.criarListaConslta();
        List<ConsultaDto> consultaDtos = consultaMapper.paraDtos(consultas);

        Assertions.assertNotNull(consultaDtos);
        Assertions.assertEquals(consultas.size(), consultaDtos.size());

        for(int i = 0; i<consultaDtos.size(); i++){
            Consulta consulta = consultas.get(i);
            ConsultaDto consultaDto = consultaDtos.get(i);

            Assertions.assertEquals(consulta.getId(), consultaDto.getId());
            PsicologoValidator.validaPsicologoDomainParaDto(consulta.getPsicologo(), consultaDto.getPsicologo());
            PacienteValidator.validaPacienteDomainParaDto(consulta.getPaciente(), consultaDto.getPaciente());
            Assertions.assertEquals(consulta.getDataHora(), consultaDto.getDataHora());
            EnderecoValidator.validaEnderecoDomainParaDto(consulta.getEndereco(), consultaDto.getEndereco());
            Assertions.assertEquals(consulta.getFinalizada(), consultaDto.getFinalizada());
        }
    }
}