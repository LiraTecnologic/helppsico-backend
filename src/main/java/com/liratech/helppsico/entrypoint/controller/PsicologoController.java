package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.PsicologoUseCase;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PsicologoController {

    private final PsicologoUseCase useCase;
    private final PsicologoMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<PsicologoDto>> cadastrar (@RequestBody @Valid PsicologoDto psicologo){
        PsicologoDto psicologoSalvo = mapper.paraDto(useCase.cadastrar(mapper.paraDomain(psicologo)));
        ResponseDto<PsicologoDto> resposta = new ResponseDto<>(psicologoSalvo);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/psicologos/{id}")
                                .buildAndExpand(psicologoSalvo.getId())
                                .toUri()
                )
                .body(resposta);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<Page<PsicologoDto>>> listar (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<PsicologoDto> psicologos = useCase.listar(pageable).map(mapper::paraDto);
        ResponseDto<Page<PsicologoDto>> resposta = new ResponseDto<>(psicologos);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PsicologoDto>> consultarPorId (@PathVariable UUID id){
        PsicologoDto psicologo = mapper.paraDto(useCase.consultarPorId(id));
        ResponseDto<PsicologoDto> resposta = new ResponseDto<>(psicologo);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/nome")
    public ResponseEntity<ResponseDto<Page<PsicologoDto>>> consultarPorNome (
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        String nomeFormatado = nome.replace("-", " ");

    Page<PsicologoDto> psicologos = useCase.consultarPorNome(nomeFormatado, pageable).map(mapper::paraDto);
        ResponseDto<Page<PsicologoDto>> resposta = new ResponseDto<>(psicologos);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/melhores-avaliados")
    public ResponseEntity<ResponseDto<Page<PsicologoDto>>> consultarMelhoresAvaliados (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<PsicologoDto> psicologos = useCase.consultarMelhoresAvaliados(pageable).map(mapper::paraDto);
        ResponseDto<Page<PsicologoDto>> resposta = new ResponseDto<>(psicologos);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/crp")
    public ResponseEntity<ResponseDto<PsicologoDto>> consultarPorCrp (@RequestParam String crp){
        PsicologoDto psicologo = mapper.paraDto(useCase.consultarPorCrp(crp));
        ResponseDto<PsicologoDto> resposta = new ResponseDto<>(psicologo);

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PsicologoDto>> alterar (@RequestBody PsicologoDto psicologo, @PathVariable UUID id){
        PsicologoDto psicologoNovo = mapper.paraDto(useCase.alterar(mapper.paraDomain(psicologo), id));
        ResponseDto<PsicologoDto> resposta = new ResponseDto<>(psicologoNovo);
        return ResponseEntity.ok(resposta);
    }

}
