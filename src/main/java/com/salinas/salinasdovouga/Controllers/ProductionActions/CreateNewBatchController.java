package com.salinas.salinasdovouga.Controllers.ProductionActions;

import com.salinas.salinasdovouga.Controllers.ProductionManagerController;
import com.salinas.salinasdovouga.Model.ProductType;
import com.salinas.salinasdovouga.Model.ProductionLot;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CreateNewBatchController {

    private ProductionManagerController parentController;

    public void setParentController(ProductionManagerController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private TextField lotNumberField;

    @FXML
    private DatePicker productionDatePicker;

    @FXML
    private TextField tanksField;

    @FXML
    private TextField workersField;

    @FXML
    private ChoiceBox<ProductType> productTypeChoiceBox;

    @FXML
    private void initialize() {
        productTypeChoiceBox.setItems(FXCollections.observableArrayList(ProductType.values()));
    }

    @FXML
    private TextField weightQuantityField;

    @FXML
    private void handleCreateNewBatchADD() {
        try {
            // Retrieve information from the FXML fields
            String lotNumberText = lotNumberField.getText();
            if (lotNumberText.isEmpty()) {
                showAlert("Error", "Please enter a lot number.");
                return;
            }

            int lotNumber = Integer.parseInt(lotNumberText);
            LocalDate productionDate = productionDatePicker.getValue().atStartOfDay().toLocalDate();
            String tanks = tanksField.getText();
            String workers = workersField.getText();
            ProductType productType = productTypeChoiceBox.getValue();
            String weightQuantityText = weightQuantityField.getText();

            if (weightQuantityText.isEmpty()) {
                showAlert("Error", "Please enter a weight quantity.");
                return;
            }

            double weightQuantity = Double.parseDouble(weightQuantityText);

            // Create a new ProductionLot
            ProductionLot newProductionLot = new ProductionLot();
            newProductionLot.setAssociatedTanks(tanks);
            newProductionLot.setAssociatedWorkers(workers);

            newProductionLot.setWeightQuantity(weightQuantity);

            // Call the method in the parent controller to add the new production lot
            parentController.addNewProductionLot(newProductionLot);

            // Refresh the table view in the parent controller
            parentController.refreshProductionTableView();

            showAlert("Batch Created", "New batch created successfully!");

        } catch (NumberFormatException | NullPointerException e) {
            showAlert("Error", "Please fill in all fields with valid data.");
            e.printStackTrace();
        }
    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
