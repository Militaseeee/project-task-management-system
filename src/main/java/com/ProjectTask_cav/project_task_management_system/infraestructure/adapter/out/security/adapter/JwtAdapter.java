package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.security.adapter;

import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.TokenProviderPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtAdapter implements TokenProviderPort {

    // Nota: En un entorno real, esto iría en el application.properties
    private static final String SECRET_KEY = "mi_clave_secreta_super_segura_para_el_assessment_riwi";
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}