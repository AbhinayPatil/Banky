package com.abhinay.bankapp.bankapp.config;

import com.abhinay.bankapp.bankapp.filter.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
//                                                   DecryptionFilter decryptionFilter,
//                                                   EncryptionFilter encryptionFilter,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        //authorisation od role has been set on the respective controllers on the basis of roles
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                // Ensure request decryption happens early (if you use decryption)
//                .addFilterBefore(decryptionFilter, UsernamePasswordAuthenticationFilter.class)
                // JWT filter must run before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                // Run encryption after decryption and after response is produced
//                .addFilterAfter(encryptionFilter, DecryptionFilter.class);

        return http.build();
    }
}
