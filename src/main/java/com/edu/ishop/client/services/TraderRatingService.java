package com.edu.ishop.client.services;

import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.entity.ProductTrader;
import com.edu.ishop.helpers.entity.TraderRating;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.ProductTraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TraderRatingService {

    ProductTraderRepository productTraderRepository;

    @Autowired

    public TraderRatingService(ProductTraderRepository productTraderRepository) {
        this.productTraderRepository = productTraderRepository;
    }


    public void calculateTraderQualityIndex (TraderRating traderRating)  {
        String[] wordsMarkers=new String[]{"Плох","плох","Опоздал","опоздал","Задерж","задерж","Обман","обман"};
        ProductTrader productTraderFromDB = productTraderRepository.findById(traderRating.getProductTraderName()).orElse(null);
        // не смог найти продавца с помощью findByName и приходится передавать ID продавца
        // в контроллере при передаче TraderRating целиком выдавал ошибку. Пришлось делить на отдельные поля
        //Если продавца не существует, надо выбрасывать exception ?
        //  if(productTraderFromDB == null)throw  new UsernameNotFoundException("Продавца с таким именем не существует");
        if(productTraderFromDB == null) return;

        int totalCountAllRatings =   productTraderFromDB.getTotalCountAllRatings() + 1;
        double traderRate =  traderRating.getTraderRate();


        for (int i = 0; i < wordsMarkers.length; i++) {
            System.out.println("sout traderRate old == " + traderRate + " ; i == "+ i);
            if(traderRating.getComments().contains(wordsMarkers[i]) && traderRating.getTraderRate() > 3) {
                traderRate = traderRate -1;
                System.out.println("sout traderRate new after change == " + traderRate + " ; i == "+ i);
                break;
            }
        }
        double totalSumAllRatings =   productTraderFromDB.getTotalSumAllRatings() + traderRate;
        double scale = Math.pow(10, 2);
        double traderQualityIndex = Math.ceil((totalSumAllRatings/totalCountAllRatings)*scale)/scale;

        productTraderFromDB.setTraderQualityIndex(traderQualityIndex);
        productTraderFromDB.setTotalCountAllRatings(totalCountAllRatings);
        productTraderFromDB.setTotalSumAllRatings(totalSumAllRatings);
        productTraderRepository.save(productTraderFromDB);


    }
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
