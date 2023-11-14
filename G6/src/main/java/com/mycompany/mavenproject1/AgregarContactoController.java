/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.Contact;
import com.mycompany.contacts.Util;
import ec.edu.espol.TDAs.ArrayList;
import ec.edu.espol.TDAs.DoublyLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author laboratorio
 */
public class AgregarContactoController implements Initializable {

    @FXML
    private VBox list_contact;
    private ArrayList<Contact> contactos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.contactos = Util.listaContacto();
        list_contact.getChildren();
        for (Contact c : this.contactos) {
            HBox contactIndi = new HBox();
            Text name = new Text(c.getName());
            name.setFont(new Font(14));
            CheckBox checkBox = new CheckBox("Anadir contacto");
//             name.setFont(new Font(1));
            contactIndi.getChildren().addAll(name, checkBox);
            list_contact.getChildren().add(contactIndi);
        }
    }

    @FXML
    private void guardadoContact(MouseEvent event) {
        ArrayList<Contact> nuevaLisr = new ArrayList<>();
        for (int i = 0; i < list_contact.getChildren().size(); i++) {
            HBox contactIndi = (HBox) list_contact.getChildren().get(i);
            CheckBox checkBox = (CheckBox) contactIndi.getChildren().get(1);
            if (checkBox.isSelected()) {
                nuevaLisr.add(contactos.get(i));
            }
        }       
        Util.saveListToFile("ContactosSeleccionados.ser", nuevaLisr); 
    }

  
}
