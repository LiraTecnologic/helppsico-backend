package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
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
    @DisplayName("Caso de sucesso na transformação de Domain para Entity")
    void testeTrasnformacaoConsultaDomainParaEntity() {
        Consulta consulta = ConsultaBuilder.criarConsulta();
        ConsultaEntity consultaEntity = consultaMapper.paraEntity(consulta);

        Assertions.assertNotNull(consultaEntity);
        PsicologoValidator.validaPsicologoDomainParaEntity(consulta.getPsicologo(), consultaEntity.getPsicologo());
        PacienteValidator.validaPacienteDomainParaEntity(consulta.getPaciente(), consultaEntity.getPaciente());
        Assertions.assertEquals(consulta.getDataHora(), consultaEntity.getDataHora());
        Assertions.assertEquals(consulta.getValor(), consultaEntity.getValor());
        EnderecoValidator.validaEnderecoDomainParaEntity(consulta.getEndereco(), consultaEntity.getEndereco());
        Assertions.assertEquals(consulta.getFinalizada(), consultaEntity.getFinalizada());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Entity para Domain")
    void testeTrasnformacaoConsultaEntityParaDomain() {
        ConsultaEntity consultaEntity = ConsultaBuilder.criarConsultaEntity();
        Consulta consulta = consultaMapper.paraDomain(consultaEntity);

        Assertions.assertNotNull(consulta);
        PsicologoValidator.validaPsicologoEntityParaDomain(consultaEntity.getPsicologo(), consulta.getPsicologo());
        PacienteValidator.validaPacienteEntityParaDomain(consultaEntity.getPaciente(), consulta.getPaciente());
        Assertions.assertEquals(consultaEntity.getDataHora(), consulta.getDataHora());
        Assertions.assertEquals(consultaEntity.getValor(), consulta.getValor());
        EnderecoValidator.validaEnderecoEntityParaDomain(consultaEntity.getEndereco(), consulta.getEndereco());
        Assertions.assertEquals(consultaEntity.getFinalizada(), consulta.getFinalizada());
    }

    @Test
    @DisplayName("Caso de sucesso na trasformação de Lista Entity para Lista Domain")
    void testeTrasnformacaoListConsultaEntityParaListConsultaDomain() {
        List<ConsultaEntity> consultaEntityList = ConsultaBuilder.criarPageConsultaEntity();
        List<Consulta> consultaList = consultaMapper.paraDomains(consultaEntityList);

        Assertions.assertNotNull(consultaList);
        Assertions.assertEquals(consultaEntityList.size(), consultaList.size());

        for(int i=0; i < consultaEntityList.size(); i++){
            ConsultaEntity consultaEntity = consultaEntityList.get(i);
            Consulta consulta = consultaList.get(i);

            Assertions.assertEquals(consultaEntity.getId(), consulta.getId());
            Assertions.assertNotNull(consulta.getPsicologo());
            PsicologoValidator.validaPsicologoEntityParaDomain(consultaEntity.getPsicologo(), consulta.getPsicologo());
            Assertions.assertNotNull(consulta.getPaciente());
            PacienteValidator.validaPacienteEntityParaDomain(consultaEntity.getPaciente(), consulta.getPaciente());
            Assertions.assertEquals(consultaEntity.getDataHora(), consulta.getDataHora());
            Assertions.assertEquals(consultaEntity.getValor(), consulta.getValor());
            Assertions.assertNotNull(consulta.getEndereco());
            EnderecoValidator.validaEnderecoEntityParaDomain(consultaEntity.getEndereco(), consulta.getEndereco());
            Assertions.assertEquals(consultaEntity.getFinalizada(), consulta.getFinalizada());
        }
    }
}