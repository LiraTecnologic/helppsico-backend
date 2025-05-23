package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.VinculoUseCase;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.entrypoint.mapper.VinculoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vinculos")
@RequiredArgsConstructor
public class VinculoController {
    private final VinculoUseCase useCase;
    private final VinculoMapper mapper;

    public ResponseEntity<ResponseDto<VinculoDto>> solicitarVinculo(@RequestBody @Valid VinculoDto vinculoDto){

    }

    public ResponseEntity<ResponseDto<VinculoDto>> aceitarVinculo(@PathVariable UUID id){

    }

    public ResponseEntity<Void> desvincular(@PathVariable UUID id){

    }

    public ResponseEntity<ResponseDto<Page<VinculoDto>>> listarPorIdPsicologo(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "paciente.nome,asc") String sort){

    }

    public ResponseEntity<ResponseDto<VinculoDto>> consultarPorIdPaciente(@PathVariable UUID id){

    }
}
