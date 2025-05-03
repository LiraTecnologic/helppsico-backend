package com.liratech.helppsico.entrypoint.controller.documento;

import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/solicitacoes-documentos")
public class SolicitacaoDocumentoController {

    private final SolicitacaoDocumentoUseCase useCase;
    private final SolicitacaoDocumentoMapper mapper;

    @PostMapping()
    public ResponseEntity<ResponseDto<SolicitacaoDocumentoDto>> solicitarDocumentos(@RequestBody SolicitacaoDocumentoDto solicitacao){
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
}
