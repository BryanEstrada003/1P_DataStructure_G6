module ec.edu.espol.treeview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.treeview to javafx.fxml;
    exports ec.edu.espol.treeview;
}
