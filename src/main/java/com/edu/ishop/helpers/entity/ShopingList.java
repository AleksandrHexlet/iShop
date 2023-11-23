package com.edu.ishop.helpers.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
    public class ShopingList extends LoginData {
        //    Map<BigDecimal,Product> shopingList = new HashMap<BigDecimal,Product>();
        List<Product> productList = new ArrayList<Product>();
        String name;
        BigDecimal costAllItemsInList;

        public ShopingList() {
        }

        public List<Product> getProductList() {
            return productList;
        }

        public void setProductList(List<Product> productList) {
            this.productList = productList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getCostAllItemsInList() {
            return costAllItemsInList;
        }

        public void setCostAllItemsInList(BigDecimal costAllItemsInList) {
            this.costAllItemsInList = this.costAllItemsInList.add(costAllItemsInList);

        }
    }

