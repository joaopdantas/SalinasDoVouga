package com.salinas.salinasdovouga.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class OrdersController {

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private ListView<String> ordersListView;  // Defina o tipo apropriado para o ListView

    @FXML
    void handleShowOrders(ActionEvent event) {
        // Lógica para exibir as encomendas selecionadas pelo cliente
    }


}



