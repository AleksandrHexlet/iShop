package com.edu.ishop.admin.services;

import com.edu.ishop.entity.Category;
import com.edu.ishop.entity.FeedBack;
import com.edu.ishop.entity.Product;
import com.edu.ishop.entity.ProductManufacturer;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDeserialized  extends StdDeserializer<Product> {

    protected ProductDeserialized(Class<?> vc) {
        super(vc);
    }
    protected ProductDeserialized() {
      this(null);
    }

    @Override
    public Product deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        Product product = new Product();
        ProductManufacturer productManufacturer = new ProductManufacturer();
        Category category = new Category();
        JsonNode node = parser.getCodec().readTree(parser);
        productManufacturer.setName(node.get("productManufacturerName").asText());
        productManufacturer.setCity(node.get("productManufacturerCity").asText());
        category.setId((Integer) node.get("categoryProduct").numberValue());

        product.setNameProduct(node.get("nameProduct").asText());
        product.setPrice(BigDecimal.valueOf((Double)node.get("price").numberValue()));
        product.setQuantityStock((Integer) node.get("quantityStock").numberValue());
        product.setProductManufacturer(productManufacturer);
        product.setCategoryProduct(category);

        return product;
    }
}
