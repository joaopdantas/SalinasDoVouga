// ProductCatalogController.java

package com.salinas.salinasdovouga.Controllers.ProductionActions;

import com.salinas.salinasdovouga.Model.ProductType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ProductCatalogController {

    @FXML
    private TableView<ProductType> productTableView;

    @FXML
    private void initialize() {
        // Initialize the table view with product data
        initializeProductTableView();
    }

    private void initializeProductTableView() {
        ObservableList<ProductType> productTypes = FXCollections.observableArrayList(ProductType.values());
        productTableView.setItems(productTypes);
    }
}
