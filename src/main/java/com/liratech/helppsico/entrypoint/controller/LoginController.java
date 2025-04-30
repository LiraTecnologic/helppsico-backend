package com.liratech.helppsico.entrypoint.controller;

import com.liratech.helppsico.entrypoint.dto.LoginDto;
import com.liratech.helppsico.entrypoint.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private LoginUseCase useCase;

    @PostMapping("/paciente")
    public ResponseEntity<ResponseDto<LoginRespostaDto>> logarPaciente(@RequestBody LoginDto loginCorpo){
        LoginRespostaDto loginResposta = useCase.logarPaciente(loginCorpo.getEmail(), loginCorpo.getSenha());
        ResponseDto<LoginRespostaDto> resposta = new ResponseDto<LoginRespostaDto>();

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/psicologo")
    public ResponseEntity<ResponseDto<LoginRespostaDto>> logarPsicologo(@RequestBody LoginDto loginCorpo){
        LoginRespostaDto loginResposta = useCase.logarPsicologo(loginCorpo.getCrp(), loginCorpo.getSenha());
        ResponseDto<LoginRespostaDto> resposta = new ResponseDto<LoginRespostaDto>();

        return ResponseEntity.ok(resposta);
    }
}
