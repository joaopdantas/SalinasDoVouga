package com.salinas.salinasdovouga.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class FinalProductLot implements Serializable {
    private int finalProductLotNumber;
    private LocalDate productionDate;
    private ProductionLot associatedLot;
    private String productName;
    private double weight;

    // Construtor
    public FinalProductLot(int finalProductLotNumber, LocalDate productionDate, ProductionLot associatedLot) {
        this.finalProductLotNumber = finalProductLotNumber;
        this.productionDate = productionDate;
        this.associatedLot = associatedLot;
        this.productName = productName;
        this.weight = weight;
    }

    // Getters e Setters

    public int getFinalProductLotNumber() {
        return finalProductLotNumber;
    }

    public void setFinalProductLotNumber(int finalProductLotNumber) {
        this.finalProductLotNumber = finalProductLotNumber;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public ProductionLot getAssociatedLot() {
        return associatedLot;
    }

    public void setAssociatedLot(ProductionLot associatedLot) {
        this.associatedLot = associatedLot;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Outros métodos, se necessário
}
