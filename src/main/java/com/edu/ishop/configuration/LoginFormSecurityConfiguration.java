package com.edu.ishop.configuration;

import com.edu.ishop.admin.services.CustomerDetailsService;
import com.edu.ishop.admin.services.JwtAuthenticationFilter;
import com.edu.ishop.admin.services.LoginDataDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;


@Configuration
@EnableWebSecurity //  для авторизации https
@EnableMethodSecurity(securedEnabled = true)
// для указания каким ролям доступен вызов конкретного  метода благодаря аннотации Secured в контроллерах
public class LoginFormSecurityConfiguration {
    private LoginDataDetailsService loginDataDetailService;
    private CustomerDetailsService customerDetailsService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    public LoginFormSecurityConfiguration(LoginDataDetailsService loginDataDetailService,
                                          CustomerDetailsService customerDetailsService,
                                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.loginDataDetailService = loginDataDetailService;
        this.customerDetailsService = customerDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
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
                                .requestMatchers("/endpoint", "/endpoint2").permitAll()
                                .requestMatchers("/adminHTML/**").hasAnyRole("READONLY_ADMIN", "ADMIN")
//                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .usernameParameter("username").passwordParameter("password")
                        .loginPage("/adminHTML/login").failureUrl("/adminHTML/login?failed")
                        .loginProcessingUrl("/adminHTML/login").defaultSuccessUrl("/adminHTML/account").permitAll())
                .logout(logout -> logout.logoutUrl("/adminHTML/logout")
                        .logoutSuccessUrl("/adminHTML/login").permitAll())
                .authorizeHttpRequests((authorize) -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers("/api/customer/registration", "/api/customer/authorization").permitAll()
                        .requestMatchers("/api/customer/**").hasRole("CUSTOMER")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}