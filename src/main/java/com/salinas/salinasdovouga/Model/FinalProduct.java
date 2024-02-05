package com.salinas.salinasdovouga.Model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinalProduct extends ProductionLot implements Serializable {

        private static final long serialVersionUID = 1L;

    private String finalProductID;

    private LocalDate finalProductionDate;
    private List<ProductionLot> associatedLots;

    public FinalProduct(Integer lotNumber, LocalDate productionDate, String associatedTanks, String associatedWorkers, String productType, Double weightQuantity, String finalProductID) {
        super(lotNumber, productionDate, associatedTanks, associatedWorkers, ProductType.valueOf(productType), weightQuantity);
        this.finalProductID = finalProductID;
        this.finalProductionDate = finalProductionDate;
        this.associatedLots = new ArrayList<>();
    }



    public String getFinalProductID() {
        return finalProductID;
    }

    public void setFinalProductID(String finalProductID) {
        this.finalProductID = finalProductID;
    }

    public LocalDate getFinalProductionDate() {
        return finalProductionDate;
    }

    public void setFinalProductionDate(LocalDate finalProductionDate) {
        this.finalProductionDate = finalProductionDate;
    }

    public List<ProductionLot> getAssociatedLots() {
        return associatedLots;
    }

    public void setAssociatedLots(List<ProductionLot> associatedLots) {
        this.associatedLots = associatedLots;
    }
}

