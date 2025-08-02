package kh.edu.cstad.mbapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class KeycloakSecurityConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> converter = jwt -> {

            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            Collection<String> authorities = realmAccess.get("roles");
            return authorities.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
        };
        JwtAuthenticationConverter jwtGrantedAuthoritiesConverter = new JwtAuthenticationConverter();
        jwtGrantedAuthoritiesConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtGrantedAuthoritiesConverter;
    }

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request ->
                request
                        .requestMatchers(HttpMethod.GET, "/api/v1/customers/**").hasAnyRole("ADMIN", "STAFF", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/customers/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/customers/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/v1/accounts/**").hasAnyRole("USER")
                        .requestMatchers("/media/**").permitAll()
                        .anyRequest().authenticated()
        );

        http.oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));

//        disable form login
        http.formLogin(AbstractHttpConfigurer::disable);

//        disable csrf
        http.csrf(AbstractHttpConfigurer::disable);
//        change to STATELESS
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
