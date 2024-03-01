package pers.bohan.statelessauthenticationsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pers.bohan.statelessauthenticationsystem.security.jwt.AuthEntryPointJwt;
import pers.bohan.statelessauthenticationsystem.security.jwt.AuthTokenFilter;
import pers.bohan.statelessauthenticationsystem.security.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .oauth2Login(
                        oauth2Login -> oauth2Login
                                .loginPage("/oauth2/login")
                                .defaultSuccessUrl("/oauth2/success", true)
                )
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
                                .requestMatchers("/oauth2/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/news/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/news/**").hasRole("admin")
                                .requestMatchers(HttpMethod.DELETE, "/news/**").hasRole("admin")
                                .requestMatchers(HttpMethod.GET, "/comments/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/comments/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/comments/**").hasRole("admin")
                                .requestMatchers(HttpMethod.GET, "/ad**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/ad**").hasRole("admin")
                                .requestMatchers(HttpMethod.DELETE, "/ad**").hasRole("admin")
                                .requestMatchers("/api/admin/**").hasRole("admin")
                                .requestMatchers("/actuator/**").permitAll() // 允许无授权访问健康检查端点
                                .requestMatchers("/instances/**").permitAll() // 允许无授权访问健康检查端点
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
