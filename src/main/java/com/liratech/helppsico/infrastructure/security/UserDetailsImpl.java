package com.liratech.helppsico.infrastructure.security;

import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final UUID id;
    private final String email;
    private final String senha;
    private final String tipo;

    public UserDetailsImpl(PsicologoEntity psicologo) {
        this.id = psicologo.getId();
        this.email = psicologo.getEmail();
        this.senha = psicologo.getSenha();
        this.tipo = "PSICOLOGO";
    }

    public UserDetailsImpl(PacienteEntity paciente) {
        this.id = paciente.getId();
        this.email = paciente.getEmail();
        this.senha = paciente.getSenha();
        this.tipo = "PACIENTE";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + tipo));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
