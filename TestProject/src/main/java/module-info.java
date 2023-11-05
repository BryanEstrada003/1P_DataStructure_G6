module com.mycompany.testproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.testproject to javafx.fxml;
    exports com.mycompany.testproject;
}
