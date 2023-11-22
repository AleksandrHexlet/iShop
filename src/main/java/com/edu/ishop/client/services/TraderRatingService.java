package com.edu.ishop.client.services;

import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.entity.ProductTrader;
import com.edu.ishop.helpers.entity.TraderRating;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.ProductCustomerOrderRepository;
import com.edu.ishop.helpers.repository.ProductRepository;
import com.edu.ishop.helpers.repository.ProductTraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class TraderRatingService {

    ProductTraderRepository productTraderRepository;
    ProductCustomerOrderRepository productCustomerOrderRepository;

    @Autowired

    public TraderRatingService(ProductTraderRepository productTraderRepository, ProductCustomerOrderRepository productCustomerOrderRepository) {
        this.productCustomerOrderRepository = productCustomerOrderRepository;
        this.productTraderRepository = productTraderRepository;


    }

    // отдельный файл для потока и тут метод пометить как доступный для потока
    @Async("taskExecutor")
    public void calculateTraderQualityIndex(TraderRating traderRating,int idProduct) {

            String[] wordsMarkers = new String[]{"Плох", "плох", "Опоздал", "опоздал", "Задерж", "задерж", "Обман", "обман"};
            ProductTrader productTraderFromDB = productTraderRepository.findByUserName(traderRating.getProductTraderName()).orElse(null);


            if (productTraderFromDB == null) return;
            if (!productCustomerOrderRepository.existsByProductIdAndProductTraderUserName(idProduct,
                    traderRating.getProductTraderName())) return;

            int totalCountAllRatings = productTraderFromDB.getTotalCountAllRatings() + 1;
            double traderRate = traderRating.getTraderRate();

            for (int i = 0; i < wordsMarkers.length; i++) {
                if (traderRating.getComments().contains(wordsMarkers[i]) && traderRating.getTraderRate() > 3) {
                    traderRate = traderRate - 1;
                    break;
                }
            }
            double totalSumAllRatings = productTraderFromDB.getTotalSumAllRatings() + traderRate;
            double scale = Math.pow(10, 2);
            double traderQualityIndex = Math.ceil((totalSumAllRatings / totalCountAllRatings) * scale) / scale;

            productTraderFromDB.setTraderQualityIndex(traderQualityIndex);
            productTraderFromDB.setTotalCountAllRatings(totalCountAllRatings);
            productTraderFromDB.setTotalSumAllRatings(totalSumAllRatings);
            productTraderRepository.save(productTraderFromDB);


            // от клиента приходи бюджет( ) и названия товаров сервер возращает несколько
        // списков(2-3) какие товары он может купить на этот бюджет.
        // Пришла 1 тыс рублей и название товаров(лыжи, ботинки и шлем) Например: лыжи за
        // 500 р + ботинки за 300 + шлем за 200 рублей или только  лыжи за
        // 500 р + ботинки за 500. Если нет ни одного товара значит пишет, что
        // бюджет маленький и нет подходящих товаров. Либо только что-то одно может купить например
        // только лыжи за 1 тысячу
        // Список выглядит только по одному продавцу на каждую позицию и только по одному экземпляру товара.
        // Двое лыж нельзя. Если у 2 продавцов одинаковый товар и одинаковая стоимость,
        // тогда предлагай того у кого рейтинг выше.Если рейтинг одинаковый тогда у того у кого тариф.
        // Если тариф одинаковый тогда рандомно определяй кого
        // Каждый из вариантов покупок это лист с возможными товарами.
        // Реализация создай продукты в БД (товары с одинаковыми названиями от разных продавцов),
        // потом написать метод в репозитории(findByPriceLessThanAndNameProductIn) поиска товара и стоимостью
        // ниже чем указано в методе.
        // List<Product> findByPriceLessThanAndNameProductIn(BigDecimal price, Collection<String> names);
        // Отсортировать лист по производителю и потом внутри одного производителя брать каждый товар по одному и
        // самые дешевые. Если самые дешевые не укладываются в бюджет тогда один товар выкинули. Если все уложились и бюджет остался, тогда смотри товары
        // более дорогие товары. Берём один товар и смотрим его с более дорогой стоимостью, чтобы уложиться.
        // Если перебор по цене, тогда ищем этот товар но более дешевый. Если наша покупка меньше бюджета
        // менее чем на 10 процентов, тогда это ОК и мы возвращаем список товаров.
        // В каждом списке могут быть товары и одного производителя и разных производителей

        // почитай патерны микросервисной архитектуры. Какие есть подходы. Если есть сервис авторизации
        // тогда куда приходят запросы. Как сервисы общаются между.
        // GateAway . Как устроено взаимодействие. Как между собой общаются микросервисы на моменте авторизации.
        // Либо каждый сервис ходит за авторизацией либо мы делаем маршрутизатор
        // и только он ходит на сервис автторизации. Посмотри плюсы и минусы обоих подходов.
        // Как выделяются микросервисы из монолита (выделяем по ресурсам).
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
