package com.salinas.salinasdovouga;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main FXML file (e.g., login screen)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Salinas do Vouga Management - Admin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
