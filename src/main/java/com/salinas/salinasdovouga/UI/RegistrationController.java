package com.salinas.salinasdovouga.UI;

import com.salinas.salinasdovouga.Authentication.UserAuthentication;
import com.salinas.salinasdovouga.Model.User;
import com.salinas.salinasdovouga.Users.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField citizenCardNumberField;

    @FXML
    private TextField fiscalNumberField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField localityField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private void handleRegisterButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String citizenCardNumber = citizenCardNumberField.getText();
        String fiscalNumber = fiscalNumberField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String locality = localityField.getText();
        String selectedRole = roleComboBox.getValue();
        String formattedRole = selectedRole.toUpperCase().replace(" ", "_");

        User newUser = new User(username, password, name, citizenCardNumber, fiscalNumber, phone, address, locality, UserType.valueOf(formattedRole));

        if (UserAuthentication.registerUser(newUser)) {
            showAlert("Registration Successful", "You have successfully registered!");
            // Optionally, you can navigate to another view (e.g., login)
        } else {
            showAlert("Registration Failed", "Username already exists. Please choose another username.");
        }
    }

    public void configureComboBoxForCustomer() {
        roleComboBox.getItems().setAll("CUSTOMER");
        roleComboBox.setValue("CUSTOMER");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
