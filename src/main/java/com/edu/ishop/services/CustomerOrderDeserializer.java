package com.edu.ishop.services;

import com.edu.ishop.entity.Customer;
import com.edu.ishop.entity.CustomerOrder;
import com.edu.ishop.entity.Product;
import com.edu.ishop.entity.ProductCustomerOrder;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Iterator;


public class CustomerOrderDeserializer extends StdDeserializer<CustomerOrder> {
    public CustomerOrderDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomerOrderDeserializer() {
        this(null);
    }

    @Override
    public CustomerOrder deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        CustomerOrder customerOrder = new CustomerOrder();
        Customer customer = new Customer();

        JsonNode node = parser.getCodec().readTree(parser); // вся JSON строка, которая прилетела с клиента
        String deliveryAddress = node.get("deliveryAddress").asText();
        int customerId = (Integer) (node.get("customerId")).numberValue();
        customer.setId(customerId);
        customerOrder.setCustomer(customer);
        customerOrder.setDeliveryAddress(deliveryAddress);

        Iterator<JsonNode> elements = node.get("products").elements(); //elements() вернет массив products
        while (elements.hasNext()) {
            ProductCustomerOrder productCustomerOrder = new ProductCustomerOrder();
            Product product = new Product();

            JsonNode element = elements.next();
            int productId = (Integer) (element.get("productId")).numberValue();
            int productCount = (Integer) (element.get("count")).numberValue();
            product.setId(productId);

            productCustomerOrder.setProduct(product);
            productCustomerOrder.setCount(productCount);
            customerOrder.addListProductCustomerOrder(productCustomerOrder);
        }

        return customerOrder;
    }
}

//    private Customer customer;
//    private List<ProductCustomerOrder> productCustomerOrder = new ArrayList<>();
//    //    select FROM customerOrder LEFT JOIN ProductCustomerOrder
////    on ProductCustomerOrder.customerOrder = customerOrder(this)
//    private LocalDate date;
//    private LocalDate dateUpdateStatus;
//    private CustomerOrder.OrderStatus status;
//    private String deliveryAddress;


//   {
//         "customerId": "567",
//         "deliveryAddress": "MSK",
//
//        "products":
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
//     }