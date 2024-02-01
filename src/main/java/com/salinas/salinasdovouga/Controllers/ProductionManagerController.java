package com.salinas.salinasdovouga.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.salinas.salinasdovouga.Model.ProductionLot;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ProductionManagerController {

    @FXML
    private TextField lotNumberField;

    @FXML
    private DatePicker productionDatePicker;

    @FXML
    private TextField tanksField;

    @FXML
    private TextField workersField;

    @FXML
    private ChoiceBox<String> productTypeChoiceBox;

    @FXML
    private TextField weightQuantityField;

    @FXML
    private TableView<ProductionLot> productionTableView;

    @FXML
    private TableColumn<ProductionLot, String> lotNumberColumn;
    @FXML
    private TableColumn<ProductionLot, LocalDate> productionDateColumn;
    @FXML
    private TableColumn<ProductionLot, String> associatedTanksColumn;
    @FXML
    private TableColumn<ProductionLot, String> associatedWorkersColumn;
    @FXML
    private TableColumn<ProductionLot, String> productTypeColumn;
    @FXML
    private TableColumn<ProductionLot, String> weightQuantityColumn;

    @FXML
    private void handleCreateNewBatch() {
        try {
            // Load the registration.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/create_new_batch.fxml"));
            Parent root = loader.load();

            if (loader.getController() == null) {
                System.out.println("Controller is null");
            }

            // Create a new stage for the registration window
            Stage createNewBatchStage = new Stage();
            createNewBatchStage.setTitle("Create New Batch");
            createNewBatchStage.setScene(new Scene(root));

            // Show the registration window
            createNewBatchStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load create new batch window.");
        }
    }

    @FXML
    private void initialize() {
        // Set up the cell value factories for each column
        lotNumberColumn.setCellValueFactory(new PropertyValueFactory<>("lotNumber"));
        productionDateColumn.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
        associatedTanksColumn.setCellValueFactory(new PropertyValueFactory<>("associatedTanks"));
        associatedWorkersColumn.setCellValueFactory(new PropertyValueFactory<>("associatedWorkers"));
        productTypeColumn.setCellValueFactory(new PropertyValueFactory<>("productType"));
        weightQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("weightQuantity"));

        // Refresh the table view with current production lot data
        refreshProductionTableView();
    }
    @FXML
    private void refreshProductionTableView() {
        // Clear existing items
        productionTableView.getItems().clear();

        // Load all production lots and add them to the TableView
        productionTableView.getItems().addAll();

    }

   /* @FXML
    private void handleCreateNewBatchADD() {
        try {
            // Retrieve information from the FXML fields
            int lotNumber = Integer.parseInt(lotNumberField.getText());
            LocalDate productionDate = productionDatePicker.getValue().atStartOfDay().toLocalDate();
            String tanks = tanksField.getText();
            String workers = workersField.getText();
            String productType = productTypeChoiceBox.getValue();
            double weightQuantity = Double.parseDouble(weightQuantityField.getText());


            // Create a new ProductionLot
            ProductionLot newProductionLot = new ProductionLot(lotNumber, productionDate, tanks, workers, productType, weightQuantity);
            newProductionLot.setAssociatedTanks(tanks);
            newProductionLot.setAssociatedWorkers(workers);
            newProductionLot.setProductType(productType);
            newProductionLot.setWeightQuantity(weightQuantity);

            // TODO: Add code to save the newProductionLot or perform any other necessary actions

            showAlert("Batch Created", "New batch created successfully!");

        } catch (NumberFormatException | NullPointerException e) {
            showAlert("Error", "Please fill in all fields with valid data.");
            e.printStackTrace();
        }
    }*/

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
