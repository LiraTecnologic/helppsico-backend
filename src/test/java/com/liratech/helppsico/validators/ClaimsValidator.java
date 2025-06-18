package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class ClaimsValidator {
    public static void validaClaimPaciente(Paciente paciente, Map<String, Object> claims){
        Assertions.assertEquals(paciente.getId().toString(), claims.get("id"));
        Assertions.assertEquals("PACIENTE", claims.get("tipo"));
        Assertions.assertEquals(paciente.getNome(), claims.get("nome"));
        Assertions.assertEquals(paciente.getCpf(), claims.get("cpf"));
        Assertions.assertEquals(paciente.getEmail(), claims.get("email"));
        Assertions.assertEquals(paciente.getGenero(), claims.get("genero"));
    }

    public static void validaClaimPsicologo(Psicologo psicologo, Map<String, Object> claims) {
        Assertions.assertEquals(psicologo.getId().toString(), claims.get("id"));
        Assertions.assertEquals("PSICOLOGO", claims.get("tipo"));
        Assertions.assertEquals(psicologo.getNome(), claims.get("nome"));
        Assertions.assertEquals(psicologo.getCrp(), claims.get("crp"));
        Assertions.assertEquals(psicologo.getCpf(), claims.get("cpf"));
        Assertions.assertEquals(psicologo.getEmail(), claims.get("email"));
        Assertions.assertEquals(psicologo.getGenero(), claims.get("genero"));
    }
}