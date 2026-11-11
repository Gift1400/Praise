package za.ac.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/events/**",
                                "/api/announcements/**",
                                "/api/sermon/**",
                                "/api/bibleVerse/**",
                                "/api/churchSite/**",
                                "/api/prayerRequest/**",
                                "/api/testimony/**",
                                "/api/outreachProgram/**",
                                "/api/leader/**",
                                "/api/rsvp/getByEvent/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/prayerRequest/create",
                                "/api/testimony/create",
                                "/api/donations/create",
                                "/api/rsvp/create"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/events/**",
                                "/api/announcements/**",
                                "/api/sermon/**",
                                "/api/outreachProgram/**",
                                "/api/bibleVerse/**"
                        ).hasAnyRole("LEADER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/events/**",
                                "/api/announcements/**",
                                "/api/sermon/**",
                                "/api/outreachProgram/**",
                                "/api/bibleVerse/**"
                        ).hasAnyRole("LEADER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/events/**",
                                "/api/announcements/**",
                                "/api/sermon/**",
                                "/api/outreachProgram/**",
                                "/api/bibleVerse/**",
                                "/api/prayerRequest/**",
                                "/api/testimony/**"
                        ).hasAnyRole("LEADER", "ADMIN")
                        .requestMatchers("/api/member/create", "/api/member/getAll").hasRole("ADMIN")
                        .requestMatchers("/api/donations/total", "/api/donations/getAll").hasAnyRole("LEADER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
