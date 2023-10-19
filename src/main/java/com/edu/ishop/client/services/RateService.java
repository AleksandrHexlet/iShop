package com.edu.ishop.client.services;

import com.edu.ishop.helpers.entity.Rate;
import com.edu.ishop.helpers.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RateService {
   private RateRepository rateRepository;

   @Autowired

    public RateService(RateRepository repository) {
        this.rateRepository = repository;
    }

    Rate MaxRate = new Rate("MaxRate",true,true,true,300);
    Rate MiddleRate = new Rate("MiddleRate",true,true,false,200);
    Rate JuniorRate = new Rate("JuniorRate",true,false,false,100);

    @Bean
    public CommandLineRunner createTable(){
        return (args) -> {

            rateRepository.save(MaxRate);
            rateRepository.save(MiddleRate);

            rateRepository.save(JuniorRate);

        };
    }
}

