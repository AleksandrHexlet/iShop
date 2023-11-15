package com.edu.ishop.client.services;

import com.edu.ishop.helpers.entity.LoginData;
import com.edu.ishop.helpers.entity.ProductTrader;
import com.edu.ishop.helpers.entity.Role;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.LoginDataRepository;
import com.edu.ishop.helpers.repository.ProductTraderRepository;
import com.edu.ishop.helpers.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductTraderService {
    private ProductTraderRepository productTraderRepository;
    private LoginDataRepository loginDataRepository;
    private RoleRepository roleRepository;
    private LoginData loginDataTrader = new LoginData();
    private PasswordEncoder encoderPassword;

@Autowired
    public ProductTraderService(ProductTraderRepository productTraderRepository,
                                LoginDataRepository loginDataRepository,
                                RoleRepository roleRepository,
                                PasswordEncoder loginFormSecurityConfigurationEncoderPassword) {
        this.productTraderRepository = productTraderRepository;
        this.loginDataRepository = loginDataRepository;
        this.roleRepository = roleRepository;

        this.encoderPassword = loginFormSecurityConfigurationEncoderPassword;
    }

    public void traderRegistration(ProductTrader productTrader) throws ResponseException {

        if (productTraderRepository.existsByUserName(productTrader.getName()))
            throw new ResponseException("Такой продавец уже зарегистрирован");

        loginDataTrader.setUserName((productTrader
                .getName().trim()).strip());
        loginDataTrader.setPassword(encoderPassword
                .encode(productTrader.getPassword().strip()));
        Role roleTrader = roleRepository.findByRoleType(Role.RoleType.ROLE_TRADER).orElseGet(()->{
            Role traderRole = new Role();
            traderRole.setRoleType(Role.RoleType.ROLE_TRADER);
             Role traderRoleFromDBwithID = roleRepository.save(traderRole);
                return traderRoleFromDBwithID;
        });
        System.out.println("roleTrader === " + roleTrader.getRoleType().name());
        loginDataTrader.setRole(roleTrader);
        loginDataRepository.save(loginDataTrader);
        productTrader.setDateRegistration(LocalDate.now());
        productTraderRepository.save(productTrader);
    }
}
//http://127.0.0.1:9090/oauth2/authorize?response_type=code&client_id=$2a$10$.8ck0l.PVTGuKzrfPPpUBepgPHKWrhR.Un6/JjU/dWuYmfBOC1iTy&redirect_uri=http://localhost:8888/redirect&scope=openid read