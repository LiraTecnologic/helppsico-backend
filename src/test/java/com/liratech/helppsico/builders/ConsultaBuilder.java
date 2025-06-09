package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaBuilder {

    public static Consulta criarConsulta(){
        return Consulta.builder()
                .id(UUID.fromString("41ad1798-be2a-4a35-9537-e355e80a5737"))
                .paciente(PacienteBuilder.criarPaciente())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .horario(HorarioBuilder.criarHorario())
                .data(LocalDate.of(2020,10,20))
                .valor(new BigDecimal("400"))
                .finalizada(false)
                .endereco(EnderecoBuilder.criarEndereco())
                .build();
    }

    public static ConsultaDto criarConsultaDto(){
        return ConsultaDto.builder()
                .id(UUID.fromString("41ad1798-be2a-4a35-9537-e355e80a5737"))
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .horario(HorarioBuilder.criarHorarioDto())
                .data(LocalDate.of(2020,10,20))
                .valor(new BigDecimal("150.5"))
                .endereco(EnderecoBuilder.criarEnderecoDto())
                .finalizada(false)
                .build();
    }

    public static ConsultaEntity criarConsultaEntity() {
        return ConsultaEntity.builder()
                .id(UUID.fromString("977d87f8-b2b4-48f8-b844-1fab663d050a"))
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .horario(HorarioBuilder.criarHorarioEntity())
                .data(LocalDate.of(2020,10,20))
                .valor(new BigDecimal("150.5"))
                .endereco(EnderecoBuilder.criarEnderecoEntity())
                .finalizada(false)
                .build();
    }

    public static List<Consulta> criarListaConslta(){
        List<Consulta> consultas = new ArrayList<>();

        for(int i=0; i<3; i++){
            consultas.add(criarConsulta());
        }

        return consultas;
    }

    public static List<ConsultaEntity> criarListaConsultaEntity() {
        List<ConsultaEntity> consultaEntites = new ArrayList<>();

        for(int i =0; i<3; i++){
            consultaEntites.add(criarConsultaEntity());
        }

        return consultaEntites;
    }

    public static Page<ConsultaEntity> criarPageConsultaEntity() {
        Pageable pageable = PageRequest.of(0, 10);
        return transformarListaEmPaginaEntity(criarListaConsultaEntity(), pageable);
    }

    private static Page<ConsultaEntity> transformarListaEmPaginaEntity(List<ConsultaEntity> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<ConsultaEntity> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }

    private static Page<Consulta> transformarListaEmPaginaDomain(List<Consulta> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<Consulta> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }

    public static Page<Consulta> criarPageConsultaDomain() {
        Pageable pageable = PageRequest.of(0, 10);
        return transformarListaEmPaginaDomain(criarListaConslta(), pageable);
    }
}
