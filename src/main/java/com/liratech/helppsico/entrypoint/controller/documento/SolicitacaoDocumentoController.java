package com.liratech.helppsico.entrypoint.controller.documento;

import com.liratech.helppsico.application.usecases.SolicitacaoDocumentoUseCase;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.entrypoint.mapper.SolicitacaoDocumentoMapper;
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Page<SolicitacaoDocumentoDto>>> listarPorPsicologo(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "paciente.nome,asc") String sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));
        Page<SolicitacaoDocumentoDto> solicitacaoDocumentoDtoPage = useCase.listarPorPsicologo(id, pageable).map(mapper::paraDto);

        ResponseDto<Page<SolicitacaoDocumentoDto>> response = new ResponseDto<>(solicitacaoDocumentoDtoPage);

        return ResponseEntity.ok(response);
    }
}
