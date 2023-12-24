module ec.edu.espol.group_06_2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.group_06_2p to javafx.fxml;
    exports ec.edu.espol.group_06_2p;
}
