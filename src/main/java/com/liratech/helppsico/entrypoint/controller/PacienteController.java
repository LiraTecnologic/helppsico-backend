package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private PacienteUseCase useCase;
    private PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<PacienteDto>> cadastrar(@RequestBody PacienteDto paciente){
        PacienteDto pacienteNovo = mapper.paraDto(useCase.cadastrar(mapper.paraDain(paciente)));
        ResponseDto<PacienteDto> retorno = new ResponseDto<>(pacienteNovo);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/paciente/{id}")
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