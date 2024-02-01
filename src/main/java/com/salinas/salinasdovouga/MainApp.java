package com.salinas.salinasdovouga;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main FXML file (e.g., login screen)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Register");

            // Set Scene size based on the preferred size of the loaded FXML root
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Adapt the stage size to the preferred size of the loaded FXML root
            stage.sizeToScene();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
