package com.davidperezpardo.superheroes.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

public class FixedTokenFilter extends GenericFilterBean {

	// Clave API fija
    private static final String FIXED_API_KEY = "api-key-fija";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // Obtener la API Key de la cabecera de la solicitud
        String apiKey = obtainApiKeyFromRequest(request);

        if (apiKey != null && apiKey.equals(FIXED_API_KEY)) {
            // Si la API Key es válida, crear y establecer la autenticación
            AbstractAuthenticationToken authentication = new AbstractAuthenticationToken(null) {

				private static final long serialVersionUID = 4900702079985360765L;

				@Override
                public Object getCredentials() {
                    return null;
                }

                @Override
                public Object getPrincipal() {
                    return apiKey;
                }
            };
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        } else {
        	 // Limpia el contexto de seguridad en caso de API Key inválida
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

    private String obtainApiKeyFromRequest(ServletRequest request) {
        // Lógica para obtener la API Key de la solicitud
        return ((HttpServletRequest) request).getHeader("X-API-Key");
    }  
}