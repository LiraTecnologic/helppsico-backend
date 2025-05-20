package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.EnderecoUseCase;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private EnderecoUseCase useCase;
    private EnderecoMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<EnderecoDto>> cadastrar(@RequestBody @Valid EnderecoDto enderecoDto){
        EnderecoDto enderecoNovo = mapper.paraDto(useCase.cadastrar(mapper.paraDomain(enderecoDto)));
        ResponseDto<EnderecoDto> retorno = new ResponseDto<>(enderecoDto);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/enderecos/{id}")
                                .buildAndExpand(enderecoNovo.getId())
                                .toUri()
                )
                .body(retorno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<EnderecoDto>> consultarPorId(@PathVariable UUID id){
        EnderecoDto enderecoDto = mapper.paraDto(useCase.consultarPorId(id));
        ResponseDto<EnderecoDto> retorno = new ResponseDto<>(enderecoDto);

        return ResponseEntity.ok(retorno);
    }
}
