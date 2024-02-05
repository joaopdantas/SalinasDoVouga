package com.salinas.salinasdovouga.Controllers.ProductionActions;

import com.salinas.salinasdovouga.GeneralRepository;
import com.salinas.salinasdovouga.Model.FinalProduct;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class FinalProductBatchController {


    @FXML
    private TableView<FinalProduct> finalProductTableView;


    @FXML
    private TableColumn<FinalProduct, String> finalProductIDColumn;

    @FXML
    private TableColumn<FinalProduct, LocalDate> finalProductDateColumn;

    @FXML
    private TableColumn<FinalProduct, String> associatedLotsColumn;

    private GeneralRepository repository;




    @FXML
    private void initialize() {
        // Set up the cell value factories for each column
        finalProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("finalProductID"));
        finalProductDateColumn.setCellValueFactory(new PropertyValueFactory<>("finalProductionDate"));
        associatedLotsColumn.setCellValueFactory(new PropertyValueFactory<>("associatedLots"));

        // Initialize the repository
        repository = GeneralRepository.deserialize("repository.ser");

        // Refresh the table view with current production lot data
        refreshFinalProductTableView();

    }

    @FXML
    public void handleCreateFinalProduct() {
        try {
            // Carrega o novo FXML e seu controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/create_final_product.fxml"));
            Parent root = loader.load();
            CreateFinalProductController createFinalProductController = loader.getController();

            // Define o controlador pai
            createFinalProductController.setParentControllerFinalProduct(this);

            // Cria uma nova cena
            Scene scene = new Scene(root);

            // Cria um novo estágio (janela)
            Stage stage = new Stage();
            stage.setTitle("Create Final Product");
            stage.setScene(scene);

            // Exibe a nova janela
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Lida com a exceção (por exemplo, exibindo uma mensagem de erro)
        }
    }

    public void addNewFinalProduct(FinalProduct newFinalProduct) {
        // TODO: Adicione código para salvar o novo ProductionLot ou realizar outras ações necessárias
        // Use o número do lote correto como chave ao adicionar ao repositório
        GeneralRepository repository = GeneralRepository.getRepository();
        repository.getFinalProducts().put(String.valueOf(newFinalProduct.getFinalProductID()), newFinalProduct);

        // Salve as alterações no repositório
        repository.serialize("repository.ser");

        // For now, you can print the newProductionLot details
        System.out.println("New FinalProduct Lot: " + newFinalProduct);

        // Refresh the table view
        refreshFinalProductTableView();
    }

    @FXML
    public void refreshFinalProductTableView() {
        // Clear existing items
        finalProductTableView.getItems().clear();

        // Load all production lots from the repository and add them to the TableView
        Map<String, FinalProduct> finalProducts = GeneralRepository.getRepository().getFinalProducts();
        System.out.println("Final Products from Repository: " + finalProducts);

        finalProductTableView.getItems().addAll(finalProducts.values());
    }



}