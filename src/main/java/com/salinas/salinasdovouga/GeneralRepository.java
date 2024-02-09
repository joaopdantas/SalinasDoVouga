package com.salinas.salinasdovouga;

import com.salinas.salinasdovouga.Model.FinalProduct;
import com.salinas.salinasdovouga.Model.Order;
import com.salinas.salinasdovouga.Model.ProductionLot;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class GeneralRepository implements Serializable {
    private static GeneralRepository repo = null;

    private Map<String, ProductionLot> productionLots = new HashMap<>();
    // Add more maps for other entities if needed
    private Map<String, FinalProduct> finalProducts = new HashMap<>();

    private List<Order> orders = new ArrayList<>(); // Add a list to store orders

    public List<Order> getOrders() {
        return orders;
    }
    public void addOrder(Order order) {
        orders.add(order);
    }

    private GeneralRepository() {
    }

    public Map<String, ProductionLot> getProductionLots() {
        return productionLots;
    }
    // Add more getters for other entities if needed
    public Map<String, FinalProduct> getFinalProducts() {
        return finalProducts;
    }

    public static GeneralRepository getRepository() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        if (repo == null)
            repo = new GeneralRepository();
        lock.unlock();
        return repo;
    }

    public void serialize(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename + "\n");
        } catch (IOException ex) {
            System.out.println("Error Serialize: " + ex.getMessage());
        }
    }

    public static GeneralRepository deserialize(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repo = (GeneralRepository) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            System.out.println("Error Deserialize: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Repository class not found. " + ex.getMessage());
        }
        return repo;
    }
}

