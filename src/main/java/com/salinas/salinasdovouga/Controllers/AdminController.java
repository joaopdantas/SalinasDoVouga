package com.salinas.salinasdovouga.Controllers;

import com.salinas.salinasdovouga.Authentication.UserAuthentication;
import com.salinas.salinasdovouga.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminController {

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
        userTableView.getItems().addAll(UserAuthentication.getUsers());
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
    private void handleCreateUser() {
        try {
            // Load the registration.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/register_admin.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration window
            Stage registrationStage = new Stage();
            registrationStage.setTitle("Create User");
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

    @FXML
    private void handleSystemSettings() {
        // TODO: Implement system settings handling
        // Example: Open a new window for system settings
        showAlert("System Settings", "System settings window will be opened.");
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
}
