module com.dentalcare.salinasdovouga {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens com.salinas.salinasdovouga to javafx.fxml;
    opens com.salinas.salinasdovouga.Controllers to javafx.fxml;
    opens com.salinas.salinasdovouga.Model to javafx.base; // Add this line
    exports com.salinas.salinasdovouga;
    exports com.salinas.salinasdovouga.Util;
    opens com.salinas.salinasdovouga.Util to javafx.fxml;

    opens com.salinas.salinasdovouga.UI to javafx.fxml;
    exports com.salinas.salinasdovouga.UI;
    exports com.salinas.salinasdovouga.Controllers.ProductionActions;
    opens com.salinas.salinasdovouga.Controllers.ProductionActions to javafx.fxml;
    exports com.salinas.salinasdovouga.Controllers;
}
