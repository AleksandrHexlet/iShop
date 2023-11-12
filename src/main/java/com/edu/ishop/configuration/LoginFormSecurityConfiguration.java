package com.edu.ishop.configuration;

import com.edu.ishop.admin.services.LoginDataDetailsService;
import com.edu.ishop.client.services.CustomerDetailsService;
import com.edu.ishop.client.services.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
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
                                          JwtAuthenticationFilter jwtAuthenticationFilter


//                           @Qualifier(value = "Vasya") DaoAuthenticationProvider Vasya123


    ) {
        this.loginDataDetailService = loginDataDetailService;
        this.customerDetailsService = customerDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }




    @Bean("Vasya")

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    @Order(1)
    public SecurityFilterChain filterChainCustomer(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/customer/**")
                .authorizeHttpRequests((authorize) -> authorize
                                .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                                .requestMatchers("/api/customer/registration", "/api/customer/authorization").permitAll()
                                .requestMatchers("/api/customer/**").hasRole("CUSTOMER")
//                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProviderCustomer())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChainTrader(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/trader/**")
                .authorizeHttpRequests((authorize) -> authorize
                                .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                                .requestMatchers("/api/trader/registration").permitAll()
//                                .requestMatchers("/trader/**").hasAnyRole("TRADER", "ADMIN")
//                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain filterChainAdmin(HttpSecurity http) throws Exception {
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
                        .logoutSuccessUrl("/adminHTML/login").permitAll());
        return http.build();
    }

//    @Bean("Vasya")
    @Bean

    public DaoAuthenticationProvider authenticationProviderCustomer() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//    @Bean("Petya")
//    public DaoAuthenticationProvider authenticationProviderDefault() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(customerDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    LoginDataDetailsService

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}