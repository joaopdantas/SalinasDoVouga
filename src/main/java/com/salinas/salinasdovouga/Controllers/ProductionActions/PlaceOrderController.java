package com.salinas.salinasdovouga.Controllers.ProductionActions;

import com.salinas.salinasdovouga.Controllers.CustomerDashboardController;
import com.salinas.salinasdovouga.GeneralRepository;
import com.salinas.salinasdovouga.Model.Order;
import com.salinas.salinasdovouga.Model.Product;
import com.salinas.salinasdovouga.Model.ProductType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PlaceOrderController {

    private CustomerDashboardController parentController;

    @FXML
    private ChoiceBox<ProductType> productTypeChoiceBox;

    @FXML
    private TextField quantityTextField;

    public void setParentController(CustomerDashboardController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void initialize() {
        // Initialize the product type choice box
        productTypeChoiceBox.setItems(FXCollections.observableArrayList(ProductType.values()));
        productTypeChoiceBox.getSelectionModel().selectFirst(); // Select the first item by default
    }

    @FXML
    private void handleSubmitOrder() {
        try {
            // Retrieve information from the form
            ProductType selectedProductType = productTypeChoiceBox.getValue();
            int quantity = Integer.parseInt(quantityTextField.getText());

            // Create a new order
            Order order = new Order();
            Product product = new Product(selectedProductType, 1); // You can adjust the price as needed
            order.addProduct(product);

            // Add the new order
            addNewOrder(order);

            // Show success message
            showAlert("Order Placed", "Your order has been placed successfully!");

            // Clear the form fields
            productTypeChoiceBox.getSelectionModel().clearSelection();
            quantityTextField.clear();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid quantity.");
            e.printStackTrace();
        }
    }

    private void addNewOrder(Order newOrder) {
        // Add the new order to the repository
        GeneralRepository repository = GeneralRepository.getRepository();
        repository.addOrder(newOrder);

        // Serialize the repository to persist the changes
        repository.serialize("repository.ser");

        // Refresh the order table view
        parentController.refreshOrderTableView();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
