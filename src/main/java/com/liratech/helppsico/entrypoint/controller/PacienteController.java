package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.PacienteUseCase;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteUseCase useCase;
    private final PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<PacienteDto>> cadastrar(@RequestBody @Valid PacienteDto paciente){
        PacienteDto pacienteNovo = mapper.paraDto(useCase.cadastrar(mapper.paraDomain(paciente)));
        ResponseDto<PacienteDto> retorno = new ResponseDto<>(pacienteNovo);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/pacientes/{id}")
                                .buildAndExpand(pacienteNovo.getId())
                                .toUri()
                )
                .body(retorno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PacienteDto>> consultarPorId (@PathVariable UUID id){
        PacienteDto pacienteDto = mapper.paraDto(useCase.consultarPorId(id));
        ResponseDto<PacienteDto> retorno = new ResponseDto<>(pacienteDto);

        return ResponseEntity.ok(retorno);
    }
}