package com.salinas.salinasdovouga.Controllers;

import com.salinas.salinasdovouga.Model.ProductionLot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

public class ProductionManagerController {

    @FXML
    private ListView<ProductionLot> lotListView;

    private ObservableList<ProductionLot> productionLots;

    @FXML
    private void initialize() {
        // Initialize production lots (this can be loaded from a data source)
        productionLots = FXCollections.observableArrayList();
        // Set the items to the lotListView
        lotListView.setItems(productionLots);
    }

    @FXML
    private void handleCreateNewBatch() {
        // Create a new production lot
        ProductionLot newLot = new ProductionLot();

        // Add the new lot to the list
        productionLots.add(newLot);

        showAlert("Create New Batch", "New batch created successfully.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
