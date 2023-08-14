package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig  {


    /*
    @Bean
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("thiago")
                .password("123")
                .roles("ADMIN"); // Obrigatório, mas não serve para nada
    }
    */

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()

                .authorizeRequests()
                    .antMatchers("/v1/cozinhas/**").permitAll()
                    .anyRequest().authenticated()

                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Desabilita cookies

                .and()
                    .csrf().disable(); // Assim libera os métodos post

        return http.build();
    }
}


