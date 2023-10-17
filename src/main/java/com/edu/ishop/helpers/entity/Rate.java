package com.edu.ishop.helpers.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Rate {
    @Id
    @GeneratedValue
    private int id;
    private String name;


//    tariff for using the platforms
}


//    Чтобы не забылось. Добавить сущность Тариф со свойствами: идентификатор, название, процент за
//    размещение объявления, процент за рекламу. И ещё каких нибудь можно придумать
//    (в зависимости от функционала площадки).