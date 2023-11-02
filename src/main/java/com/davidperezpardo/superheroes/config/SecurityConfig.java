package com.davidperezpardo.superheroes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.davidperezpardo.superheroes.filter.FixedTokenFilter;

/**
 * Security config class.
 * @author David
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    private static final String FIXED_TOKEN = "tu_token_fijo_aqui";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/public/**").permitAll() // Rutas públicas
            .antMatchers("/**").authenticated() // Todas las demás rutas requieren autenticación
            .and()
            .httpBasic()
            .and()
            .addFilterBefore(new FixedTokenFilter(), BasicAuthenticationFilter.class);
    }
}
