/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

    import com.mycompany.contacts.Contact;
import com.mycompany.contacts.RelatedContact;
import com.mycompany.contacts.TipoRelacion;
    import com.mycompany.contacts.Util;
    import ec.edu.espol.TDAs.ArrayList;
    import java.net.URL;
    import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Button;
    import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private static ArrayList<RelatedContact> relatedContacts;
    private static TipoRelacion tipoRel = TipoRelacion.ninguno;
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
        relatedContacts = new ArrayList<>();
        // Inicializa la lista de contactos
        RelatedContact rc;
        for (Contact c : this.contactos) {
            HBox contactIndi = new HBox(20);
            Text name = new Text(c.getName());
            name.getStyleClass().add("text-field");
            CheckBox checkBox = new CheckBox("ADD");
            
            ObservableList<String> options = FXCollections.observableArrayList(
                    TipoRelacion.familiar.toString(),
                    TipoRelacion.amistad.toString(),
                    TipoRelacion.asistente.toString(),
                    TipoRelacion.asociacion.toString(),
                    TipoRelacion.colega.toString(),
                    TipoRelacion.sitio_laboral.toString(),
                    TipoRelacion.ninguno.toString()
            );

            // Crear un ComboBox y configurarlo con la lista de opciones
            ComboBox<String> tipoRelacion = new ComboBox<>(options);
            tipoRelacion.setPromptText("Selecciona una opción");
            
            
            // Manejar eventos de selección
            tipoRelacion.setOnAction(event -> {
                String selectedOption = tipoRelacion.getSelectionModel().getSelectedItem();
                tipoRel = TipoRelacion.parse(selectedOption);                
            });
            rc = new RelatedContact(tipoRel.toString(),c);
            
            
            contactIndi.getChildren().addAll(name, tipoRelacion,checkBox);
            contactIndi.getStyleClass().add("blackbackgorund");
            list_contact.getChildren().add(contactIndi);
            System.out.println(rc);
            relatedContacts.add(rc);
            
        }
    }

    @FXML
    private void saveContact(MouseEvent event) {
        ArrayList<RelatedContact> nuevaList = new ArrayList<>();
        for (int i = 0; i < list_contact.getChildren().size(); i++) {
            HBox contactIndi = (HBox) list_contact.getChildren().get(i);
            CheckBox checkBox = (CheckBox) contactIndi.getChildren().get(2);
            if (checkBox.isSelected()) {
                nuevaList.add(relatedContacts.get(i));
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
