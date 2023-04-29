package com.spring.catalogapp.entity;

import com.spring.catalogapp.entity.Product;
import lombok.ToString;

import java.util.ArrayList;

@ToString
public class ReturnObject {
    private ArrayList<Product> products;
    private Integer noOfPages;

    public ReturnObject(ArrayList<Product> products, Integer noOfPages) {
        this.products = products;
        this.noOfPages = noOfPages;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Integer getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(Integer noOfPages) {
        this.noOfPages = noOfPages;
    }
}
