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

        Page<PsicologoDto> psicologos = useCase.listar(pageable);
        //Ver como utilizar o mapper nesta situação

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
    public ResponseEntity<ResponseDto<List<PsicologoDto>>> consultarPorNome (@RequestParam String nome){
        List<PsicologoDto> psicologos = mapper.paraDtos(useCase.consultarPorNome(nome));
        ResponseDto<List<PsicologoDto>> resposta = new ResponseDto<>(psicologos);
        //Ver como utilizar o mapper nesta situação

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/melhores-avaliados")
    public ResponseEntity<ResponseDto<Page<PsicologoDto>>> consultarMelhoresAvaliados (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<PsicologoDto> psicologos = useCase.consultarMelhoresAvaliados(pageable);
        ResponseDto<Page<PsicologoDto>> resposta = new ResponseDto<>(psicologos);
        //Ver como utilizar o mapper nesta situação

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
        PsicologoDto psicologoNovo = mapper.paraDto(useCase.alterar(psicologo, id));
        ResponseDto<PsicologoDto> resposta = new ResponseDto<>(psicologoNovo);
        return ResponseEntity.ok(resposta);
    }

}
