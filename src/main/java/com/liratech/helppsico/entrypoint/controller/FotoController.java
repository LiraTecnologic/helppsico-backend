package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.entrypoint.dto.FotoDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import com.liratech.helppsico.entrypoint.mapper.FotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class FotoController {

    private FotoUseCase useCase;
    private FotoMapper mapper;

    @PostMapping()
    public ResponseEntity<ResponseDto<FotoDto>> salvar(@RequestBody MultipartFile arquivoFoto, @PathVariable FotoDto fotoDto){
        FotoDto foto = mapper.paraDto(useCase.salvar(arquivoFoto, mapper.paraDomain(fotoDto)));

        ResponseDto<FotoDto> response = new ResponseDto<>(foto);

        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/pacientes/")
                        .build()
                        .toUri()
        ).body(response);
    }
}
