package com.salinas.salinasdovouga.Controllers.ProductionActions;

import com.salinas.salinasdovouga.Controllers.FinalProductBatchController;
import com.salinas.salinasdovouga.Model.ProductionLot;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;

public class CreateFinalProductController {

    private FinalProductBatchController parentController;

    public void setParentControllerFinalProduct(FinalProductBatchController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private TextField finalProductLotNumberField;

    @FXML
    private DatePicker finalProductionDateField;

    @FXML
    private ChoiceBox<ProductionLot> associatedLotsChoiceBox;

    public void setAssociatedLots(List<ProductionLot> productionLots) {
        if (productionLots != null && !productionLots.isEmpty()) {
            System.out.println("setAssociatedLots called with " + productionLots.size() + " lots.");
            associatedLotsChoiceBox.setItems(FXCollections.observableArrayList(productionLots));
        } else {
            System.out.println("setAssociatedLots: Empty or null list of production lots.");
        }
    }

    @FXML
    private void createFinalProduct(ActionEvent actionEvent) {
        try {
            // Retrieve information from the FXML fields
            String lotNumberText = finalProductLotNumberField.getText();
            LocalDate productionDate = finalProductionDateField.getValue();
            ProductionLot associatedLot = associatedLotsChoiceBox.getValue();

            // Perform validations
            if (lotNumberText.isEmpty() || productionDate == null || associatedLot == null) {
                showAlert("Error", "Please fill in all fields with valid data.");
                return;
            }

            int lotNumber = Integer.parseInt(lotNumberText);

            // Create a new FinalProduct
           // IMPORTANTE RESOLVER FinalProduct newFinalProduct = new FinalProduct(lotNumber, productionDate, associatedLots);

            // Call the method in the parent controller to add the new final product
           // parentController.addNewFinalProduct(newFinalProduct);

            // Refresh the table view in the parent controller
            parentController.refreshFinalProductTableView();

            showAlert("Final Product Created", "New final product created successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values for lot number.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
