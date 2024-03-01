package pers.bohan.springbootadmin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 安全配置类
 *
 * @author chuan
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final String adminContextPath;

    public WebSecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setTargetUrlParameter("redirectTo");
        handler.setDefaultTargetUrl(this.adminContextPath + "/");

        http.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated())
                .httpBasic(withDefaults()) // 启用HTTP基本认证
                .formLogin(withDefaults()); // 启用表单登录
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}


