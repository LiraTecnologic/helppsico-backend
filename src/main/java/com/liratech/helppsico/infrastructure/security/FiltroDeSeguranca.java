package com.liratech.helppsico.infrastructure.security;

import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FiltroDeSeguranca extends OncePerRequestFilter {

    private final TokenDataProvider tokenDataProvider;
    private final PsicologoRepository psicologoRepository;
    private final PacienteRepository pacienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = recuperarToken(request);

            if (token == null){
                filterChain.doFilter(request, response);
                return;
            }

            String tipo = tokenDataProvider.validarToken(token);
            Claims claims = tokenDataProvider.extrairClaims(token);

            UUID id = claims.get("id", UUID.class);
            String email = claims.get("email", String.class);

            UserDetails usuario;

            if ("PACIENTE".equals(tipo)){
                PacienteEntity paciente = pacienteRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));

                usuario = new UserDetailsImpl(paciente);
            } else if ("PSICOLOGO".equals(tipo)){
                PsicologoEntity psicologo = psicologoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Psicologo não encontrado."));

                usuario = new UserDetailsImpl(psicologo);
            } else {
                throw new RuntimeException("Tipo de usuario invalido");
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }catch (Exception exception){
            log.warn("Autenticação JWT falhou: " + exception.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    public String recuperarToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
