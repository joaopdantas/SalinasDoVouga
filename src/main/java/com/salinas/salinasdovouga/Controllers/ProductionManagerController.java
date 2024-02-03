package com.salinas.salinasdovouga.Controllers;

import com.salinas.salinasdovouga.Controllers.ProductionActions.CreateFinalProductController;
import com.salinas.salinasdovouga.Controllers.ProductionActions.CreateNewBatchController;
import com.salinas.salinasdovouga.GeneralRepository;
import com.salinas.salinasdovouga.Model.FinalProductLot;
import com.salinas.salinasdovouga.Model.ProductionLot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        repository = GeneralRepository.deserialize("repository.ser");

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
        // TODO: Adicione código para salvar o novo ProductionLot ou realizar outras ações necessárias
        // Use o número do lote correto como chave ao adicionar ao repositório
        GeneralRepository repository = GeneralRepository.getRepository();
        repository.getProductionLots().put(String.valueOf(newProductionLot.getLotNumber()), newProductionLot);

        repository.serialize("repository.ser");

        // For now, you can print the newProductionLot details
        System.out.println("New Production Lot: " + newProductionLot);

        // Refresh the table view
        refreshProductionTableView();
    }

    @FXML
    public void handleCreateFinalProduct(ActionEvent actionEvent) {
        try {
            // Load the create_final_product.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/create_final_product.fxml"));
            Parent root = loader.load();

            // Create a new stage for the create_final_product window
            Stage createFinalProductStage = new Stage();
            createFinalProductStage.setTitle("Create Final Product");
            createFinalProductStage.setScene(new Scene(root));
            createFinalProductStage.initModality(Modality.WINDOW_MODAL);
            createFinalProductStage.initOwner(productionTableView.getScene().getWindow());

            // Set the controller for create_final_product.fxml
            CreateFinalProductController createFinalProductController = loader.getController();

            // Show the create_final_product window
            createFinalProductStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load create final product window.");
        }
    }

    public void addFinalProduct(int finalProductLotNumber, LocalDate productionDate, ProductionLot associatedLot) {
        // TODO: Adicione código para salvar o novo FinalProduct ou realizar outras ações necessárias
        // Use o número do lote correto como chave ao adicionar ao repositório
        GeneralRepository repository = GeneralRepository.getRepository();

        // Aqui, você deve criar e adicionar um novo objeto FinalProduct ao repositório
        FinalProductLot finalProduct = createFinalProduct(finalProductLotNumber, productionDate, associatedLot);
        repository.getFinalProducts().put(String.valueOf(finalProductLotNumber), finalProduct);

        repository.serialize("repository.ser");

        // For now, you can print the finalProduct details
        System.out.println("New Final Product: " + finalProduct);

    }

    private FinalProductLot createFinalProduct(int finalProductLotNumber, LocalDate productionDate, ProductionLot associatedLot) {
        return new FinalProductLot(finalProductLotNumber, productionDate, (ProductionLot) associatedLot);
    }




    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void addNewFinalProduct(int finalProductLotNumber, LocalDate productionDate, ProductionLot selectedLot) {
    }

    public void refreshFinalProductTableView() {
    }
}
