package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class VinculoValidatorJson {

    public static void validaVinculoJson(VinculoDto esperado, ResultActions resultado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId()))
                .andExpect(jsonPath("$.dado.paciente.id").value(esperado.getPaciente().getId().toString()))
                .andExpect(jsonPath("$.dado.paciente.nome").value(esperado.getPaciente().getNome()))
                .andExpect(jsonPath("$.dado.paciente.cpf").value(esperado.getPaciente().getCpf()))
                .andExpect(jsonPath("$.dado.paciente.email").value(esperado.getPaciente().getEmail()))
                .andExpect(jsonPath("$.dado.paciente.telefone").value(esperado.getPaciente().getTelefone()))
                .andExpect(jsonPath("$.dado.paciente.dataNascimento").value(esperado.getPaciente().getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.paciente.senha").value(esperado.getPaciente().getSenha()))
                .andExpect(jsonPath("$.dado.paciente.genero").value(esperado.getPaciente().getGenero().toString()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.id").value(esperado.getPaciente().getEndereco().getId().toString()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.rua").value(esperado.getPaciente().getEndereco().getRua()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.numero").value(esperado.getPaciente().getEndereco().getNumero()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.cep").value(esperado.getPaciente().getEndereco().getCep()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.cidade").value(esperado.getPaciente().getEndereco().getCidade()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.estado").value(esperado.getPaciente().getEndereco().getEstado()))
                .andExpect(jsonPath("$.dado.paciente.fotoUrl").value(esperado.getPaciente().getFotoUrl()))
                .andExpect(jsonPath("$.dado.psicologo.id").value(esperado.getPsicologo().getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo.nome").value(esperado.getPsicologo().getNome()))
                .andExpect(jsonPath("$.dado.psicologo.crp").value(esperado.getPsicologo().getCrp()))
                .andExpect(jsonPath("$.dado.psicologo.cpf").value(esperado.getPsicologo().getCpf()))
                .andExpect(jsonPath("$.dado.psicologo.email").value(esperado.getPsicologo().getEmail()))
                .andExpect(jsonPath("$.dado.psicologo.telefone").value(esperado.getPsicologo().getTelefone()))
                .andExpect(jsonPath("$.dado.psicologo.dataNascimento").value(esperado.getPsicologo().getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.psicologo.senha").value(esperado.getPsicologo().getSenha()))
                .andExpect(jsonPath("$.dado.psicologo.genero").value(esperado.getPsicologo().getGenero().toString()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.id").value(esperado.getPsicologo().getEnderecoAtendimento().getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.rua").value(esperado.getPsicologo().getEnderecoAtendimento().getRua()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.numero").value(esperado.getPsicologo().getEnderecoAtendimento().getNumero()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.cep").value(esperado.getPsicologo().getEnderecoAtendimento().getCep()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.cidade").value(esperado.getPsicologo().getEnderecoAtendimento().getCidade()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.estado").value(esperado.getPsicologo().getEnderecoAtendimento().getEstado()))
                .andExpect(jsonPath("$.dado.psicologo.fotoUrl").value(esperado.getPsicologo().getFotoUrl()))
                .andExpect(jsonPath("$.dado.psicologo.biografia").value(esperado.getPsicologo().getBiografia()))
                .andExpect(jsonPath("$.erro").doesNotExist());

    }

    public static void validaPageResponse(VinculoDto esperado, ResultActions resultado) throws Exception {
        resultado.andExpect(jsonPath("$.dado.length()").value(3))

                .andExpect(jsonPath("$.dado.pageable.pageNumber").value(0))
                .andExpect(jsonPath("$.dado.pageable.pageSize").value(10))

                .andExpect(jsonPath("$.dado.last").value(true))
                .andExpect(jsonPath("$.dado.first").value(true))
                .andExpect(jsonPath("$.dado.totalPages").value(1))
                .andExpect(jsonPath("$.dado.empty").value(false))

                .andExpect(jsonPath("$.dado.sort.sorted").value(true))
                .andExpect(jsonPath("$.dado.sort.unsorted").value(false))
                .andExpect(jsonPath("$.dado.sort.empty").value(false))
                .andExpect(jsonPath("$.dado.sort.orders[0].property").value("nome"))
                .andExpect(jsonPath("$.dado.sort.orders[0].direction").value("ASC"));

        for (int i = 0; i < 3; i++){
            resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId()))
                    .andExpect(jsonPath("$.dado.paciente.id").value(esperado.getPaciente().getId().toString()))
                    .andExpect(jsonPath("$.dado.paciente.nome").value(esperado.getPaciente().getNome()))
                    .andExpect(jsonPath("$.dado.paciente.cpf").value(esperado.getPaciente().getCpf()))
                    .andExpect(jsonPath("$.dado.paciente.email").value(esperado.getPaciente().getEmail()))
                    .andExpect(jsonPath("$.dado.paciente.telefone").value(esperado.getPaciente().getTelefone()))
                    .andExpect(jsonPath("$.dado.paciente.dataNascimento").value(esperado.getPaciente().getDataNascimento().toString()))
                    .andExpect(jsonPath("$.dado.paciente.senha").value(esperado.getPaciente().getSenha()))
                    .andExpect(jsonPath("$.dado.paciente.genero").value(esperado.getPaciente().getGenero().toString()))
                    .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.id").value(esperado.getPaciente().getEndereco().getId().toString()))
                    .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.rua").value(esperado.getPaciente().getEndereco().getRua()))
                    .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.numero").value(esperado.getPaciente().getEndereco().getNumero()))
                    .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.cep").value(esperado.getPaciente().getEndereco().getCep()))
                    .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.cidade").value(esperado.getPaciente().getEndereco().getCidade()))
                    .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.estado").value(esperado.getPaciente().getEndereco().getEstado()))
                    .andExpect(jsonPath("$.dado.paciente.fotoUrl").value(esperado.getPaciente().getFotoUrl()))
                    .andExpect(jsonPath("$.dado.psicologo.id").value(esperado.getPsicologo().getId().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.nome").value(esperado.getPsicologo().getNome()))
                    .andExpect(jsonPath("$.dado.psicologo.crp").value(esperado.getPsicologo().getCrp()))
                    .andExpect(jsonPath("$.dado.psicologo.cpf").value(esperado.getPsicologo().getCpf()))
                    .andExpect(jsonPath("$.dado.psicologo.email").value(esperado.getPsicologo().getEmail()))
                    .andExpect(jsonPath("$.dado.psicologo.telefone").value(esperado.getPsicologo().getTelefone()))
                    .andExpect(jsonPath("$.dado.psicologo.dataNascimento").value(esperado.getPsicologo().getDataNascimento().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.senha").value(esperado.getPsicologo().getSenha()))
                    .andExpect(jsonPath("$.dado.psicologo.genero").value(esperado.getPsicologo().getGenero().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.id").value(esperado.getPsicologo().getEnderecoAtendimento().getId().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.rua").value(esperado.getPsicologo().getEnderecoAtendimento().getRua()))
                    .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.numero").value(esperado.getPsicologo().getEnderecoAtendimento().getNumero()))
                    .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.cep").value(esperado.getPsicologo().getEnderecoAtendimento().getCep()))
                    .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.cidade").value(esperado.getPsicologo().getEnderecoAtendimento().getCidade()))
                    .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.estado").value(esperado.getPsicologo().getEnderecoAtendimento().getEstado()))
                    .andExpect(jsonPath("$.dado.psicologo.fotoUrl").value(esperado.getPsicologo().getFotoUrl()))
                    .andExpect(jsonPath("$.dado.psicologo.biografia").value(esperado.getPsicologo().getBiografia()))
                    .andExpect(jsonPath("$.erro").doesNotExist());
        }
    }
}