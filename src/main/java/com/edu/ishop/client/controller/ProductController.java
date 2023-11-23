package com.edu.ishop.client.controller;

import com.edu.ishop.client.services.ShopingListService;
import com.edu.ishop.client.services.TraderRatingService;
import com.edu.ishop.helpers.entity.FeedBack;
import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.entity.ShopingList;
import com.edu.ishop.helpers.entity.TraderRating;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.client.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/product")

public class ProductController {
    private ProductService productService;
    private TraderRatingService traderRatingService;
    private ShopingListService shopingListService;

    @Autowired
    public ProductController(ProductService productService, TraderRatingService traderRatingService,
                             ShopingListService shopingListService) {
        this.productService = productService;
        this.traderRatingService = traderRatingService;
        this.shopingListService = shopingListService;
    }

    @GetMapping("/category/{urlCategory}")
    public List<Product> getProductsByCategoryName(@PathVariable String urlCategory,
                                                   @RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam(required = false) int quantity,
                                                   @RequestParam(required = false) double rating,
                                                   @RequestParam(required = false) String namesManufacturer,
                                                   @RequestParam(required = false) String orderSort) {


        List<String> namesManufacturerArr = new ArrayList<>();
        if (namesManufacturer.length() > 0) {
            namesManufacturerArr = List.of(namesManufacturer.split(","));
        }

//        return productService.getProductList(urlCategory);
        return productService.getProductByCategoryAndQuantityAndRatingAndManufacturerName(urlCategory,
                quantity, rating,
                namesManufacturerArr,
                page, size, orderSort);

    }
////1. GET:: /product/category/{categoryUrl} - постраничное получение всех товаров определенной категории
//// с возможностью сортировки по возрастанию / убыванию цены или новизне (отзывы не нужны).
////        Номер страницы, количество записей и порядок сортировки передаются параметрами и
////        являются обязательными.Добавить фильтрацию по доступному количеству (больше или равно
////        указанному), по рейтингу (больше или равно указанному),
////        по производителю (один или несколько). Количество, рейтинг, производитель передаются
////        параметрами и не являются обязательными.


    @GetMapping
    public List<Product> getProductsByRandomCategoryAndRating() {
        return productService.getProductsByRandomCategoryAndRating();
    }

    @GetMapping("/by")
    public List<Product> getProductsByNames(@RequestParam String names) {
        String[] nameArr = names.split("-");
        return productService.getProductListByName(nameArr);
    }

    @PostMapping("/feedback")
    public ResponseEntity<FeedBack> getProductsByNames(@RequestParam int idProduct,
                                                       @RequestParam String textFeedback,
                                                       @RequestParam String productTraderName,
                                                       @RequestParam String comments,
                                                       @RequestParam int traderRate,
                                                       @RequestParam int customerId
//    @RequestParam TraderRating traderRating
    ) {

        try {
            FeedBack feedBack = productService.addFeedBackToProduct(idProduct, textFeedback, customerId);
            TraderRating traderRating = new TraderRating(productTraderName, comments, traderRate);
            traderRatingService.calculateTraderQualityIndex(traderRating, idProduct);

            return new ResponseEntity<FeedBack>(feedBack, HttpStatus.CREATED);
        } catch (ResponseException responseException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Передан Id продукта или Id покупателя которого нет");
        }
    };

    @GetMapping("/id/{productId}")
    public Product getProductWithFeedbacksById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    ;


    @GetMapping("/filter")
    public List<Product> getProductByProductManufacturerAndQuantityStockAndRating(@RequestParam String country,
                                                                                  @RequestParam String name,
                                                                                  @RequestParam int quantity,
                                                                                  @RequestParam double rating) {

        try {
            return productService.getProductByProductManufacturerAndQuantityStockAndRating(country, name, quantity, rating);
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/name")
    public List<Product> getProductByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    ;

    @PostMapping("/uploadExcel")
    public List<String> uploadProducts(@RequestParam MultipartFile file, @RequestParam String userNameTrader) {
        try {
            return productService.uploadProducts(file, userNameTrader);
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }

    }

    @PostMapping("/shopingList")
    public List<ShopingList> getShopingList(@RequestParam int buget, @RequestParam String shopingList){
        List<ShopingList> listShoping =  shopingListService.createShopingList( buget, shopingList);
//      try{
//
//      } catch{
//
//        }
        return listShoping;
    }


}

//getProductByCategoryAndQuantityAndRatingAndManufacturerName

///product?name=bread&image=breadImg