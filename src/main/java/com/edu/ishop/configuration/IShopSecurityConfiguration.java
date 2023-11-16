package com.edu.ishop.configuration;

import com.edu.ishop.admin.services.AdminDetailsService;
import com.edu.ishop.client.services.CustomerDetailsService;
import com.edu.ishop.client.services.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
public class IShopSecurityConfiguration {
    private AdminDetailsService loginDataDetailService;
    private CustomerDetailsService customerDetailsService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public IShopSecurityConfiguration(AdminDetailsService loginDataDetailService,
                                      CustomerDetailsService customerDetailsService,
                                      JwtAuthenticationFilter jwtAuthenticationFilter
//                           @Qualifier(value = "Vasya") DaoAuthenticationProvider Vasya123
    ) {
        this.loginDataDetailService = loginDataDetailService;
        this.customerDetailsService = customerDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean("loginFormSecurityConfigurationEncoderPassword")
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


    @Bean
    @Order(1)
    public SecurityFilterChain filterChainCustomer(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProviderCustomer())
                .securityMatcher("/api/customer/**")
                .authorizeHttpRequests((authorize) -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers("/api/customer/registration", "/api/customer/authorization").permitAll()
                        .requestMatchers("/api/customer/**").hasRole("CUSTOMER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChainTraderApi(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/trader/v1/**")
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/trader/v1/**").hasAnyAuthority("SCOPE_read")/*.hasRole("TRADER")*/
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                ).build();
    }



    @Bean
    @Order(3)
    public SecurityFilterChain filterChainAdmin(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProviderAdmin())
                .securityMatcher("/adminHTML/**")
                .authenticationProvider(authenticationProviderAdmin())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/adminHTML/login").permitAll()
                        .requestMatchers("/adminHTML/**").hasAnyRole("READONLY_ADMIN", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .usernameParameter("username").passwordParameter("password")
                        .loginPage("/adminHTML/login").failureUrl("/adminHTML/login?failed")
                        .loginProcessingUrl("/adminHTML/login").defaultSuccessUrl("/adminHTML/account").permitAll())
                .logout(logout -> logout.logoutUrl("/adminHTML/logout")
                        .logoutSuccessUrl("/adminHTML/login").permitAll());
        return http.build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain filterChainProduct(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                //.securityMatcher("/product/**")
                .authorizeHttpRequests((authorize) -> authorize
//                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/api/trader/registration"/*, "/api/trader/authorization"*/).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }


//    @Bean("Vasya")
    @Bean
    public AuthenticationProvider authenticationProviderCustomer() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //    @Bean("Petya")
    public AuthenticationProvider authenticationProviderAdmin() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginDataDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//    AdminDetailsService

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  {
        try {
            AuthenticationManager manager = config.getAuthenticationManager();
            System.out.println(manager);
            return manager;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

//  http://localhost:9090/oauth2/authorize?client_id=$2a$10$XPItnalALTnwWei0WTnlAulpmP2RatO0REzG9m/QjgwgdZFstfmv.&response_type=code&redirect_uri=http://app.ru&scope=openid%20read