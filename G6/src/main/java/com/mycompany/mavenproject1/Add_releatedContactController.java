    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
     */
    package com.mycompany.mavenproject1;

    import com.mycompany.contacts.Contact;
import com.mycompany.contacts.OnContactsSavedListener;
    import com.mycompany.contacts.Util;
    import ec.edu.espol.TDAs.ArrayList;
    import java.net.URL;
    import java.util.ResourceBundle;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Button;
    import javafx.scene.control.CheckBox;
    import javafx.scene.control.ScrollPane;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.scene.text.Font;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class Add_releatedContactController implements Initializable {

    @FXML
    private VBox list_contact;
    private ArrayList<Contact> contactos;
    private String cssFile;
    @FXML
    private AnchorPane principal_page;
    @FXML
    private Button save;
    @FXML
    private ScrollPane scrollpane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.contactos = Util.listaContacto();
        cssFile = getClass().getResource("/styles/login.css").toExternalForm();
        principal_page.getStylesheets().add(cssFile);
        scrollpane.getStylesheets().add(cssFile);
        list_contact.getStylesheets().add(cssFile);
        principal_page.getStyleClass().add("blackbackground");
        scrollpane.getStyleClass().add("blackbackground");
        save.getStyleClass().add("button_");
        list_contact.getStyleClass().add("blackbackground");
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Inicializa la lista de contactos
        for (Contact c : this.contactos) {
            HBox contactIndi = new HBox(20);
            Text name = new Text(c.getName());
            name.getStyleClass().add("text-field");
            CheckBox checkBox = new CheckBox("ADD");
            contactIndi.getChildren().addAll(name, checkBox);
            contactIndi.getStyleClass().add("blackbackgorund");
            list_contact.getChildren().add(contactIndi);
        }
    }

    @FXML
    private void saveContact(MouseEvent event) {
        ArrayList<Contact> nuevaList = new ArrayList<>();
        for (int i = 0; i < list_contact.getChildren().size(); i++) {
            HBox contactIndi = (HBox) list_contact.getChildren().get(i);
            CheckBox checkBox = (CheckBox) contactIndi.getChildren().get(1);
            if (checkBox.isSelected()) {
                nuevaList.add(contactos.get(i));
            }
        }
         System.out.println(nuevaList.size());
        Util.saveListToFile("ContactosSeleccionados.ser", nuevaList);
        ArrayList<Contact> nuevaLista = Util.readListFromFileSer("ContactosSeleccionados.ser");
        System.out.println(nuevaLista.size());
        Stage stage = (Stage) save.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }
}

