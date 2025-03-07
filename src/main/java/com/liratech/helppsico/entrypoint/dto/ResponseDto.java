package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ResponseDto<T> {
    private T dado;
    private ErroDto erro;

    public ResponseDto(T dado) {
        this.dado = dado;
    }

    public static <T> ResponseDto<T> comErro(ErroDto erro) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setErro(erro);
        return responseDto;
    }

    @Getter
    @Setter
    @Builder
    public static class ErroDto {
        private List<String> message;
    }
}