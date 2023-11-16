package com.edu.ishop.client.services;

import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.entity.Role;
import com.edu.ishop.helpers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class CustomerDetailsService implements UserDetailsService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Customer customer =  customerRepository
               .findByUserNameAndRoleRoleTypeIn(username, EnumSet.of(Role.RoleType.ROLE_CUSTOMER)).orElseThrow(()-> new UsernameNotFoundException("Пользователь с таким именем не зарегистрирован"));

        return new CustomerUserDetails(customer);
    }
}
