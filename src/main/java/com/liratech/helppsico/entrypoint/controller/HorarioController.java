package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.HorarioUseCase;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.entrypoint.mapper.HorarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/horarios")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioUseCase useCase;
    private final HorarioMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<HorarioDto>> cadastrar(@RequestBody @Valid HorarioDto horarioDto){
        HorarioDto horarioCadastrado = mapper.paraDto(
                useCase.cadastrar(mapper.paraDomain(horarioDto)));

        ResponseDto<HorarioDto> response = new ResponseDto<>(horarioDto);

        return ResponseEntity
                .created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/horarios-psicologos/{id}")
                        .buildAndExpand(horarioDto.getId())
                        .toUri())
                .body(response);
    }

    @GetMapping("/psicologo/{id}")
    public ResponseEntity<ResponseDto<List<HorarioDto>>> listarPorPsicologo(@PathVariable UUID id) {
        List<HorarioDto> horarioPsicologoDtoList = useCase.listarPorPsicologo(id)
                .stream().map(mapper::paraDto).toList();

        ResponseDto<List<HorarioDto>> response = new ResponseDto<>(horarioPsicologoDtoList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<HorarioDto>> consultarPorId(@PathVariable UUID id){
        HorarioDto horarioPsicologoDto = mapper.paraDto(useCase.consultarPorId(id));

        ResponseDto<HorarioDto> response = new ResponseDto<>(horarioPsicologoDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<HorarioDto>> alterar (@PathVariable UUID id, @RequestBody HorarioDto horarioPsicologoDtoNovo){
        HorarioDto horarioPsicologoAlterado = mapper.paraDto(useCase.alterar(mapper.paraDomain(horarioPsicologoDtoNovo), id));

        ResponseDto<HorarioDto> response = new ResponseDto<>(horarioPsicologoAlterado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        useCase.deletar(id);

        return ResponseEntity.noContent().build();
    }
}