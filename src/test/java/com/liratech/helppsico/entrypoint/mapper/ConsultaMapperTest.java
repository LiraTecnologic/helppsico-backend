package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.validators.EnderecoValidator;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

class ConsultaMapperTest {

    private final ConsultaMapper consultaMapper = Mappers.getMapper(ConsultaMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void testeTransformacaoConsultaDtoParaDomain() {
        ConsultaDto consultaDto = ConsultaBuilder.criarConsultaDto();
        Consulta consulta = consultaMapper.paraDomain(consultaDto);

        Assertions.assertNotNull(consulta);
        Assertions.assertEquals(consultaDto.getId(), consulta.getId());
        Assertions.assertNotNull(consulta.getPsicologo());
        PsicologoValidator.validaPsicologoDtoParaDomain(consultaDto.getPsicologo(), consulta.getPsicologo());
        Assertions.assertNotNull(consulta.getPaciente());
        PacienteValidator.validaPacienteDtoParaDomain(consultaDto.getPaciente(), consulta.getPaciente());
        Assertions.assertEquals(consultaDto.getDataHora(), consulta.getDataHora());
        Assertions.assertEquals(consultaDto.getValor(), consulta.getValor());
        EnderecoValidator.validaEnderecoDtoParaDomain(consultaDto.getEndereco(), consulta.getEndereco());
        Assertions.assertEquals(consultaDto.getFinalizada(), consulta.getFinalizada());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para Dto")
    void testeTransformacaoConsltaDomainParaDto() {
        Consulta consulta = ConsultaBuilder.criarConsulta();
        ConsultaDto consultaDto = consultaMapper.paraDto(consulta);

        Assertions.assertNotNull(consultaDto);
        Assertions.assertEquals(consulta.getId(), consultaDto.getId());
        Assertions.assertNotNull(consultaDto.getPsicologo());
        PsicologoValidator.validaPsicologoDomainParaDto(consulta.getPsicologo(), consultaDto.getPsicologo());
        Assertions.assertNotNull(consultaDto.getPaciente());
        PacienteValidator.validaPacienteDomainParaDto(consulta.getPaciente(), consultaDto.getPaciente());
        Assertions.assertEquals(consulta.getDataHora(), consultaDto.getDataHora());
        Assertions.assertEquals(consulta.getValor(), consultaDto.getValor());
        EnderecoValidator.validaEnderecoDomainParaDto(consulta.getEndereco(), consultaDto.getEndereco());
        Assertions.assertEquals(consulta.getFinalizada(), consultaDto.getFinalizada());
    }

    @Test
    @DisplayName("Caso de sucesso nas transformações de Domains para Dtos")
    void testeTransformacaoConsultaDomainsParaDtos() {
        List<Consulta> consultas = ConsultaBuilder.criarListaConslta();
        List<ConsultaDto> consultaDtos = consultaMapper.paraDtos(consultas);

        Assertions.assertNotNull(consultaDtos);
        Assertions.assertEquals(consultas.size(), consultaDtos.size());

        for(int i = 0; i<consultaDtos.size(); i++){
            Consulta consulta = consultas.get(i);
            ConsultaDto consultaDto = consultaDtos.get(i);

            Assertions.assertEquals(consulta.getId(), consultaDto.getId());
            Assertions.assertNotNull(consultaDto.getPsicologo());
            PsicologoValidator.validaPsicologoDomainParaDto(consulta.getPsicologo(), consultaDto.getPsicologo());
            Assertions.assertNotNull(consultaDto.getPaciente());
            PacienteValidator.validaPacienteDomainParaDto(consulta.getPaciente(), consultaDto.getPaciente());
            Assertions.assertEquals(consulta.getDataHora(), consultaDto.getDataHora());
            Assertions.assertEquals(consulta.getValor(), consultaDto.getValor());
            EnderecoValidator.validaEnderecoDomainParaDto(consulta.getEndereco(), consultaDto.getEndereco());
            Assertions.assertEquals(consulta.getFinalizada(), consultaDto.getFinalizada());
        }
    }
}