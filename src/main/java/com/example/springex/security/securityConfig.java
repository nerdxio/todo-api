package com.example.springex.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {
    private final String []PUBLIC_ENDPOINTS ={"/api/*/v1/auth/**"};
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable() // cokes
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/*/v1/auth/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
