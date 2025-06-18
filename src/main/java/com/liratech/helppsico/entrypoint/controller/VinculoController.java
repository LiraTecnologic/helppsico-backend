package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.VinculoUseCase;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.entrypoint.mapper.VinculoMapper;
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
@RequestMapping("/vinculos")
@RequiredArgsConstructor
public class VinculoController {
    private final VinculoUseCase useCase;
    private final VinculoMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<VinculoDto>> solicitarVinculo(@RequestBody @Valid VinculoDto vinculoDto){
        VinculoDto vinculoSalvo = mapper.paraDto(useCase.criarSolicitacaoVinculo(mapper.paraDomain(vinculoDto)));
        ResponseDto<VinculoDto> response = new ResponseDto<>(vinculoSalvo);

        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/vinculos/{id}")
                        .buildAndExpand(vinculoSalvo.getId())
                        .toUri()
        ).body(response);
    }

    @GetMapping("/listar/psicologo/{id}")
    public ResponseEntity<ResponseDto<Page<VinculoDto>>> listarPorIdPsicologo(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "paciente.nome,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));
        Page<VinculoDto> vinculoDtoPage = useCase.listarPorIdPsicologo(id, pageable).map(mapper::paraDto);

        ResponseDto<Page<VinculoDto>> response = new ResponseDto<>(vinculoDtoPage);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/paciente/{id}")
    public ResponseEntity<ResponseDto<Page<VinculoDto>>> listarPorIdPaciente(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "psicologo.nome,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));
        Page<VinculoDto> vinculoDtoPage = useCase.listarPorIdPaciente(id, pageable).map(mapper::paraDto);

        ResponseDto<Page<VinculoDto>> response = new ResponseDto<>(vinculoDtoPage);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/aceitar/{id}")
    public ResponseEntity<ResponseDto<VinculoDto>> aceitarVinculo(@PathVariable UUID id){
        VinculoDto vinculoAceito = mapper.paraDto(useCase.aceitarSolicitacao(id));
        ResponseDto<VinculoDto> response = new ResponseDto<>(vinculoAceito);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/recusar/{id}")
    public ResponseEntity<ResponseDto<VinculoDto>> recusarVinculo(@PathVariable UUID id){
        VinculoDto vinculoRecusado = mapper.paraDto(useCase.recusarSolicitacao(id));
        ResponseDto<VinculoDto> response = new ResponseDto<>(vinculoRecusado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desvincular(@PathVariable UUID id){
        useCase.desvincular(id);

        return ResponseEntity.noContent().build();
    }
}
