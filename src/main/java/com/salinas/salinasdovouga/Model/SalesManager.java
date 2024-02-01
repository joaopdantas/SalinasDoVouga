package com.salinas.salinasdovouga.Model;

import com.salinas.salinasdovouga.Users.UserType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SalesManager extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Order> processedOrders;
    private List<Customer> customers;

    public SalesManager(String username, String password, String name, String citizenCardNumber, String fiscalNumber,
                        String phone, String address, String locality) {
        super(username, password, name, citizenCardNumber, fiscalNumber, phone, address, locality, UserType.SALES_MANAGER);
        this.processedOrders = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public List<Order> getProcessedOrders() {
        return processedOrders;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void processOrder(Order order) {
        // Logic for processing an order
        // May include validations, inventory updates, etc.
        processedOrders.add(order);
    }

    public void addCustomer(Customer customer) {
        // Logic for adding a new customer
        // May include validations, duplicate checks, etc.
        customers.add(customer);
    }

    // Other functionalities as needed for the Sales Manager
}
