package com.salinas.salinasdovouga.Model;

import java.io.Serializable;

public class ProductionLot implements Serializable {

    private static final long serialVersionUID = 1L;

    private int lotNumber;
    private String productionDate;

    // Constructors, getters, setters, etc.

    public ProductionLot() {
        // Default constructor
    }

    public ProductionLot(int lotNumber, String productionDate) {
        this.lotNumber = lotNumber;
        this.productionDate = productionDate;
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return "ProductionLot{" +
                "lotNumber=" + lotNumber +
                ", productionDate='" + productionDate + '\'' +
                '}';
    }
}
