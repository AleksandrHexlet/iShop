package com.edu.ishop.admin.services;

import com.edu.ishop.helpers.HistoryOrder;
import com.edu.ishop.helpers.entity.*;
import com.edu.ishop.helpers.repository.*;
import com.edu.ishop.helpers.exceptions.ResponseException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class AdminService {
    ProductRepositoryAdmin productRepositoryAdmin;
    ProductManufactureRepository productManufactureRepository;
    CategoryRepository categoryRepository;
    CustomerRepositoryAdmin customerRepositoryAdmin;
    ProductCustomerOrderRepository productCustomerOrderRepository;
    @Autowired
    public AdminService(ProductCustomerOrderRepository productCustomerOrderRepository,ProductRepositoryAdmin productRepositoryAdmin, ProductManufactureRepository productManufactureRepository, CategoryRepository categoryRepository, CustomerRepositoryAdmin customerRepositoryAdmin) {
        this.productRepositoryAdmin = productRepositoryAdmin;
        this.productManufactureRepository = productManufactureRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepositoryAdmin = customerRepositoryAdmin;
    }


    public Product createNewProduct(@Valid Product product) throws ResponseException {
        product.setDateAdded(LocalDate.now());
        ProductTrader productTrader = productManufactureRepository
                .findById(product.getProductManufacturer().getUserName()).orElse(null);
        if (productTrader == null || !productTrader.isActive()) {
            throw new ResponseException("Товар не может быть добавлен. Производитель " +
                    "не существует ил не активен");
        }
        Product prod = productRepositoryAdmin.save(product);

        return prod;
    }

    public Customer createNewCustomer(@Valid Customer customer) throws ResponseException {
        Customer customerIsExist = customerRepositoryAdmin
                .getCustomersByUserName(customer.getUserName()).get();
        if (customerIsExist != null) {
            throw new ResponseException("Клиент с таким userName уже существует ");
        }
        return customerRepositoryAdmin.save(customer);


    }

    public Category createNewCategory(@Valid Category category) throws ResponseException {
        Category сategoryIsExist = categoryRepository.findByName(category.getName());
        if (сategoryIsExist != null) {
            throw new ResponseException("Категория с таким именем уже существует ");
        }
        return categoryRepository.save(category);
    }


    public ProductTrader createNewTrader(@Valid ProductTrader productTrader) {
        productTrader.setDateRegistration(LocalDate.now());
        productManufactureRepository.save(productTrader);
        return productTrader;
    }

    public void updateTrader(@Size(min = 1, max = 99,message = "Нарушены требование к длине имени") String userName, String  rate, String cityStorage) throws ResponseException {
        if (userName == null)
            throw new ResponseException("userName должен быть заполнен");
        if ((cityStorage != null && cityStorage.isEmpty() && rate.equals("-1")) || (cityStorage == null && rate.equals("-1")))
            throw new ResponseException("Нужна информация для обновления. Сейчас данные не переданы");
       ProductTrader productTrader =  productManufactureRepository.findById(userName).orElse(null);
       if(productTrader == null) throw new ResponseException("Продавец не зарегистрирован");
        if(productTrader.getCityStorage().equals(cityStorage) && productTrader.getRate() == rate)
            throw new ResponseException("Данные не требуют обновления.Они идентичны сохранённым в БД");
        if(!rate.equals("-1")) productTrader.setRate(rate);
        if(cityStorage != null) productTrader.setCityStorage(cityStorage);
       productManufactureRepository.save(productTrader);
    }

    public  List<HistoryOrder>  getOrderTrader(String username, String typeFilter
//                                                  @FutureOrPresent @PastOrPresent  LocalDate date
//                                                  @Min(0) @Max(99999) @Positive @Negative int something
//                                                  List<@Size(min = 0, max = 56789) String> strList
//                                                  List<@Min(0) @Max(12356789) @PositiveOrZero Integer> intList
//                                                  List<@Valid Product> productList
    ) {
        List<HistoryOrder> historyOrderList = new ArrayList<>();
        List<ProductCustomerOrder> productCustomerOrderList = productCustomerOrderRepository.findByProductTraderUserName(username);
        List<Product> productList = null;
        Map<CustomerOrder, List<Product>> productMap = new HashMap<>();
        Map<CustomerOrder, Integer> customerOrderCountMap  = new HashMap<>();
        for (ProductCustomerOrder productCustomerOrder : productCustomerOrderList) {

            customerOrderCountMap.put(productCustomerOrder.getCustomerOrder(),productCustomerOrder.getCount());

            if(productMap.containsKey(productCustomerOrder.getCustomerOrder())){
                productMap.get(productCustomerOrder.getCustomerOrder() ).add(productCustomerOrder.getProduct());
            } else{
                List<Product> newProductList = new ArrayList<>();
                newProductList.add(productCustomerOrder.getProduct());
                productMap.put(productCustomerOrder.getCustomerOrder(),newProductList );
            }

        }
        for (Map.Entry<CustomerOrder, List<Product>> customerOrderListEntry : productMap.entrySet()) {
            CustomerOrder customerOrder = customerOrderListEntry.getKey();
            int count = customerOrderCountMap.get(customerOrder);
            Double billCustomerOrder = customerOrderListEntry.getValue().stream()
                    .mapToDouble(product -> product.getPrice().doubleValue() * count).sum();


            HistoryOrder historyOrder = new HistoryOrder(customerOrder.getCustomer(),
                    customerOrderListEntry.getValue(), customerOrder.getStatus(),customerOrder.getDate(),
                    customerOrder.getDateUpdateStatus(),BigDecimal.valueOf(billCustomerOrder),new Rate());
//                    productCustomerOrderList.get(0).getProductTrader().getRate() );
            historyOrderList.add(historyOrder);
        }

       return historyOrderList;
    };
}


