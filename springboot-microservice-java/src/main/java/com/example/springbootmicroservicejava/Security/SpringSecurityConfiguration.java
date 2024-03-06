package com.example.springbootmicroservicejava.Security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //all requets shiold be authenticated
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        //if a request is not authencicated, a web page is shown
        http.httpBasic(withDefaults());
        // disable CSRF-> post and put requests
        http.csrf().disable();
        return http.build();
    }
}
