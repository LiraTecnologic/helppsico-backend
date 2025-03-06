package com.liratech.helppsico.entrypoint.dto.documento;

import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class DeclaracaoDto extends DocumentoDto{
    private String motivo;
    private String descricao;
    private String finalidade;

    public DeclaracaoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String motivo, String descricao, String finalidade){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.motivo = motivo;
        this.descricao = descricao;
        this.finalidade = finalidade;
    }
}
