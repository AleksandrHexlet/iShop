package com.edu.ishop.helpers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@IdClass(ProductManufactureId.class)
@PrimaryKeyJoinColumn(name = "traderId")
public class ProductTrader  extends  LoginData{
    @Size(min = 2, max = 199, message = "Длина имени категории должна быть равен или более 2 символам и менее 199 символов")
    private String name;
    private LocalDate dateRegistration;
    private double storeQualityIndex;
    @PositiveOrZero
//    @DecimalMin(value = "0")
    private BigDecimal traderBill;
    private String rate;

  @ManyToOne
  TraderRating traderRating;
    //    @Id
    private String cityStorage;
    private boolean isActive = true;

    public ProductTrader() {
    }

    public ProductTrader(String name, LocalDate dateRegistration,
                         double storeQualityIndex, BigDecimal traderBill, String rate, String cityStorage,
                         boolean isActive) {
        this.name = name;
        this.dateRegistration = dateRegistration;
        this.storeQualityIndex = storeQualityIndex;
        this.traderBill = traderBill;
        this.rate = rate;
        this.cityStorage = cityStorage;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStoreQualityIndex() {
        return storeQualityIndex;
    }

    public void setStoreQualityIndex(double storeQualityIndex) {
        this.storeQualityIndex = storeQualityIndex;
    }

    public String getCityStorage() {
        return cityStorage;
    }

    public void setCityStorage(String cityStorage) {
        this.cityStorage = cityStorage;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDateRegistration(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
}

// http://127.0.0.1:9090/oauth2/authorize?response_type=code&client_id=$2a$10$dU8slMdM5sMSIDwI75sJeuwgdxr0DtfY28YhL.spiTYKLYTwNxmXK&redirect_uri=http://localhost:8888&scope=openid read