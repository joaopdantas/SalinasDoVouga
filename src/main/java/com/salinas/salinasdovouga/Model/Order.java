package com.salinas.salinasdovouga.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final AtomicInteger orderCounter = new AtomicInteger(1);

    private final int orderNumber;
    private final List<Product> products;

    public Order() {
        this.orderNumber = orderCounter.getAndIncrement();
        this.products = new ArrayList<>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
