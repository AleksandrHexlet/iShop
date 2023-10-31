package com.edu.ishop.client.services;

import com.edu.ishop.helpers.entity.ProductTrader;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.ProductTraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TraderService {
    private ProductTraderRepository productTraderRepository;

    @Autowired
    public TraderService(ProductTraderRepository productTraderRepository) {
        this.productTraderRepository = productTraderRepository;
    }

    public void traderRegistration(ProductTrader productTrader) throws ResponseException {
        if (productTraderRepository.existsByUserName(productTrader.getUserName()))
            throw new ResponseException("Такой продавец уже зарегистрирован");
        productTrader.setDateRegistration(LocalDate.now());
        productTraderRepository.save(productTrader);
    }
}
