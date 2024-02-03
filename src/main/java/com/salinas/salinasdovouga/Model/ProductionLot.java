package com.salinas.salinasdovouga.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ProductionLot implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer lotNumber;
    private LocalDate productionDate;
    private String associatedTanks;
    private String associatedWorkers;
    private String productType;
    private Double weightQuantity;

    // Constructors, getters, setters, etc.




    public ProductionLot(int lotNumber, LocalDate productionDate, String tanks, String workers, ProductType productType, double weightQuantity) {
        // Default constructor
    }

    public ProductionLot(Integer lotNumber, LocalDate productionDate, String associatedTanks,
                         String associatedWorkers, String productType, Double weightQuantity) {
        this.lotNumber = lotNumber;
        this.productionDate = productionDate;
        this.associatedTanks = associatedTanks;
        this.associatedWorkers = associatedWorkers;
        this.productType = productType;
        this.weightQuantity = weightQuantity;
    }



    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getAssociatedTanks() {
        return associatedTanks;
    }

    public void setAssociatedTanks(String associatedTanks) {
        this.associatedTanks = associatedTanks;
    }

    public String getAssociatedWorkers() {
        return associatedWorkers;
    }

    public void setAssociatedWorkers(String associatedWorkers) {
        this.associatedWorkers = associatedWorkers;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getWeightQuantity() {
        return (weightQuantity != null) ? weightQuantity.doubleValue() : 0.0;
    }


    public void setWeightQuantity(double weightQuantity) {
        this.weightQuantity = weightQuantity;
    }

    @Override
    public String toString() {
        return "ProductionLot{" +
                "lotNumber='" + lotNumber + '\'' +
                ", productionDate=" + productionDate +
                ", associatedTanks='" + associatedTanks + '\'' +
                ", associatedWorkers='" + associatedWorkers + '\'' +
                ", productType='" + productType + '\'' +
                ", weightQuantity='" + weightQuantity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionLot that = (ProductionLot) o;
        return lotNumber == that.lotNumber;
    }


    @Override
    public int hashCode() {
        return Objects.hash(lotNumber);
    }
}
