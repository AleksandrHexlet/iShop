package com.edu.ishop.client.services;

import com.edu.ishop.helpers.entity.CustomerOrder;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepositoryOUT) {
        this.orderRepository = orderRepositoryOUT;
    }

    public CustomerOrder postNewOrder(@Valid CustomerOrder customerOrder) throws ResponseException {
        LocalDate dateCreated = LocalDate.now();
        CustomerOrder.OrderStatus status = CustomerOrder.OrderStatus.ORDER_ACCEPTED;

        customerOrder.setDate(dateCreated);
        customerOrder.setDateUpdateStatus(dateCreated);
        customerOrder.setStatus(status);
//        System.out.println(customerOrder.getDeliveryAddress());
        boolean isExistCustomer = orderRepository.existsById(customerOrder.getCustomer().getId());
//        boolean isExistProduct = orderRepository.existsById(customerOrder.);

        if (isExistCustomer == false) throw new ResponseException
                ("Такого клиента не существует. Пройдите авторизацию");
        int[] idProductArray = customerOrder.getProductCustomerOrderList().stream()
                .mapToInt((productCustomerOrder)-> productCustomerOrder.getProduct()
                        .getId()).toArray();
        int countProductInBD = orderRepository.getProductsCountById(idProductArray);
        if(countProductInBD != idProductArray.length) throw new
                ResponseException("Одного из товар нет в базе данных");

        int idCustomerOrder = orderRepository.save(customerOrder).getId();
        System.out.println("idCustomerOrder == " + idCustomerOrder);
        if (idCustomerOrder != customerOrder.getId()) {
            return customerOrder;
        } else {
            throw new ResponseException("Сохранение не удалось");
        }
    }



}

//   {
//         "customerId": "567",
//         "deliveryAddress": "MSK",
//
//        "product":
//        [
//        {
//             "productId":"789",
//             "count": "1234",
//        }
//        {
//             "productId":"567",
//             "count": "4567",
//        }
//        ];
//
//
//    }
//