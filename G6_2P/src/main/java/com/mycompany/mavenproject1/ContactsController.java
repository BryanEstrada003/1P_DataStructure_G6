/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.Contact;
import com.mycompany.contacts.TipoContact;
import com.mycompany.contacts.User;
import com.mycompany.contacts.Util;
import ec.edu.espol.TDAs.ArrayList;
import ec.edu.espol.TDAs.DoublyLinkedList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import java.util.Optional;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class ContactsController implements Initializable {

    @FXML
    private AnchorPane principal_page;
    @FXML
    private AnchorPane header;
    @FXML
    private Label title_contacts;
    @FXML
    private ImageView newcontact;
    private ImageView lupa;
    @FXML
    private Pane show_contact;
    private ImageView regresar;
    @FXML
    private ImageView salir;
    @FXML
    private ImageView groups;
    @FXML
    private ImageView profile_picture;
    @FXML
    private Label name_lastname;
    private User owner;
    private TextField search;
    @FXML
    private VBox Vbox_contacts;
    @FXML
    private ImageView subir;
    @FXML
    private ImageView bajar;
    @FXML
    private ImageView profile_picture1;
    @FXML
    private Label name_lastname1;
    @FXML
    private VBox clasification1;
    @FXML
    private ImageView profile_picture2;
    @FXML
    private Label name_lastname2;
    @FXML
    private VBox clasification2;
    @FXML
    private ImageView profile_picture3;
    @FXML
    private Label name_lastname3;
    @FXML
    private VBox clasification3;
    @FXML
    private ImageView profile_picture4;
    @FXML
    private Label name_lastname4;
    @FXML
    private VBox clasification4;
    @FXML
    private ImageView profile_picture5;
    @FXML
    private Label name_lastname5;
    @FXML
    private VBox clasification5;
    private Contact contacto1;
    private Contact contacto2;
    private Contact contacto3;
    private Contact contacto4;
    private Contact contacto5;
    private DoublyLinkedList<Contact> contactos;
    private ListIterator<Contact> ite;
    @FXML
    private ComboBox<String> cbSortby;
    @FXML
    private ComboBox<String> cbFlType;
    @FXML
    private ImageView imFil;
    @FXML
    private CheckBox chfilEmail;
    @FXML
    private CheckBox socialNe;

    /**
     * Initializes the controller class.
     */
    public User getOwner() {
        return owner;

    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_owner();   
        imFil.setImage(new Image("Iconos/filtrar.png"));
        newcontact.setImage(new Image("Iconos/crear_contacto.png"));
        groups.setImage(new Image("Iconos/flecha-izquierda.png"));
        salir.setImage(new Image("Iconos/salir.png"));
        principal_page.getStyleClass().add("blackbackgorund");
        header.getStyleClass().add("blackbackgorund");
        show_contact.getStyleClass().add("blackbackgorund");
        subir.setImage(new Image("Iconos/subir.png"));
        bajar.setImage(new Image("Iconos/bajar.png"));
        profile_picture.setPreserveRatio(true);
        profile_picture.setSmooth(true);
        profile_picture.setFitWidth(80);
        profile_picture.setFitHeight(80);
        profile_picture.setImage(new Image("Profile_pictures/" + owner.getPersonal_user() + "/" + owner.getPersonal_user() + ".png"));
        name_lastname.setText(owner.getName() + " " + owner.getLastname());
//        profile_picture.setFitWidth(80); 
//        profile_picture.setFitHeight(80);
//        profile_picture.setPreserveRatio(true); 
//        profile_picture.setSmooth(true); 
//
//        if (owner != null) {
//            name_lastname.setText(Util.title(owner.getName()) + "  " + Util.title(owner.getLastname()));
//        } else {
//            name_lastname.setText("Name  Lastname");
//        }
//
//        try {
//            String personal_image = "Profile_pictures/" + owner.getPersonal_user() + "/" + owner.getPersonal_user() + ".png";
//            System.out.println(personal_image);
//            profile_picture.setImage(new Image(personal_image));
//        } catch (Exception e) {
//            profile_picture.setImage(new Image("Iconos/cambiar_foto.png"));
//        }
        try {
            this.contactos = Util.listaContacto2();//ListaOriginal sin aplicar Filtros
            Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
        } catch (Exception e) {
            System.out.println("Empty List");
        }

        if (this.contactos.size() < 5) {
            this.ite = this.contactos.listIterator();
            ArrayList<Contact> nuevaListg = new ArrayList<>();
            for (int i = 0; i < this.contactos.size(); i++) {
                nuevaListg.add(ite.next());
            }
            int[] arreglopos = {0, 1, 2, 3, 4};
            setInterfazCont(arreglopos, nuevaListg);
        } else {
            this.ite = this.contactos.listIterator();
            ArrayList<Contact> nuevaListg = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                nuevaListg.add(ite.next());
            }
            int[] arreglopos = {0, 1, 2, 3, 4};
            setInterfazCont(arreglopos, nuevaListg);

        }
        cbSortby.getItems().add("Name and LastName");
        cbSortby.getItems().add("Number of Telephones");
        cbSortby.getItems().add("Type and Name");
        cbFlType.setValue("All");
        cbFlType.getItems().add("Person");
        cbFlType.getItems().add("Company");
        cbFlType.getItems().add("All");

    }

    public void funcioneli() {
        for (int i = 0; i < 41; i++) {
            this.contactos.removeLast();
        }
    }

    public void setInterfazCont(int[] posic, ArrayList<Contact> nuevaListg) {
        name_lastname1.setText("");
        name_lastname2.setText("");
        name_lastname3.setText("");
        name_lastname4.setText("");
        name_lastname5.setText("");
        int indice1 = posic[0], indice2 = posic[1], indice3 = posic[2], indice4 = posic[3], indice5 = posic[4];
        try {
            this.contacto1 = nuevaListg.get(indice1);
            name_lastname1.setText(Util.identificarContact(this.contacto1));
            this.contacto2 = nuevaListg.get(indice2);
            name_lastname2.setText(Util.identificarContact(this.contacto2));
            this.contacto3 = nuevaListg.get(indice3);
            name_lastname3.setText(Util.identificarContact(this.contacto3));
            this.contacto4 = nuevaListg.get(indice4);
            name_lastname4.setText(Util.identificarContact(this.contacto4));
            this.contacto5 = nuevaListg.get(indice5);
            name_lastname5.setText(Util.identificarContact(this.contacto5));
        } catch (Exception e) {
            System.out.println("Empty List");
        }
    }

    @FXML
    private void salir(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");
        alert.setHeaderText("ARE YOU SURE YOU WANT TO LEAVE COMPLETELY ");
        alert.setContentText("CONFIRMATION");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("NO");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    private void changeInterfaz(Contact c) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowContact.fxml"));
            Parent root = loader.load();

            ShowContactController controller = loader.getController();
            controller.setContact(c);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/showContact.css");
            Stage stage = (Stage) header.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void set_owner() {
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("User_login.ser"))) {
            owner = (User) in.readObject();

        } catch (IOException ioe) {

        } catch (ClassNotFoundException c) {

        }
    }
    private int nVez = 1;
    private boolean clickDown = false, clickup = false;

    @FXML
    private void subir(MouseEvent event) {
        if (this.contactos.size() >= 5) {
            int cont = 0;
            ArrayList<Contact> nuevaList = new ArrayList<>();
            if (nVez == 1 || clickDown == true) {
                nuevaList.clear();
                while (cont < 11) {
                    nuevaList.addFirst(ite.previous());
                    cont++;
                }
            } else {
                nuevaList.clear();
                while (cont < 5) {
                    nuevaList.addFirst(ite.previous());
                    cont++;
                }
            }
            nVez++;
            clickDown = false;
            clickup = true;
            int[] arreglopos = {0, 1, 2, 3, 4};
            setInterfazCont(arreglopos, nuevaList);
        }

    }

    @FXML
    private void bajar(MouseEvent event) {
        if (this.contactos.size() >= 5) {
            int cont = 0;
            ArrayList<Contact> nuevaList = new ArrayList<>();
            if (clickup == true) {
                nuevaList.clear();
                while (cont < 11) {
                    nuevaList.addFirst(ite.next());
                    cont++;
                }
                int[] arreglopos = {4, 3, 2, 1, 0};
                setInterfazCont(arreglopos, nuevaList);
            } else {
                nuevaList.clear();
                while (cont < 5) {
                    nuevaList.add(ite.next());
                    cont++;
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaList);
            }
            nVez++;
            clickDown = true;
            clickup = false;
        }
    }

    @FXML
    private void agregar_contacto(MouseEvent event) throws IOException {
//        DoublyLinkedList<Contact> lista_contactos = Util.listaContacto2();
//        Contact c1 = lista_contactos.get(0);
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Contact_edit.ser"))) {
//            out.writeObject(c1);
//        } catch (IOException ioe) {
//
//        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newcontact.fxml"));
        Parent root = loader.load();
        NewcontactController newContactController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) subir.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void showContact1(MouseEvent event) {
        changeInterfaz(this.contacto1);
    }

    @FXML
    private void showContact2(MouseEvent event) {
        changeInterfaz(this.contacto2);
    }

    @FXML
    private void showContact3(MouseEvent event) {
        changeInterfaz(this.contacto3);
    }

    @FXML
    private void showContact4(MouseEvent event) {
        changeInterfaz(this.contacto4);
    }

    @FXML
    private void showContact5(MouseEvent event) {
        changeInterfaz(this.contacto5);
    }


    @FXML
    private void selectSort(ActionEvent event) {
        try {
            ComboBox cbSort = (ComboBox) event.getSource();
            String criterio = (String) cbSort.getValue();
            if (criterio.equals("Type and Name")) {
                this.contactos = Util.orderForNameAndType(contactos);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }

                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            } else if (criterio.equals("Name and LastName")) {
                this.contactos = Util.orderForName(contactos);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();

                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            } else {
                this.contactos = Util.orderForTelephoneSize(contactos);
                this.ite = null;
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();

                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            }
        } catch (Exception e) {
            System.out.println("Empty List");
        }

    }
    private String criterioFIl = "all";

    @FXML
    private void filtType(ActionEvent event) {
        ComboBox cbfi = (ComboBox) event.getSource();
        String criterio = (String) cbfi.getValue();
        criterio = criterio.toLowerCase();
        criterioFIl = criterio;

    }
    private boolean chBoxemail;
    private boolean chBoxSocial;
    private DoublyLinkedList<Contact> listCompany;
    private DoublyLinkedList<Contact> listPerson;
    public Comparator<Contact> cmpID = (Contact c1, Contact c2) -> c1.getID_re().compareTo(c2.getID_re());

    @FXML
    private void filtrar(MouseEvent event) {
        try {
            if (criterioFIl.equals("person")) {
                this.contactos = Util.listaContacto2();

                this.contactos = Util.filterByTypeContact(this.contactos, TipoContact.person);
                listPerson = Util.filterByTypeContact(this.contactos, TipoContact.person);
                this.ite = null;
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            } else if (criterioFIl.equals("company")) {
                this.contactos = Util.listaContacto2();

                listCompany = Util.filterByTypeContact(this.contactos, TipoContact.company);
                System.out.println(listCompany.size());
                this.contactos = Util.filterByTypeContact(this.contactos, TipoContact.company);

                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);

            } else if (criterioFIl.equals("all")) {//caso cuando solamente elija uno de los 2 criterios
                this.contactos = Util.listaContacto2();

                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            }

            if (criterioFIl.equals("person") && chBoxemail == true) {
                this.contactos = Util.listaContacto2();

                this.contactos = quitarRepe(listPerson);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            } else if (criterioFIl.equals("company") && chBoxemail == true) {

                this.contactos = Util.listaContacto2();

                this.contactos = quitarRepe(listCompany);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);

            } else if ((criterioFIl.equals("all")) && chBoxemail == true) {

                this.contactos = Util.listaContacto2();

                this.contactos = Util.filterIfEmails(contactos);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            }

            if (criterioFIl.equals("person") && chBoxSocial == true) {
                this.contactos = Util.listaContacto2();

                this.contactos = quitarRepe2(listPerson);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            } else if (criterioFIl.equals("company") && chBoxSocial == true) {
                System.out.println(criterioFIl);
                this.contactos = Util.listaContacto2();

                this.contactos = quitarRepe2(listCompany);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);

            } else if ((criterioFIl.equals("all")) && chBoxSocial == true) {

                this.contactos = Util.listaContacto2();

                this.contactos = Util.filterIfEmails(contactos);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            }

            if ((criterioFIl.equals("person")) && chBoxemail == true && chBoxSocial == true) {

                this.contactos = Util.listaContacto2();

                this.contactos = quitarRepeAllTipo(this.listPerson);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            } else if ((criterioFIl.equals("company")) && chBoxemail == true && chBoxSocial == true) {
                this.contactos = Util.listaContacto2();

                this.contactos = quitarRepeAllTipo(this.listCompany);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            } else if ((criterioFIl.equals("all")) && chBoxemail == true && chBoxSocial == true) {

                this.contactos = Util.listaContacto2();

                this.contactos = quitarRepeAllTipo(this.contactos);
                Util.serializeDoublyLinkedList(this.contactos, "ContactsOrderFilter");
                this.ite = null;
                this.ite = this.contactos.listIterator();
                ArrayList<Contact> nuevaListg = new ArrayList<>();
                if (this.contactos.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        nuevaListg.add(ite.next());
                    }
                } else {
                    for (int i = 0; i < this.contactos.size(); i++) {
                        nuevaListg.add(ite.next());
                    }
                }
                int[] arreglopos = {0, 1, 2, 3, 4};
                setInterfazCont(arreglopos, nuevaListg);
            }
        } catch (Exception e) {
        }

    }

    public DoublyLinkedList<Contact> quitarRepeAllTipo(DoublyLinkedList listaAny) {

        DoublyLinkedList<Contact> listsin = new DoublyLinkedList<>();
        try {
            listaAny.addAll(Util.filterIfEmails(listaAny));
            listaAny.addAll(Util.filterIfSocialMedia(listaAny));
            ListIterator<Contact> iteradorConjun = listaAny.listIterator();
            TreeSet<Contact> quitarRepetido = new TreeSet<>(cmpID);

            for (int i = 0; i < listaAny.size(); i++) {
                quitarRepetido.add(iteradorConjun.next());
            }

            for (Contact c : quitarRepetido) {
                listsin.add(c);
            }
        } catch (Exception e) {
        }

        return listsin;
    }

    public DoublyLinkedList<Contact> quitarRepe(DoublyLinkedList listaAny) {

        DoublyLinkedList<Contact> listsin = new DoublyLinkedList<>();
        try {
            listaAny.addAll(Util.filterIfEmails(listaAny));
            ListIterator<Contact> iteradorConjun = listaAny.listIterator();
            TreeSet<Contact> quitarRepetido = new TreeSet<>(cmpID);

            for (int i = 0; i < listaAny.size(); i++) {
                quitarRepetido.add(iteradorConjun.next());
            }

            for (Contact c : quitarRepetido) {
                listsin.add(c);
            }
        } catch (Exception e) {
        }

        return listsin;
    }

    public DoublyLinkedList<Contact> quitarRepe2(DoublyLinkedList listaAny) {
        DoublyLinkedList<Contact> listsin = new DoublyLinkedList<>();
        try {
            listaAny.addAll(Util.filterIfSocialMedia(listaAny));
            ListIterator<Contact> iteradorConjun = listaAny.listIterator();
            TreeSet<Contact> quitarRepetido = new TreeSet<>(cmpID);

            for (int i = 0; i < listaAny.size(); i++) {
                quitarRepetido.add(iteradorConjun.next());
            }

            for (Contact c : quitarRepetido) {
                listsin.add(c);
            }
        } catch (Exception e) {
        }

        return listsin;
    }

    @FXML
    private void actionFilEmail(ActionEvent event) {
        boolean estaSeleccionado = chfilEmail.isSelected();

        if (estaSeleccionado) {
            chBoxemail = true;
        }
    }

    @FXML
    private void btnSocialFil(ActionEvent event) {
        boolean estaSeleccionado = socialNe.isSelected();

        if (estaSeleccionado) {
            chBoxSocial = true;
        }
    }

    @FXML
    private void SelectiontoEliminate(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Eliminar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) principal_page.getScene().getWindow();
        stage.setScene(scene);
    }

}
