package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/psicologos")
@AllArgsConstructor
public class PsicologoController {

    private PsicologoUseCase useCase;
    private PsicologoMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<PsicologoDto>> cadastrar (@RequestBody PsicologoDto psicologo){
        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/psicologos/{id}")
                                .buildAndExpand()
                                .toUri()
                )
                .body();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<Page<PsicologoDto>>> listar (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        return ResponseEntity.ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PsicologoDto>> consultarPorId (@PathVariable UUID id){

        return ResponseEntity.ok();
    }

    @GetMapping("/nome")
    public ResponseEntity<ResponseDto<List<PsicologoDto>>> consultarPorNome (@RequestParam String nome){

        return ResponseEntity.ok();
    }

    @GetMapping("/melhores-avaliados")
    public ResponseEntity<ResponseDto<Page<PsicologoDto>>> consultarMelhoresAvaliados (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        return ResponseEntity.ok();
    }

    @GetMapping("/crp")
    public ResponseEntity<ResponseDto<PsicologoDto>> consultarPorCrp (@RequestParam String crp){

        return ResponseEntity.ok();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PsicologoDto>> alterar (@RequestBody PsicologoDto, @PathVariable UUID id){

        return ResponseEntity.ok();
    }

}
