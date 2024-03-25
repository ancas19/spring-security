package com.spring.security.secutiry;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.spring.security.services.interfaces.IJWTUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final IJWTUtility jwtUtility;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(Objects.isNull(header) || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);
        try {
            JWTClaimsSet claimsSet = jwtUtility.parseToken(token);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(claimsSet.getSubject(), null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (JOSEException | ParseException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request, response);
    }
}
