package com.salinas.salinasdovouga.UI;

import com.salinas.salinasdovouga.Authentication.UserAuthentication;
import com.salinas.salinasdovouga.Model.User;
import com.salinas.salinasdovouga.Users.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Add other necessary fields

    @FXML
    private void handleLoginButton(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User authenticatedUser = UserAuthentication.loginUser(username, password);

        if (authenticatedUser != null) {
            // Login successful
            openDashboard(authenticatedUser.getUserType(), event);
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private void openDashboard(UserType userType, ActionEvent event) {
        try {
            String fxmlPath = getDashboardFXMLPath(userType);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Assuming you have a global stage variable in your application class
            Stage stage = new Stage();
            stage.setTitle(userType.toString() + " Dashboard");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Close the login window
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDashboardFXMLPath(UserType userType) {
        switch (userType) {
            case ADMIN:
                return "/com/salinas/salinasdovouga/admin.fxml";
            case PRODUCTION_MANAGER:
                return "/com/salinas/salinasdovouga/production_manager.fxml";
            case SALES_MANAGER:
                return "/com/salinas/salinasdovouga/sales_manager.fxml";
            case CUSTOMER:
                return "/com/salinas/salinasdovouga/customer_dashboard.fxml";
            default:
                // Handle other cases or throw an exception
                return null;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleRegisterButton() {
        // Load the registration screen
        // Load the registration screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/salinas/salinasdovouga/registration.fxml"));

        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
