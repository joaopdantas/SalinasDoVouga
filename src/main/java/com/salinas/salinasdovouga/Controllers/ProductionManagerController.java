package com.salinas.salinasdovouga.Controllers;

import com.salinas.salinasdovouga.GeneralRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.salinas.salinasdovouga.Controllers.ProductionActions.CreateNewBatchController;
import com.salinas.salinasdovouga.Model.ProductionLot;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class ProductionManagerController {


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

    private GeneralRepository repository;

    @FXML
    private void initialize() {
        // Set up the cell value factories for each column
        lotNumberColumn.setCellValueFactory(new PropertyValueFactory<>("lotNumber"));
        productionDateColumn.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
        associatedTanksColumn.setCellValueFactory(new PropertyValueFactory<>("associatedTanks"));
        associatedWorkersColumn.setCellValueFactory(new PropertyValueFactory<>("associatedWorkers"));
        productTypeColumn.setCellValueFactory(new PropertyValueFactory<>("productType"));
        weightQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("weightQuantity"));

        // Initialize the repository
        repository = GeneralRepository.getRepository();

        // Refresh the table view with current production lot data
        refreshProductionTableView();
    }


    @FXML
    public void refreshProductionTableView() {
        // Clear existing items
        productionTableView.getItems().clear();

        // Load all production lots from the repository and add them to the TableView
        Map<String, ProductionLot> productionLots = GeneralRepository.getRepository().getProductionLots();
        System.out.println("Production Lots from Repository: " + productionLots);

        productionTableView.getItems().addAll(productionLots.values());
    }



    @FXML
    private void handleCreateNewBatch() {
        try {
            // Load the create_new_batch.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/create_new_batch.fxml"));
            Parent root = loader.load();

            // Create a new stage for the create_new_batch window
            Stage createNewBatchStage = new Stage();
            createNewBatchStage.setTitle("Create New Batch");
            createNewBatchStage.setScene(new Scene(root));
            createNewBatchStage.initModality(Modality.WINDOW_MODAL);
            createNewBatchStage.initOwner(productionTableView.getScene().getWindow());

            // Set the controller for create_new_batch.fxml
            CreateNewBatchController createNewBatchController = loader.getController();
            createNewBatchController.setParentController(this);

            // Show the create_new_batch window
            createNewBatchStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load create new batch window.");
        }
    }

    public void addNewProductionLot(ProductionLot newProductionLot) {
        // TODO: Add code to save the newProductionLot or perform any other necessary actions
        // Use the correct lot number as the key when adding to the repository
        GeneralRepository.getRepository().getProductionLots().put(String.valueOf(newProductionLot.getLotNumber()), newProductionLot);

        // For now, you can print the newProductionLot details
        System.out.println("New Production Lot: " + newProductionLot);

        // Refresh the table view
        refreshProductionTableView();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
