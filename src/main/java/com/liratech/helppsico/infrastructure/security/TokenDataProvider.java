package com.liratech.helppsico.infrastructure.security;

import com.liratech.helppsico.application.gateways.TokenGateway;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.application.exceptions.token.TokenExpiradoException;
import com.liratech.helppsico.application.exceptions.token.TokenInvalidoException;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenDataProvider implements TokenGateway {

    private final String chaveSecreta;

    public static final String ERRO_TOKEN_INVALIDO = "Token inválido.";
    public static final String ERRO_TOKEN_TIPO_NAO_ENCONTRADO = "Claim tipo do token não exncontrado.";
    public static final String ERRO_TOKEN_EXPIRADO = "Token expirado.";

    public TokenDataProvider(){
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        this.chaveSecreta = dotenv.get("JWT_SECRET_KEY");

        if (chaveSecreta == null || chaveSecreta.isBlank()) {
            throw new IllegalStateException("Chave JWT não configurada no .env");
        }
    }

    @Override
    public String gerarTokenPaciente(Paciente paciente) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", paciente.getId().toString());
        claims.put("tipo", "PACIENTE");
        claims.put("nome", paciente.getNome());
        claims.put("cpf", paciente.getCpf());
        claims.put("email", paciente.getEmail());
        claims.put("genero", paciente.getGenero().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(paciente.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(gerarDataDeExpiracao()))
                .signWith(
                        Keys.hmacShaKeyFor(chaveSecreta.getBytes()),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String gerarTokenPsicologo(Psicologo psicologo) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", psicologo.getId().toString());
        claims.put("tipo", "PSICOLOGO");
        claims.put("nome", psicologo.getNome());
        claims.put("crp", psicologo.getCrp());
        claims.put("cpf", psicologo.getCpf());
        claims.put("email", psicologo.getEmail());
        claims.put("genero", psicologo.getGenero().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(psicologo.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(gerarDataDeExpiracao()))
                .signWith(
                        Keys.hmacShaKeyFor(chaveSecreta.getBytes()),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String validarToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(chaveSecreta.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            if (claims.getExpiration().before(new Date())){
                throw new TokenExpiradoException(ERRO_TOKEN_EXPIRADO);
            }

            String tipo = claims.get("tipo", String.class);
            if (tipo == null) {
                throw new TokenInvalidoException(ERRO_TOKEN_TIPO_NAO_ENCONTRADO);
            }

            return tipo;
        } catch (ExpiredJwtException ex) {
            log.error(ERRO_TOKEN_EXPIRADO, ex);
            throw new TokenExpiradoException(ERRO_TOKEN_EXPIRADO, ex.getCause());
        } catch (JwtException | IllegalArgumentException ex) {
            log.error(ERRO_TOKEN_INVALIDO, ex);
            throw new TokenInvalidoException(ERRO_TOKEN_INVALIDO, ex.getCause());
        }
    }

    public Claims extrairClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(chaveSecreta.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Instant gerarDataDeExpiracao() {
        return Instant.now().plus(1, ChronoUnit.DAYS);
    }
}