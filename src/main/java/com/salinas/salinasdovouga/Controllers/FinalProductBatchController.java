package com.salinas.salinasdovouga.Controllers;

import com.salinas.salinasdovouga.Controllers.ProductionActions.CreateFinalProductController;
import com.salinas.salinasdovouga.Model.FinalProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class FinalProductBatchController {


    @FXML
    private TableColumn<?, ?> associatedLots;

    @FXML
    private TableColumn<?, ?> finalProductLotNumberColumn;

    @FXML
    private TableView<?> finalProductTableView;

    @FXML
    private TableColumn<?, ?> productionDateColumn;

    @FXML
    public void handleCreateFinalProduct(ActionEvent actionEvent) {
        try {
            // Carrega o novo FXML e seu controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/create_final_product.fxml"));
            Parent root = loader.load();
            CreateFinalProductController createFinalProductController = loader.getController();

            // Cria uma nova cena
            Scene scene = new Scene(root);

            // Cria um novo estágio (janela)
            Stage stage = new Stage();
            stage.setTitle("Create Final Batch");
            stage.setScene(scene);

            // Exibe a nova janela
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Lida com a exceção (por exemplo, exibindo uma mensagem de erro)
        }
    }

    public void addNewFinalProduct(FinalProduct newFinalProduct) {
    }

    public void refreshFinalProductTableView() {
    }
}