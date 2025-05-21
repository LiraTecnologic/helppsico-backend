package com.liratech.helppsico.entrypoint.controller.documento;

import com.liratech.helppsico.application.usecases.SolicitacaoDocumentoUseCase;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.entrypoint.mapper.SolicitacaoDocumentoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/solicitacoes-documentos")
public class SolicitacaoDocumentoController {

    private final SolicitacaoDocumentoUseCase useCase;
    private final SolicitacaoDocumentoMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<SolicitacaoDocumentoDto>> solicitarDocumentos(@RequestBody @Valid SolicitacaoDocumentoDto solicitacao){
        SolicitacaoDocumentoDto solicitacaoResultado = mapper.paraDto(useCase.criarSolicitacao(mapper.paraDomain(solicitacao)));
        ResponseDto<SolicitacaoDocumentoDto> solicitacaoResposta = new ResponseDto<>(solicitacaoResultado);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/solicitacoes-documentos/{id}")
                                .buildAndExpand(solicitacaoResultado.getId())
                                .toUri()
                )
                .body(solicitacaoResposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        useCase.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
