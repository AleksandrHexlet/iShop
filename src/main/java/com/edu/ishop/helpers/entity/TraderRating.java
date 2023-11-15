package com.edu.ishop.helpers.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TraderRating extends IdData {


    private String productTraderName;
    private String comments;
    private int traderRate;

    public TraderRating(String productTraderName, String comments, int traderRate) {
        this.productTraderName = productTraderName;
        this.comments = comments;
        this.traderRate = traderRate;
    }

    public String getProductTraderName() {
        return productTraderName;
    }

    public void setProductTraderName(String productTraderName) {
        this.productTraderName = productTraderName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getTraderRate() {
        return traderRate;
    }

    public void setTraderRate(int traderRate) {
        this.traderRate = traderRate;
    }
// математика расчёта следующая ---> храни в продукт трейде общую сумму всех оценок и отдельно количество оценок.
    // потом общую сумму дели на общее количество оценок
    // общая сумма всех оценок 500_000 и общее количество оценок 200_000
    // 500_000/200_000
    //Пришла новая оценка 4
//    500_000 + 4 = 500_004 храни в продукт трейде
//    200_000 + 1 = 200_001 храни в продукт трейде
//    итоговый рейтинг ===  500_004 / 200_001 =  храни в продукт трейде и добавь потом в кволити в трейде
//    если пришли плохие слова при высокой оценке( оценка больше 4) тогда  оценку снижаем на один балл

}
