//package com.abhinay.bankapp.bankapp.config;
//
//import com.abhinay.bankapp.bankapp.filter.decryptionFilter.DecryptionFilter;
//import com.abhinay.bankapp.bankapp.filter.encryptionFilter.EncryptionFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, DecryptionFilter decryptionFilter, EncryptionFilter encryptionFilter) throws Exception {
//
//            http
//                    .csrf(AbstractHttpConfigurer::disable)
//                    .authorizeHttpRequests(auth -> auth
//                            .requestMatchers("/h2-console/**").permitAll()
//                            .anyRequest().authenticated()
//                    )
//                    .headers(headers -> headers
//                            .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
//                    )
//                    .addFilterBefore(decryptionFilter, UsernamePasswordAuthenticationFilter.class)
//                    .addFilterAfter(encryptionFilter, DecryptionFilter.class);
//            return http.build();
//    }
//}
