package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.application.usecases.LoginUseCase;
import com.liratech.helppsico.entrypoint.dto.LoginDto;
import com.liratech.helppsico.entrypoint.dto.LoginRespostaDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase useCase;

    @PostMapping("/paciente")
    public ResponseEntity<ResponseDto<LoginRespostaDto>> logarPaciente(@RequestBody @Valid LoginDto loginCorpo){
        LoginRespostaDto loginResposta = useCase.logarPaciente(loginCorpo.getEmail(), loginCorpo.getSenha());
        ResponseDto<LoginRespostaDto> resposta = new ResponseDto<LoginRespostaDto>();

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/psicologo")
    public ResponseEntity<ResponseDto<LoginRespostaDto>> logarPsicologo(@RequestBody @Valid LoginDto loginCorpo){
        LoginRespostaDto loginResposta = useCase.logarPsicologo(loginCorpo.getCrp(), loginCorpo.getSenha());
        ResponseDto<LoginRespostaDto> resposta = new ResponseDto<LoginRespostaDto>();

        return ResponseEntity.ok(resposta);
    }
}
