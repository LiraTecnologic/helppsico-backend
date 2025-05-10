package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/validacao-crp")
@RequiredArgsConstructor
public class ValidacaoCrpController {

    private final ValidacaoCrpUseCase useCase;
    private final ValidacaoCrpMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<ValidacaoCrpDto>> criar(@RequestBody ValidacaoCrpDto validacaoCrpDto){
        ValidacaoCrpDto validacaoCrpResultado = mapper.paraDto(useCase.criar(mapper.paraDomain(validacaoCrpDto)));
        ResponseDto<ValidacaoCrpDto> resultado = new ResponseDto<ValidacaoCrpDto>(validacaoCrpResultado);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/validacao-crp/{id}")
                                .buildAndExpand(validacaoCrpResultado.getId())
                                .toUri()
                )
                .body(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ValidacaoCrpDto>> validar(
            @RequestBody ValidacaoCrpDto validacaoCrpDto,
            @PathVariable UUID id){

        validacaoCrpDto.setId(id);
        ValidacaoCrpDto validacaoCrpResultado = mapper.paraDto(useCase.validar(mapper.paraDomain(validacaoCrpDto)));
        ResponseDto<ValidacaoCrpDto> resultado = new ResponseDto<>(validacaoCrpResultado);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<Page<ValidacaoCrpDto>>> listar(Pageable pageable){
        Page<ValidacaoCrpDto> validacoesCrpResultado = useCase.listar(pageable).map(mapper::paraDto);
        ResponseDto<Page<ValidacaoCrpDto>> resultado = new ResponseDto<>(validacoesCrpResultado);

        return ResponseEntity.ok(resultado);
    }
}
