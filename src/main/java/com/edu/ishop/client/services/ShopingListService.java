package com.edu.ishop.client.services;


import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.entity.ShopingList;
import com.edu.ishop.helpers.repository.ShopingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
@Service
public class ShopingListService {

    ShopingListRepository shopingListRepository;
    Map<String, Map<BigDecimal, Product>> productListMap = new HashMap<String, Map<BigDecimal, Product>>(); // тут ключи названия продукта и мапа с ценой и продуктом

    @Autowired
    public ShopingListService(ShopingListRepository shopingListRepository) {
        this.shopingListRepository = shopingListRepository;

    }

    public List<ShopingList> createShopingList(int buget, String shopingList) {
        String[] thingList = shopingList.split(",");
        sortingAndPrepareShopingList(buget, shopingList, thingList);
        BigDecimal minPrice = new BigDecimal("999_999_999.1");
        ;
        BigDecimal middlePrice = new BigDecimal("0.1");
        ;
        BigDecimal maxPrice = new BigDecimal("0.1");
        BigDecimal devider = maxPrice.divide(new BigDecimal("3.0"), 2, RoundingMode.HALF_UP);
//        List<BigDecimal> decimalsListForSortOrder; // набор цен, которые служат ключами в мапе
        String[] productListMapKey = (String[]) productListMap.keySet().toArray(); // набор названий продуктов которые
// являются ключами в productListMap.
        for (int i = 0; i < productListMap.keySet().size(); i++) {

            BigDecimal[] productPrice = (BigDecimal[]) productListMap.get(productListMapKey[i]).keySet().toArray(); // массив с ценами, которые служат ключами в мапе
            if (productPrice[i].compareTo(minPrice) == -1) { // цена продукта меньше minPrice
                minPrice = productPrice[i];
            } else if (productPrice[i].compareTo(maxPrice) == 1) { // цена продукта выше maxPrice
                maxPrice = productPrice[i];
            } else if ((productPrice[i].compareTo(devider) == 1) && (productPrice[i].compareTo(maxPrice.subtract(minPrice)) == -1)) {
// цена продукта выше maxPrice/3 и ниже maxPrice - minPrice
                middlePrice = productPrice[i];
            }

        }
        return addingProductsTolist(minPrice, middlePrice, maxPrice, thingList, buget);
    }

    ;

//  формируй List продуктов из самых дешевых, средних и дорогих и потом доставай цену product.getPrice
// складывай цену всех товаров и проверяй что суммарная
//        // стоимость всей продуктов  меньше бюджета, если больше тогда уберай один продукт и снова проверяй,
//        // пока цена не будет меньше бюджета и далее возвращай лист продуктов

    private void sortingAndPrepareShopingList(int buget, String shopingList, String[] thingList) {

        Map<BigDecimal, Product> productsMapWithPrice = new HashMap<BigDecimal, Product>();//в мапу пишем все товары из БД и в качестве ключей цена товара
        for (int i = 0; i < thingList.length - 1; i++) {
// нахожу все товары стоимостью ниже бюджета по каждому товарному наименованию отдельно
            List<Product> productListFromDB = shopingListRepository.findByPriceLessThanAndNameProductIn(BigDecimal.valueOf(buget), thingList[i]);
// складываю в мапу в качестве ключа стоимость подукции и в качестве значения сам продукт
//        productListFromDB.stream().forEach((product)->(productsMapWithPrice.put(product.getPrice(),product)));

            for (int j = 0; j < productListFromDB.size(); j++) {

                Product product = productListFromDB.get(j);
                if (productsMapWithPrice.get(product.getPrice()) == null) {
// если такого товара в мапе нет. тогда записываем
                    productsMapWithPrice.put(product.getPrice(), product);
                    } else {
                        if (productsMapWithPrice.get(product.getPrice()).getProductTrader().getTraderQualityIndex() < product.getProductTrader().getTraderQualityIndex()) {
// два товара с одинаковой ценой.Если в мапе рейтинг продавца ниже, чем аналогичного продукта с такой же ценой из БД, тогда перезаписываем новый продукт в мапу на метсо старого
                            productsMapWithPrice.put(product.getPrice(),product);

                        }
                    }
                }

                productListMap.put(thingList[i],productsMapWithPrice);
            }
// на этапе складывания товаров в мапу, если в мапе такой ключ есть(цена), тогда доставай этот товар из мапы и
// проверяй у какого продукта (тот, что в мапе или тот который хотим положить) выше рейтинг продавца и ложи тот товар
// у которого рейтинг продавца выше
        }

        private List<ShopingList> addingProductsTolist(BigDecimal minPrice,BigDecimal middlePrice,BigDecimal maxPrice,String[]thingList,int buget){
            BigDecimal[]listPrice={minPrice,middlePrice,maxPrice};
            String[]nameList={"minPrice","middlePrice","maxPrice"};
            ShopingList minPriceList=new ShopingList();
            ShopingList middlePriceList=new ShopingList();
            ShopingList maxPriceList=new ShopingList();
            List<ShopingList> shopingLists=new ArrayList<ShopingList>();
            minPriceList.setName("minPriceList");
            middlePriceList.setName("middlePriceList");
            maxPriceList.setName("maxPriceList");
//        for (int i = 0; i < listPrice.length; i++) {
//            String name = nameList[i];
            for(int i=0;i<thingList.length-1;i++){ // из товаров с одним именем но разной ценой
// беру самые дешёвые, средние и дорогие. Например: Лыжи за 100р, 500р, 900р

                Map<BigDecimal, Product> productMap=productListMap.get(thingList[i]); // достали мапу со всеми
// ценами на одну позицию продуктом из списка желаемых вещей

                if(productMap.get(minPrice).getPrice().compareTo(new BigDecimal(buget))==-1)break; // если цена товара
// выше бюджета, тогда выходим из итерации

                if((minPriceList.getCostAllItemsInList().compareTo(new BigDecimal(buget).subtract(minPrice))==-1)){// перед добавлением проверяем
// чтобы сумма всех товаров в списке была ниже (бюджет минус цена товара который добавляем)
//                если список пуст, тогда верни об этом сообщение пользователю
                    minPriceList.getProductList().add(productListMap.get(thingList[i]).get(minPrice)); // взял по имени продукта
// вложеннуй мапу с ценой и продуктом и из этой мапы по стоимости достал сам продукт и записал его в список из ShopingList
                    minPriceList.setCostAllItemsInList(productMap.get(minPrice).getPrice()); // добавляем стоимость товара в общую стоимость всего списка товаров
                }

                if((middlePriceList.getCostAllItemsInList().compareTo(new BigDecimal(buget).subtract(middlePrice))==-1)){
                    middlePriceList.getProductList().add(productListMap.get(thingList[i]).get(middlePrice)); // взял по имени продукта
// вложеннуй мапу с ценой и продуктом и из этой мапы по стоимости достал сам продукт и записал его в список из ShopingList
                    middlePriceList.setCostAllItemsInList(productMap.get(middlePrice).getPrice());
                }

                if((maxPriceList.getCostAllItemsInList().compareTo(new BigDecimal(buget).subtract(maxPrice))==-1)){
                    maxPriceList.getProductList().add(productListMap.get(thingList[i]).get(maxPrice));
                    maxPriceList.setCostAllItemsInList(productMap.get(maxPriceList).getPrice());
                }
            }

            shopingLists.add(minPriceList);
            shopingLists.add(middlePriceList);
            shopingLists.add(maxPriceList);

            return shopingLists;
        }
    }
