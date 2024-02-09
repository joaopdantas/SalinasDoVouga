package com.salinas.salinasdovouga.Controllers;

import com.salinas.salinasdovouga.Controllers.ProductionActions.PlaceOrderController;
import com.salinas.salinasdovouga.GeneralRepository;
import com.salinas.salinasdovouga.Model.Order;
import com.salinas.salinasdovouga.Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CustomerDashboardController {

    @FXML
    private void handleBrowseProducts(ActionEvent event) {
        try {
            // Load the registration.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/ProductCatalog.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration window
            Stage browseStage = new Stage();
            browseStage.setTitle("Browse Products");
            browseStage.setScene(new Scene(root));

            // Show the registration window
            browseStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load registration window.");
        }
    }

    @FXML
    void handleManagePersonalInfo(ActionEvent event) {

    }

    @FXML
    void handlePlaceOrder(ActionEvent event) {
        try {
            // Load the place order form or dialog
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/Production Actions/PlaceOrder.fxml"));
            Parent root = loader.load();

            // Create a new stage for the place order form or dialog
            Stage placeOrderStage = new Stage();
            placeOrderStage.setTitle("Place Order");
            placeOrderStage.setScene(new Scene(root));

            // Set the controller for the place order form or dialog
            PlaceOrderController placeOrderController = loader.getController();
            placeOrderController.setParentController(this);

            // Show the place order form or dialog
            placeOrderStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load place order form or dialog.");
        }

    }

    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private TableColumn<Order, Integer> orderNumberColumn;

    @FXML
    private TableColumn<Order, String> productDetailsColumn;

    // Other columns as needed

    @FXML
    private void initialize() {
        // Initialize order table view columns
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));

        // Initialize product details column
        productDetailsColumn.setCellValueFactory(cellData -> {
            List<Product> products = cellData.getValue().getProducts();
            StringBuilder details = new StringBuilder();
            for (Product product : products) {
                details.append(product.getProductType()).append(", "); // Adjust as needed
            }
            return new SimpleStringProperty(details.toString());
        });

        // Other column configurations

        // Populate order table view
        refreshOrderTableView();
    }

    @FXML
    public void refreshOrderTableView() {
        // Clear existing items
        orderTableView.getItems().clear();

        // Load all orders from the repository and add them to the TableView
        List<Order> orders = GeneralRepository.getRepository().getOrders();
        System.out.println("Orders from Repository: " + orders);

        orderTableView.getItems().addAll(orders);
    }



    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
