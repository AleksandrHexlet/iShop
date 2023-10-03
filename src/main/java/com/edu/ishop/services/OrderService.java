package com.edu.ishop.services;

import com.edu.ishop.entity.Customer;
import com.edu.ishop.entity.CustomerOrder;
import com.edu.ishop.entity.ProductCustomerOrder;
import com.edu.ishop.exceptions.ResponseException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {


    public CustomerOrder postNewOrder(ProductCustomerOrder productCustomerOrder) throws ResponseException {
        LocalDate dateCreated = LocalDate.now();
        CustomerOrder.OrderStatus status = CustomerOrder.OrderStatus.ORDER_ACCEPTED;
        String deliveryAddress = productCustomerOrder.getCustomerOrder().getDeliveryAddress();

        CustomerOrder customerOrder = new CustomerOrder(productCustomerOrder.getCustomerOrder().getCustomer(),
                dateCreated, dateCreated, status, deliveryAddress);
        productCustomerOrder.setCustomerOrder(customerOrder);



        return customerOrder;
    }
}


//   [
//   {
//        "product":{
//             "id": 789;
//                  };
//         customerOrder:{
//            customer:{ "id": 567;
//                       "deliveryAddress": "MSK"
//               }
//
//    }
//             "count": 1234567;
//    }
//   {
//        "product":{
//             "id": 123789;
//                  };
//         customerOrder:{
//            customer:{ "id": 123567;
//                       "deliveryAddress": "SPB"
//               }
//
//    }
//             "count": 1234567;
//    }
//    ]