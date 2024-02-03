package com.salinas.salinasdovouga.Controllers.ProductionActions;

import com.salinas.salinasdovouga.Controllers.ProductionManagerController;
import com.salinas.salinasdovouga.Model.ProductionLot;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateFinalProductController {

    private ProductionManagerController parentController;

    public void setParentControllerForFinalProduct(ProductionManagerController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private TextField finalProductLotNumberField;

    @FXML
    private DatePicker productionFinalDateField;

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
    private void handleCreateFinalProduct() {
        try {
            // Obtenha informações dos campos FXML
            String finalProductLotNumberText = finalProductLotNumberField.getText();
            if (finalProductLotNumberText.isEmpty()) {
                showAlert("Error", "Please enter a final product lot number.");
                return;
            }

            int finalProductLotNumber = Integer.parseInt(finalProductLotNumberText);
            LocalDate productionDate = productionFinalDateField.getValue().atStartOfDay().toLocalDate();

            // Obtenha o controlador associado ao FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/create_final_product.fxml"));
            Parent root = loader.load();
            CreateFinalProductController controller = loader.getController();

            // Obtenha a lista de lotes
            List<ProductionLot> productionLots = new ArrayList<>();
            productionLots.add(new ProductionLot(/* ... */));
            productionLots.add(new ProductionLot(/* ... */));

            // Configure a ChoiceBox com a lista de lotes
            controller.setAssociatedLots(productionLots);

            // Obtenha o lote associado selecionado
            ProductionLot selectedLot = controller.associatedLotsChoiceBox.getValue();

            if (selectedLot == null) {
                showAlert("Error", "Please select an associated production lot.");
                return;
            }

            // Lógica para criar o produto final com o lote associado
            // ...

            // Exemplo de como acessar os detalhes do lote associado
            System.out.println("Lote associado selecionado: " + selectedLot);

            // Chame o método no controlador pai para adicionar o novo produto final
            parentController.addNewFinalProduct(finalProductLotNumber, productionDate, selectedLot);

            // Atualize a exibição da tabela no controlador pai
            parentController.refreshFinalProductTableView();

            showAlert("Final Product Created", "New final product created successfully!");

        } catch (NumberFormatException | NullPointerException | IOException e) {
            showAlert("Error", "Please fill in all fields with valid data.");
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