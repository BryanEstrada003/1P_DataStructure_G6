/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.Contact;
import com.mycompany.contacts.Id_register;
import com.mycompany.contacts.RelatedContact;
import com.mycompany.contacts.TipoRelacion;
import com.mycompany.contacts.Util;
import ec.edu.espol.TDAs.ArrayList;
import ec.edu.espol.TDAs.DoublyLinkedList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class Add_ReledController implements Initializable {

    @FXML
    private VBox list_contact;
    private DoublyLinkedList<Contact> contactos;
    private String cssFile;
    private static DoublyLinkedList<RelatedContact> relatedContacts;
    private static DoublyLinkedList<RelatedContact> relatedContactsCheck;
    private static String tipoSeleccionado;
    @FXML
    private AnchorPane page_principal;
    @FXML
    private Button savec;
    @FXML
    private ScrollPane scrollpane;
    private String id_registro_E;
    private String name_archivo;

    public String getId_registro_E() {
        return id_registro_E;
    }

    public void setId_registro_dE() {
        Id_register id_re = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("PassInformation/id_re_add.ser"))) {
            id_re = (Id_register) in.readObject();
            System.out.println("id_re");
        } catch (IOException ioe) {
            System.out.println("Aquí esta el error al momento de leer el PassInformation/id_re_add.ser");
        } catch (ClassNotFoundException c) {

        }
        this.id_registro_E = id_re.getId_register();
        name_archivo = "ContactosSeleccionados/ContactosSeleccionados" + id_registro_E + ".ser";
        try {
            File file = new File("PassInformation/id_re_add.ser");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoSeleccionado = null;

        setId_registro_dE();
        this.contactos = Util.listaContacto2();
        cssFile = getClass().getResource("/styles/login.css").toExternalForm();
        page_principal.getStylesheets().add(cssFile);
        scrollpane.getStylesheets().add(cssFile);
        list_contact.getStylesheets().add(cssFile);
        page_principal.getStyleClass().add("blackbackground");
        scrollpane.getStyleClass().add("blackbackground");
        savec.getStyleClass().add("button_");
        list_contact.getStyleClass().add("blackbackground");
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        System.out.println(id_registro_E);

        relatedContactsCheck = new DoublyLinkedList<>();
        // Inicializa la lista de contactos

        for (Contact c : this.contactos) {
            HBox contactoIndi = new HBox(20);
            Text nombre = new Text(c.getName());
            nombre.getStyleClass().add("text-field");
            CheckBox checkBox = new CheckBox();

            ObservableList<String> opciones = FXCollections.observableArrayList(
                    TipoRelacion.familiar.toString(),
                    TipoRelacion.amistad.toString(),
                    TipoRelacion.asistente.toString(),
                    TipoRelacion.asociacion.toString(),
                    TipoRelacion.colega.toString(),
                    TipoRelacion.sitio_laboral.toString(),
                    TipoRelacion.ninguno.toString()
            );

            // Crear un ComboBox y configurarlo con la lista de opciones
            ComboBox<String> tipoRelacion = new ComboBox<>(opciones);
            tipoRelacion.setPromptText("Select an option");
            tipoRelacion.setOnAction(event -> {
                tipoSeleccionado = tipoRelacion.getValue();
                System.out.println("Tipo Seleccionado: " + tipoSeleccionado);
            });

            Comparator<RelatedContact> cmp = (RelatedContact c1, RelatedContact c2) -> {
                return c1.getContact().getID_re().compareTo(c2.getContact().getID_re());
            };
            File file = new File(name_archivo);
            if (file.exists()) {
                relatedContacts = Util.readListFromFileSer(name_archivo);
                if (!relatedContacts.isEmpty()) {
                int index = relatedContacts.indexOf(new RelatedContact("", c), cmp);
                if (index != -1 && index != -2) {
                    RelatedContact r = relatedContacts.get(index);

                    tipoRelacion.getSelectionModel().select(r.getContactType());
                    checkBox.setSelected(true);
                    relatedContactsCheck.add(r);
                }

            }
            }

            

            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    // Acciones a realizar cuando el estado del CheckBox cambia
                    RelatedContact rc = new RelatedContact(tipoSeleccionado, c);

                    if (checkBox.isSelected()) {

                        int index = relatedContactsCheck.indexOf(rc, cmp);
                        if (index != -1 && index != -2) {
                            relatedContactsCheck.remove(rc, cmp);

                        }
                        relatedContactsCheck.add(rc);
                        System.out.println(rc);
                    }

                }
            });

            contactoIndi.getChildren().addAll(checkBox, nombre, tipoRelacion);
            contactoIndi.getStyleClass().add("blackbackgorund");
            list_contact.getChildren().add(contactoIndi);

        }

        tipoSeleccionado = null;
    }

    @FXML
    private void savecContact(MouseEvent event) {

        System.out.println(relatedContactsCheck.size());
        if (!relatedContactsCheck.isEmpty()) {
            for (RelatedContact n : relatedContactsCheck) {
                System.out.println(n);
            }
        }

        System.out.println(id_registro_E);
        Util.<RelatedContact>savecListToFile(name_archivo, relatedContactsCheck);
        Stage stage = (Stage) savec.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }
}

