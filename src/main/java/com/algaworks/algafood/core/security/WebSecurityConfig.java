package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter { // Cuidar pois está depreciado

    @Override
    protected void configure(HttpSecurity http)  throws Exception {
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

       // return http.build();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        System.out.println(passwordEncoder().encode("123"));

        auth.inMemoryAuthentication()
                .withUser("joao")
                    .password(passwordEncoder().encode("123"))
                    .roles("ADMIN")
                .and()
                   .withUser("maria")
                 .password(passwordEncoder().encode("123"))
                .roles();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}


