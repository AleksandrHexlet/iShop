package com.edu.ishop.helpers.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Rate {
//    @Id
//    @GeneratedValue
//    private int id;
    @Id
    private String name;
    private boolean storage;
    private boolean packaging;
    private boolean delivery;
    private int costPlacingAdsAsPercentage;

    public Rate() {
    }

    public Rate(String name, boolean storage, boolean packaging, boolean delivery, int costPlacingAdsAsPercentage) {

        this.name = name;
        this.storage = storage;
        this.packaging = packaging;
        this.delivery = delivery;
        this.costPlacingAdsAsPercentage = costPlacingAdsAsPercentage;
    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public boolean isStorage() {
//        return storage;
//    }
//
//    public void setStorage(boolean storage) {
//        this.storage = storage;
//    }
//
//    public boolean isPackaging() {
//        return packaging;
//    }
//
//    public void setPackaging(boolean packaging) {
//        this.packaging = packaging;
//    }
//
//    public boolean isDelivery() {
//        return delivery;
//    }
//
//    public void setDelivery(boolean delivery) {
//        this.delivery = delivery;
//    }
//
//    public int getCostPlacingAdsAsPercentage() {
//        return costPlacingAdsAsPercentage;
//    }
//
//    public void setCostPlacingAdsAsPercentage(int costPlacingAdsAsPercentage) {
//        this.costPlacingAdsAsPercentage = costPlacingAdsAsPercentage;
//    }
}


//    Чтобы не забылось. Добавить сущность Тариф со свойствами: идентификатор, название, процент за
//    размещение объявления, процент за рекламу. И ещё каких нибудь можно придумать
//    (в зависимости от функционала площадки).