package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.entrypoint.mapper.ProntuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("prontuarios")
@RequiredArgsConstructor
public class ProntuarioController {

    private final ProntuarioUseCase useCase;
    private final ProntuarioMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<ProntuarioDto>> registrar(@RequestBody ProntuarioDto novoProntuario) {
        ProntuarioDto response = mapper.paraDto(useCase.registrar(novoProntuario));
        ResponseDto<ProntuarioDto> resposta = new ResponseDto<>(response);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/prontuarios/{id}")
                                .buildAndExpand(response.getId())
                                .toUri()
                )
                .body(resposta);
    }

    @GetMapping("paciente")
    public ResponseEntity<ResponseDto<Page<ProntuarioDto>>> listarPorPaciente(
            @PathVariable("idPaciente") UUID idPaciente,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<ProntuarioDto> resultado = useCase.listarPorPaciente(idPaciente, pageable).map(mapper::paraDto);
        ResponseDto<Page<ProntuarioDto>> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("psicologo")
    public ResponseEntity<ResponseDto<Page<ProntuarioDto>>> listarPorPsicolopo(
            @PathVariable("idPsicologo") UUID idPsicologo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort.split(",")[0])));

        Page<ProntuarioDto> resultado = useCase.listarPorPsicologo(idPsicologo, pageable).map(mapper::paraDto);
        ResponseDto<Page<ProntuarioDto>> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ProntuarioDto>> alterar(@RequestBody ProntuarioDto novosDados, @PathVariable UUID idPronturia) {
        ProntuarioDto resultado = mapper.paraDto(useCase.alterar(novosDados, idPronturia));
        ResponseDto<ProntuarioDto> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<ProntuarioDto>> alterarParcial(@RequestBody Map<String, Object> campos, @PathVariable UUID idProntuario) {
        ProntuarioDto resultado = mapper.paraDto(useCase.alterarParcial(campos, idProntuario));
        ResponseDto<ProntuarioDto> resposta = new ResponseDto<>(resultado);

        return ResponseEntity.ok(resposta);
    }

}
