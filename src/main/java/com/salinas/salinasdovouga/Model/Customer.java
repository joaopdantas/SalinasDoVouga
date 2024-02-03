package com.salinas.salinasdovouga.Model;

import com.salinas.salinasdovouga.Users.UserType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Order> orders;

    public Customer(String username, String password, String name, String citizenCardNumber, String fiscalNumber,
                    String phone, String address, String locality) {
        super(username, password, name, citizenCardNumber, fiscalNumber, phone, address, locality, UserType.CUSTOMER);
        this.orders = new ArrayList<>();
    }

    public String getUsername() {
        return super.getUsername();
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void placeOrder(Order order) {
        // Logic to place an order
        // This could include validations, updates to inventory, etc.
        orders.add(order);
    }

    public List<Product> browseAvailableProducts() {
        // Logic to browse available products (salt and flor de sal)
        // Return the list of available products
        return null;  // Adjust as per real logic
    }

    public void viewOrderHistory() {
        // Logic to view order history
        // This can be displayed in the user interface or returned as a list
    }

    public void managePersonalInformation() {
        // Logic to manage personal information
        // This can be displayed in the user interface for editing
    }

    // Additional methods or attributes as needed
}