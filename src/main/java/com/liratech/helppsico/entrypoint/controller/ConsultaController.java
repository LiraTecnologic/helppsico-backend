package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.ConsultaUseCase;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.DataConsultaDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.entrypoint.mapper.ConsultaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaUseCase useCase;
    private final ConsultaMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<ConsultaDto>> agendar(@RequestBody @Valid ConsultaDto novaConsulta) {

        ConsultaDto resultado = mapper.paraDto(useCase.agendar(mapper.paraDomain(novaConsulta)));
        ResponseDto<ConsultaDto> resposta = new ResponseDto<>(resultado);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/consultas/{id}")
                                .buildAndExpand(resultado.getId())
                                .toUri()
                ).body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> cancelar(@PathVariable UUID id) {
        useCase.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/futuras/{idPaciente}")
    public ResponseEntity<ResponseDto<Page<ConsultaDto>>> consultarConsultasFuturasPaciente(
            @PathVariable("idPaciente") UUID idPaciente,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data,asc") String sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<ConsultaDto> resultado = useCase.consultarConsultasFuturasPaciente(idPaciente, pageable).map(mapper::paraDto);
        ResponseDto<Page<ConsultaDto>> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/paciente/historico/{idPaciente}")
    public ResponseEntity<ResponseDto<Page<ConsultaDto>>> consultarHistoricoPaciente(
            @PathVariable("idPaciente") UUID idPaciente,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data,asc") String sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<ConsultaDto> resultado = useCase.consultarHistoricoPaciente(idPaciente, pageable).map(mapper::paraDto);
        ResponseDto<Page<ConsultaDto>> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/psicologo/futuras/{idPsicologo}")
    public ResponseEntity<ResponseDto<Page<ConsultaDto>>> consultarConsultasFuturasPsicologo(
            @PathVariable("idPsicologo") UUID idPsicologo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data,asc") String sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<ConsultaDto> resultado = useCase.consultarConsultasFuturasPsicologo(idPsicologo, pageable).map(mapper::paraDto);
        ResponseDto<Page<ConsultaDto>> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/psicologo/historico/{idPsicologo}")
    public ResponseEntity<ResponseDto<Page<ConsultaDto>>> consultarHistoricoPsicologo(
            @PathVariable("idPsicologo") UUID idPsicologo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data,asc") String sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<ConsultaDto> resultado = useCase.consultarHistoricoPsicologo(idPsicologo, pageable).map(mapper::paraDto);
        ResponseDto<Page<ConsultaDto>> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @PatchMapping("/{idConsulta}")
    public ResponseEntity<ResponseDto<ConsultaDto>> alterarData(@PathVariable UUID idConsulta, @RequestBody DataConsultaDto novaData) {
        ConsultaDto resultado = mapper.paraDto(useCase.alterarData(
                idConsulta,
                mapper.paraDomain(ConsultaDto.builder()
                                .horario(novaData.getHorario())
                                .data(novaData.getData())
                        .build())));
        ResponseDto<ConsultaDto> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @PatchMapping("/finalizar/{idConsulta}")
    public ResponseEntity<ResponseDto<ConsultaDto>> finalizar(@PathVariable UUID idConsulta) {
        ConsultaDto resultado = mapper.paraDto(useCase.finalizar(idConsulta));
        ResponseDto<ConsultaDto> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }
}
