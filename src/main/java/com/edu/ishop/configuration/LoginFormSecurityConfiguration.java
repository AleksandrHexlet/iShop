package com.edu.ishop.configuration;

import com.edu.ishop.admin.services.LoginDataDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;


@Configuration
@EnableWebSecurity //  для авторизации https
@EnableMethodSecurity(securedEnabled = true)
// для указания каким ролям доступен вызов конкретного  метода благодаря аннотации Secured в контроллерах
public class LoginFormSecurityConfiguration {
    private LoginDataDetailsService loginDataDetailService;

    @Autowired
    public LoginFormSecurityConfiguration(LoginDataDetailsService loginDataDetailService) {
        this.loginDataDetailService = loginDataDetailService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers("/endpoint", "/endpoint1", "/endpoint2").permitAll()
                        .requestMatchers("/adminHTML/**").hasAnyRole("READONLY_ADMIN", "ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .usernameParameter("username").passwordParameter("password")
                        .loginPage("/adminHTML/login").failureUrl("/adminHTML/login?failed")
                        .loginProcessingUrl("/adminHTML/login").defaultSuccessUrl("/adminHTML/account").permitAll())
                .logout(logout -> logout.logoutUrl("/adminHTML/logout")
                        .logoutSuccessUrl("/adminHTML/login").permitAll());
        return http.build();
    }
}