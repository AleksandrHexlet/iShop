package com.edu.ishop.admin.services;

import com.edu.ishop.helpers.entity.LoginData;
import com.edu.ishop.helpers.entity.Role;
import com.edu.ishop.helpers.repository.LoginDataRepository;
import com.edu.ishop.helpers.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

@Service
public class AdminDetailsService implements UserDetailsService {
    private LoginDataRepository<LoginData> loginDataRepository;
    private RoleRepository roleRepository;


    @Autowired
    public AdminDetailsService(LoginDataRepository<LoginData> loginDataRepository, RoleRepository repository) {
        this.loginDataRepository = loginDataRepository;
        this.roleRepository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginData loginData = loginDataRepository
                .findByUserNameAndRoleRoleTypeIn(username, EnumSet.of(Role.RoleType.ROLE_ADMIN, Role.RoleType.ROLE_READONLY_ADMIN))
                .orElseThrow(() -> new UsernameNotFoundException(String
                        .format("Пользователь с именем %s не зарегистрирован", username)));


        GrantedAuthority authority = new SimpleGrantedAuthority(loginData.getRole().getRoleType()
                .name());
        return new User(username, loginData.getPassword(), Set.of(authority));

    }

    @Bean
    public CommandLineRunner createTableLoginDataDetailsService() {
        return (args) -> {

            Role roleAdmin = new Role();
            roleAdmin.setRoleType(Role.RoleType.ROLE_ADMIN);

            Role roleReadOnly = new Role();
            roleReadOnly.setRoleType(Role.RoleType.ROLE_READONLY_ADMIN);

            roleRepository.save(roleAdmin);
            roleRepository.save(roleReadOnly);
            LoginData loginDataAdmin = new LoginData();
            loginDataAdmin.setRole(roleAdmin);
            loginDataAdmin.setUserName("admin");
            loginDataAdmin.setPassword("$2a$10$7oCTGflP2kNI3WP41FV2IOFyXVh9beW6e9ywgsew3/rmIOxoEq/LW");

            LoginData loginDataReadOnlyAdmin = new LoginData();
            loginDataReadOnlyAdmin.setRole(roleReadOnly);
            loginDataReadOnlyAdmin.setUserName("readOnlyAdmin");
            loginDataReadOnlyAdmin.setPassword("$2a$10$fnDj5PUIC0rWC1otWLxHbeRXK8Plfh1oHGriPC6QBI5cP99Tb3wTq");
//
            loginDataRepository.save(loginDataAdmin);
            loginDataRepository.save(loginDataReadOnlyAdmin);

        };
    }
}
