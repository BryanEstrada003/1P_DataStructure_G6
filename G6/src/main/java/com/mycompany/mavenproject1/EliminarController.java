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
public class EliminarController implements Initializable {

    @FXML
    private VBox list_contact;
    private DoublyLinkedList<Contact> contactos;
    private String cssFile;
    private static DoublyLinkedList<Contact> toEliminate;
    @FXML
    private AnchorPane principal_page;
    @FXML
    private Button save;
    @FXML
    private ScrollPane scrollpane;
    private String id_registro_E;
    private String name_archivo;

//    public String getId_registro_E() {
//        return id_registro_E;
//    }
//
//    public void setId_registro_dE() {
//        Id_register id_re = null;
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("PassInformation/id_re_add.ser"))) {
//            id_re = (Id_register) in.readObject();
//            System.out.println("id_re");
//        } catch (IOException ioe) {
//            System.out.println("Aquí esta el error al momento de leer el PassInformation/id_re_add.ser");
//        } catch (ClassNotFoundException c) {
//
//        }
//        this.id_registro_E = id_re.getId_register();
//        name_archivo = "ContactosSeleccionados/ContactosSeleccionados" + id_registro_E + ".ser";
//        try {
//            File file = new File("PassInformation/id_re_add.ser");
//            if (file.exists()) {
//                file.delete();
//            }
//        } catch (Exception e) {
//
//        }
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.contactos = Util.listaContacto2();
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
        System.out.println(id_registro_E);

        toEliminate = new DoublyLinkedList<>();
        // Inicializa la lista de contactos

        for (Contact c : this.contactos) {
            HBox contactoIndi = new HBox(20);
            Text nombre = new Text(c.getName());
            nombre.getStyleClass().add("text-field");
            CheckBox checkBox = new CheckBox();


            Comparator<Contact> cmp = (Contact c1, Contact c2) -> {
                return c1.getID_re().compareTo(c2.getID_re());
            };
            

            

            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    // Acciones a realizar cuando el estado del CheckBox cambia

                    if (checkBox.isSelected()) {

                        int index = toEliminate.indexOf(c, cmp);
                        if (index != -1 && index != -2) {
                            toEliminate.remove(c, cmp);

                        }
                        toEliminate.add(c);
                        System.out.println(c);
                    }

                }
            });

            contactoIndi.getChildren().addAll(checkBox, nombre);
            contactoIndi.getStyleClass().add("blackbackgorund");
            list_contact.getChildren().add(contactoIndi);

        }

    }

    @FXML
    private void saveContact(MouseEvent event) {

       
    }
}
