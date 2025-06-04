package com.liratech.helppsico.entrypoint.controller.documento;

import com.liratech.helppsico.application.usecases.DocumentoUseCase;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.documento.DocumentoDto;
import com.liratech.helppsico.entrypoint.mapper.DocumentoMapper;
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
@RequestMapping("/documentos")
public class EmissaoDocumentoController {

    private final DocumentoUseCase useCase;
    private final DocumentoMapper mapper;

    @PostMapping("/{idSolicitacao}")
    public ResponseEntity<ResponseDto<DocumentoDto>> emitirDocumento(@RequestBody DadosGeraisDocumentoDto dadosGerais, @PathVariable UUID idSolicitacao) {
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

    @GetMapping("/{idPaciente}")
    public ResponseEntity<ResponseDto<Page<DocumentoDto>>> listarPorPaciente(
            @PathVariable UUID idPaciente,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "paciente,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));
        Page<DocumentoDto> documentoDtoPage = useCase.listarPorPaciente(idPaciente, pageable).map(mapper::paraDto);

        ResponseDto<Page<DocumentoDto>> response = new ResponseDto<>(documentoDtoPage);

        return ResponseEntity.ok(response);
    }
}
