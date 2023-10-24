package com.edu.ishop.client.services;

import com.edu.ishop.admin.services.CustomerUserDetails;
import com.edu.ishop.admin.services.JwtSecurityService;
import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.entity.Role;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.CustomerRepository;
import com.edu.ishop.helpers.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtSecurityService jwtSecurityService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           JwtSecurityService jwtSecurityService,
                           AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtSecurityService = jwtSecurityService;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public CommandLineRunner createRoleCustomer() {
        return (args) -> {
            Role roleCustomer = new Role();
            roleCustomer.setRoleType(Role.RoleType.ROLE_CUSTOMER);
            roleRepository.save(roleCustomer);
        };
    }

    public void customerRegistration(Customer customer) throws ResponseException {
        if (customerRepository.existsByUserName(customer.getUserName()))
            throw new ResponseException("Пользователь с таким именем уже зарегистрирован");
        Role role = roleRepository.findByRoleType(Role.RoleType.ROLE_CUSTOMER);
        customer.setRole(role);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    public String customerAuthorization(String userName, String password) {
        Customer customer = customerRepository.findByUserName(userName).orElseThrow(() ->
                new UsernameNotFoundException("Такого пользователя не существует"));
        CustomerUserDetails customerUserDetails = new CustomerUserDetails(customer);
        String tokenGenerated = jwtSecurityService.generateToken(customerUserDetails);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenGenerated;
    }
}
