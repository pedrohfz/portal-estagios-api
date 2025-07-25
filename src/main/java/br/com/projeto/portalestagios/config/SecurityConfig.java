package br.com.projeto.portalestagios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/area-interesse/listar").permitAll()
                .requestMatchers("/api/estudante/**").permitAll()
                .requestMatchers("/api/usuario/**").hasRole("ADMIN")
                
                .requestMatchers("/api/area-interesse/listar").permitAll()
                .requestMatchers("/api/area-interesse/listar-areas-empresa").permitAll()
                .requestMatchers("/api/area-interesse/obter/**").hasAnyRole("ADMIN", "EMPRESA", "ESTUDANTE")
                .requestMatchers("/api/area-interesse/**").hasRole("ADMIN")
                
                .requestMatchers("/api/empresa/salvar").permitAll()
                .requestMatchers("/api/empresa/listar").hasAnyRole("ADMIN", "EMPRESA", "ESTUDANTE")
                .requestMatchers("/api/empresa/obter/**").hasAnyRole("ADMIN", "EMPRESA", "ESTUDANTE")
                .requestMatchers("/api/empresa/**").hasRole("ADMIN")
                
                .requestMatchers("/api/vaga/listar-disponiveis").hasAnyRole("ADMIN", "ESTUDANTE")
                .requestMatchers("/api/vaga/obter/**").hasAnyRole("EMPRESA", "ESTUDANTE")
                .requestMatchers("/api/vaga/**").hasRole("EMPRESA")
                
                .requestMatchers("/api/inscricao/listar-empresa").hasRole("EMPRESA")
                .requestMatchers("/api/inscricao/**").hasRole("ESTUDANTE")
                
                .requestMatchers("/api/avaliar-empresa/**").hasRole("ESTUDANTE")
                .anyRequest().authenticated()
            )
            .exceptionHandling(exception -> exception
                    .accessDeniedHandler(customAccessDeniedHandler()))
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(403);
            response.setContentType("application/json");
            response.getWriter().write("Você não tem permissão para acessar este recurso.");
        };
    }
}