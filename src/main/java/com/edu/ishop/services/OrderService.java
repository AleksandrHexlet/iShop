package com.edu.ishop.services;

import com.edu.ishop.entity.Customer;
import com.edu.ishop.entity.CustomerOrder;
import com.edu.ishop.entity.ProductCustomerOrder;
import com.edu.ishop.exceptions.ResponseException;
import com.edu.ishop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepositoryOUT) {
        this.orderRepository = orderRepositoryOUT;
    }

    public CustomerOrder postNewOrder(CustomerOrder customerOrder) throws ResponseException {
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
                .map((productCustomerOrder)-> productCustomerOrder.getProduct().getId()).toArray(new int[0]);

//                    .map((productCustomerOrder)-> productCustomerOrder.getProduct().getId())
//                .toArray(int[]::new);

//        orderRepository.getProductsCountById();
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