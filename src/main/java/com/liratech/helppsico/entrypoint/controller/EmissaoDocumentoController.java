package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.exceptions.TipoDocumentoInvalidoException;
import com.liratech.helppsico.application.usecases.DocumentoUseCase;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.documento.DocumentoDto;
import com.liratech.helppsico.entrypoint.mapper.DocumentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documentos")
public class EmissaoDocumentoController {

    private DocumentoUseCase useCase;
    private DocumentoMapper mapper;

    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto<DocumentoDto>> emitirDocumento(@RequestBody DadosGeraisDocumentoDto dadosGerais, @PathVariable UUID idSolicitacao) throws TipoDocumentoInvalidoException {
        DocumentoDto documentoDto = mapper.paraDto(useCase.salvar(idSolicitacao, dadosGerais));

        ResponseDto<DocumentoDto> response = new ResponseDto<>(documentoDto);

        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/documentos/{id}")
                        .buildAndExpand(documentoDto.getId())
                        .toUri()
        ).body(response);
    }

}
