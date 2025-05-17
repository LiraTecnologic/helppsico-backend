package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.entrypoint.mapper.AvaliacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/avaliacoes")
public class    AvaliacaoController {

    private final AvaliacaoUseCase useCase;
    private final AvaliacaoMapper mapper;

    @PostMapping()
    public ResponseEntity<ResponseDto<AvaliacaoDto>> avaliar(@RequestBody AvaliacaoDto avaliacao){
        AvaliacaoDto avaliacaoResultado = useCase.avaliar(mapper.paraDomain(avaliacao));
        ResponseDto<AvaliacaoDto> resposta = new ResponseDto<>(avaliacaoResultado);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/avaliacoes/{id}")
                                .buildAndExpand(avaliacaoResultado.getId())
                                .toUri()
                )
                .body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<AvaliacaoDto>> buscarPorId(@PathVariable UUID id){
        AvaliacaoDto avaliacaoDto = useCase.buscarPorId(id);
        ResponseDto<AvaliacaoDto> resposta = new ResponseDto<>(avaliacaoDto);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/psicologo/{id}")
    public ResponseEntity<ResponseDto<Page<AvaliacaoDto>>> listarPorPsicologo(@PathVariable UUID id){
        Page<AvaliacaoDto> avaliacaoDtoPage = useCase.listarPorPsicologo(id);
        ResponseDto<Page<AvaliacaoDto>> resposta = new ResponseDto<>(avaliacaoDtoPage);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        useCase.deletar(id);

        return ResponseEntity.noContent().build();
    }

}
