package com.salinas.salinasdovouga.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProductType productType;
    private double price;

    public Product(ProductType productType, double price) {
        this.productType = productType;
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public double getPrice() {
        return price;
    }
}
