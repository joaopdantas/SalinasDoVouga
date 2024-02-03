package com.salinas.salinasdovouga.Controllers;

import com.salinas.salinasdovouga.Authentication.UserAuthentication;
import com.salinas.salinasdovouga.Model.User;
import com.salinas.salinasdovouga.UI.RegistrationController;
import com.salinas.salinasdovouga.Users.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SalesManagerController {

    public TableColumn usernameColumn;
    public TableColumn nameColumn;
    public TableColumn userTypeColumn;
    public TableColumn citizenCardNumberColumn;
    public TableColumn fiscalNumberColumn;
    public TableColumn phoneColumn;
    public TableColumn addressColumn;
    public TableColumn localityColumn;
    @FXML
    private TableView<User> userTableView;

    @FXML
    private void initialize() {
        // Set up the cell value factories for each column
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        citizenCardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("citizenCardNumber"));
        fiscalNumberColumn.setCellValueFactory(new PropertyValueFactory<>("fiscalNumber"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        localityColumn.setCellValueFactory(new PropertyValueFactory<>("locality"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

        // Refresh the table view with current user data
        refreshUserTableView();
    }

    @FXML
    private void refreshUserTableView() {
        // Clear existing items
        userTableView.getItems().clear();

        // Load all users from UserAuthentication and add them to the TableView
        userTableView.getItems().addAll(UserAuthentication.getUsersByType(UserType.CUSTOMER));
    }

    @FXML
    private void handleDeleteUser() {
        String usernameToDelete = promptForUsernameToDelete();
        if (usernameToDelete != null) {
            UserAuthentication.deleteUser(usernameToDelete);
            refreshUserTableView();
        }
    }


    @FXML
    private void handleCreateCustomer() {
        try {
            // Load the register_customer.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/register_customer.fxml"));
            Parent root = loader.load();

            // Access the controller for register_customer.fxml
            RegistrationController registrationController = loader.getController();

            // Set the roleComboBox to only allow "Customer"
            registrationController.configureComboBoxForCustomer();

            // Create a new stage for the registration window
            Stage registrationStage = new Stage();
            registrationStage.setTitle("Create Customer");
            registrationStage.setScene(new Scene(root));

            // Show the registration window
            registrationStage.showAndWait();

            // Refresh the user table view after registration
            refreshUserTableView();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load registration window.");
        }
    }

    private String promptForUsernameToDelete() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete User");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter username to delete:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleCostumeOrders(ActionEvent actionEvent) {
        try {
            // Carrega o arquivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/customers_orders.fxml"));
            Parent root = loader.load();

            // Cria um novo estágio para a janela de encomendas
            Stage ordersStage = new Stage();
            ordersStage.setTitle("Customer Orders");
            ordersStage.initModality(Modality.APPLICATION_MODAL); // Isso torna a janela modal
            ordersStage.setScene(new Scene(root));

            // Mostra a janela de encomendas
            ordersStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            // Lida com exceções, por exemplo, exibindo uma caixa de diálogo de erro
        }
    }

    public void handleShowOrders(ActionEvent actionEvent) {
    }
}

