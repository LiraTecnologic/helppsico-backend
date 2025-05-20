package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.HorarioPsicologoUseCase;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.HorarioPsicologoMapper;
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
@RequestMapping("/horarios-psicologos")
@RequiredArgsConstructor
public class HorarioPsicologoController {
    private final HorarioPsicologoUseCase useCase;
    private final HorarioPsicologoMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<HorarioPsicologoDto>> cadastrar(@RequestBody @Valid HorarioPsicologoDto horarioDto){
        HorarioPsicologoDto horarioPsicologoDto = mapper.paraDto(
                useCase.cadastrar(mapper.paraDomain(horarioDto)));

        ResponseDto<HorarioPsicologoDto> response = new ResponseDto<>(horarioPsicologoDto);

        return ResponseEntity
                .created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/horarios-psicologos/{id}")
                        .buildAndExpand(horarioPsicologoDto.getId())
                        .toUri())
                .body(response);
    }

    @GetMapping("/psicologo/{id}")
    public ResponseEntity<ResponseDto<Page<HorarioPsicologoDto>>> listarPorPsicologo(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dia,asc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<HorarioPsicologoDto> horarioPsicologoDtoPage = useCase.listarPorPsicologo(id, pageable).map(mapper::paraDto);

        ResponseDto<Page<HorarioPsicologoDto>> response = new ResponseDto<>(horarioPsicologoDtoPage);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<HorarioPsicologoDto>> consultarPorId(@PathVariable UUID id){
        HorarioPsicologoDto horarioPsicologoDto = mapper.paraDto(useCase.consultarPorId(id));

        ResponseDto<HorarioPsicologoDto> response = new ResponseDto<>(horarioPsicologoDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<HorarioPsicologoDto>> alterar (@PathVariable UUID id, @RequestBody HorarioPsicologoDto horarioPsicologoDtoNovo){
        HorarioPsicologoDto horarioPsicologoAlterado = mapper.paraDto(useCase.alterar(mapper.paraDomain(horarioPsicologoDtoNovo), id));

        ResponseDto<HorarioPsicologoDto> response = new ResponseDto<>(horarioPsicologoAlterado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        useCase.deletar(id);

        return ResponseEntity.noContent().build();
    }
}