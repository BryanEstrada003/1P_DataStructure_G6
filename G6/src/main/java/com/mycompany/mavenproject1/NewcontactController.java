/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.Contact;
import com.mycompany.contacts.Email;
import com.mycompany.contacts.Telephone;
import com.mycompany.contacts.Address;
import com.mycompany.contacts.Date;
import com.mycompany.contacts.Person;
import com.mycompany.contacts.RelatedContact;
import com.mycompany.contacts.SocialMedia;
import com.mycompany.contacts.TipoContact;
import com.mycompany.contacts.Util;
import ec.edu.espol.TDAs.ArrayList;
import ec.edu.espol.TDAs.DoublyLinkedList;
import ec.edu.espol.TDAs.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.function.BiFunction;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class NewcontactController implements Initializable {

    @FXML
    private AnchorPane newcontact_page;
    @FXML
    private ScrollPane principal;
    private VBox contentBox;
    private String cssFile;
    private String id_registro;
    private int number_photos;
    private String directoryPath;
    private String tipo = "";
    private TextField last_d;
    private DoublyLinkedList<Contact> lista_contacto;
    private HBox add_RC;
    private Label process;
    private Boolean edit_new ;
    private int width = 730;
    private int heigth = 730;
    private int width_container = 700;
    private Map<String,Integer> indices_ContentBox;
    private ComboBox<String> typeDropdown;
    private String id_registro_E;
    private Contact actual_contact;
    public Map getIndices_ContentBox() {
        return indices_ContentBox;
    }

    public VBox getContentBox() {
        return contentBox;
    }

    public void setContentBox(VBox contentBox) {
        this.contentBox = contentBox;
    }

    public String getId_registro_E() {
        return id_registro_E;
    }

    public void setId_registro_E(String id_registro_E) {
        this.id_registro_E = id_registro_E;
    }

    public Contact getContactbyId(String id_registro_E){
        DoublyLinkedList<Contact> lista_contactos  =  Util.listaContacto2();
        for(Contact c1 : lista_contactos)
            if(c1.getID_re().compareTo(id_registro_E) == 0){
                return c1;
            }
        return null;
    }
    
    public void setIndices_ContentBox() {
        indices_ContentBox= new LinkedHashMap<>();
        indices_ContentBox.put("Container_Name_Last",2);
        indices_ContentBox.put("container_AddPhone",3);
        indices_ContentBox.put("container_AddEmail",4);
        indices_ContentBox.put("container_address",5);
        indices_ContentBox.put("container_SocialMedia",6);
        indices_ContentBox.put("container_AddImportantsDate",8);
        indices_ContentBox.put("container_AddRelatedContacts",9);
    }
    
    public String getNextIdRegistro(){
        DoublyLinkedList<Contact> lista_contactos  =  Util.listaContacto2();
        System.out.println(lista_contactos.size());
        Queue<String> orden = new PriorityQueue<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }  
        });
        for(Contact c: lista_contactos ){
            orden.offer(c.getID_re());
        }
        String actual = orden.poll();
        return nextId(actual);  
    }
    public static String nextId(String currentId) {
        
        if (currentId == null || currentId.length() != 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "INVALID ID");
            alert.setHeaderText("WARNING");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
        else{
            char prefix = currentId.charAt(0);
            int number = Integer.parseInt(currentId.substring(1));
            number++;
            if (number > 9999) {
                number = 1; 
                prefix++; 
            }
            return prefix + String.format("%04d", number);
        }
        return null;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_registro = getNextIdRegistro();
        System.out.println(id_registro);
        number_photos = 0;
        setIndices_ContentBox();
        cssFile = getClass().getResource("/styles/login.css").toExternalForm();
        principal.getStylesheets().add(cssFile);
        newcontact_page.getStylesheets().add(cssFile);

        principal.getStyleClass().add("blackbackground");
        newcontact_page.getStyleClass().add("blackbackground");

        contentBox = new VBox();
        contentBox.setMinSize(width, 751); 
        contentBox.setMaxWidth(width);
        contentBox.getStyleClass().add("blackbackground");
        
        // si hay un contacto se pone true 
        edit_new = false;

        contentBox.setAlignment(Pos.TOP_CENTER);

        Container_Top();
        Container_Header();
        Container_Name_Last();
        container_AddPhone();
        container_AddEmail();
        container_address();
        container_SocialMedia();
        createHBox_photo();
        container_AddImportantsDate();
        container_AddRelatedContacts("ADD RELEATED CONTACT","");
        SaveContact();
        
        // Solo actualiza no agrega
        updateRelatedContact();
        principal.setContent(contentBox);
        principal.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        principal.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        

        for(Node e :contentBox.getChildren() ){
            System.out.println(e);
        }
        System.out.println("El tamaño de contentBox es "+ contentBox.getChildren().size());

        // Esto tenemos que cambiar
        if (actual_contact != null ){
            lista_contacto = Util.changetoDoublyLinkedList(actual_contact.getRelatedContacts());
        }

    }

    private void Container_Top() {

        HBox top = new HBox();
        top.getStyleClass().add("blackbackgorund");
        top.setSpacing(20);
        ImageView im1 = new ImageView(new Image("Iconos/flecha-izquierda.png"));
        im1.setFitHeight(60);
        im1.setFitWidth(60);
        im1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("contacts.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) principal.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException ex) {
                }
            }

        });
        Text titulo = new Text();
        titulo.getStyleClass().add("titutlo");
        titulo.setText("NEW CONTACTS");
        top.getChildren().addAll(im1, titulo);
        contentBox.getChildren().add(top);
    }

    private void Container_Header() {
        HBox nueva = new HBox(30);
        nueva.getStyleClass().add("blackbackgorund");
        nueva.setAlignment(Pos.CENTER);

        VBox header = new VBox(10);
        header.getStyleClass().add("blackbackgorund");
        header.setAlignment(Pos.CENTER);
        Text info_upload = new Text("UPLOAD IMAGE");
        info_upload.getStyleClass().add("upload_image");
        ImageView profile_picture = new ImageView(new Image("Iconos/cambiar_foto.png"));
        if (actual_contact != null ){
            profile_picture = new ImageView(new Image(actual_contact.getProfilePhoto()));
        }
        final ImageView profile_picture_e = profile_picture;
        profile_picture_e.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                FileChooser file = new FileChooser();
                file.setTitle("CHOOSE PROFILE PICTURE ");
                file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                File selectedFile = file.showOpenDialog(null);
                Image image;
                if (selectedFile != null) {                
                    Path sourcePath = selectedFile.toPath();
                    createFolder();
                    Path targetPath = Paths.get(directoryPath + "/" + "personalprofile.png");
                    try {
                        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        Platform.runLater(() -> {
                            profile_picture_e.setImage(new Image(targetPath.toUri().toString()));
                            info_upload.setText("");
                        });
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ISSUES TO MOVE FOLDER");
                        alert.setTitle("MOVE FOLDER");
                        alert.setHeaderText("INFORMATION");
                        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(okButton);
                        alert.showAndWait();
                    }
                }   
            }
        });
        profile_picture_e.setFitHeight(88);
        profile_picture_e.setFitWidth(153);

        
        header.getChildren().addAll(profile_picture_e, info_upload);

        typeDropdown = new ComboBox<>();
        typeDropdown.getItems().addAll("PERSON", "COMPANY");
        typeDropdown.setValue("PERSON");
        typeDropdown.setOnAction(event -> {
            tipo = typeDropdown.getValue();
            adjustContentBasedOnTipo();
        });
        nueva.getChildren().addAll(header, typeDropdown);
        contentBox.getChildren().add(nueva);
    }

    private void adjustContentBasedOnTipo() {
        Platform.runLater(() -> {
            contentBox.getChildren().remove(2);
            contentBox.getChildren().add(2, Create_Container_Name_Last());
        });
    }

    private void Container_Name_Last() {
        
        VBox name_last = new VBox();
        name_last.getStyleClass().addAll("blackbackgorund", "margin-top");
        name_last.setAlignment(Pos.CENTER);
        

        TextField name = new TextField();
        name.setPromptText("NAME");
        name.getStyleClass().add("text-field");
        name.setPrefSize(500, 60);
        name.setMaxSize(500, 60);
        name.setMinSize(500, 60);
        if (actual_contact != null ){
            name.setText(actual_contact.getName());
        }
        TextField last = new TextField();
        last.setPromptText("LASTNAME");
        last.getStyleClass().add("text-field");
        last.setPrefSize(500, 60);
        last.setMaxSize(500, 60);
        last.setMinSize(500, 60);
        try{
            if (actual_contact != null ){
                last.setText(actual_contact.getLastname());
            }
        }catch(Exception e){
            
        }

        if (tipo.equals("COMPANY")) {
            name_last.getChildren().addAll(name);
        } else {
            name_last.getChildren().addAll(name, last);
        }
        contentBox.getChildren().add(name_last);
    }

    private VBox Create_Container_Name_Last() {
        VBox name_last = new VBox();
        name_last.getStyleClass().addAll("blackbackgorund", "margin-top");
        name_last.setAlignment(Pos.CENTER);

        TextField name = new TextField();
        name.setPromptText("NAME");
        name.getStyleClass().add("text-field");
        name.setPrefSize(500, 60);
        name.setMaxSize(500, 60);
        name.setMinSize(500, 60);
        if (actual_contact != null ){
            name.setText(actual_contact.getName());
        }
        TextField last = new TextField();
        last.setPromptText("LASTNAME");
        last.getStyleClass().add("text-field");
        last.setPrefSize(500, 60);
        last.setMaxSize(500, 60);
        last.setMinSize(500, 60);
        try{
            if (actual_contact != null ){
                last.setText(actual_contact.getLastname());
            }
        }catch(Exception e){
            
        }
        if (tipo.equals("COMPANY")) {
            name_last.getChildren().addAll(name);
        } else {
            name_last.getChildren().addAll(name, last);
        }
        return name_last;
    }

    private void container_AddPhone() {
        VBox phones = new VBox();
        phones.getStyleClass().addAll("blackbackgorund", "margin-top");
        phones.setAlignment(Pos.CENTER);

        HBox add_phone = createAddOptions("ADD PHONE", "Iconos/agregar.png", event -> {
            HBox new_phone = createHBox("Iconos/eliminar.png", "TIPO", "PHONE", 150, 500);
            phones.getChildren().add(new_phone);
            new_phone.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> phones.getChildren().remove(new_phone));
        });
        if (actual_contact != null ){
           List<Telephone> telefonos_actuales = actual_contact.getTelephoneNumbers();
           for (int i = 0 ; i<telefonos_actuales.size() ; i++){
               Telephone tp1 = telefonos_actuales.get(i);
               HBox new_phone = createHBox_edit("Iconos/eliminar.png", "TIPO", "PHONE", 150, 500,tp1.getNumberType(),tp1.getTelephoneNumber());
               phones.getChildren().add(new_phone);
               new_phone.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> phones.getChildren().remove(new_phone));
           }
        }
        
        phones.getChildren().add(add_phone);
        contentBox.getChildren().add(phones);
    }

    private void container_AddEmail() {
        VBox emails = new VBox();
        emails.getStyleClass().add("blackbackgorund");
        emails.setAlignment(Pos.CENTER);

        HBox add_email = createAddOptions("ADD EMAIL", "Iconos/agregar.png", event -> {
            HBox new_email = createHBox("Iconos/eliminar.png", "TYPE", "EMAIL", 150, 620);
            emails.getChildren().add(new_email);
            new_email.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> emails.getChildren().remove(new_email));
        });
        if (actual_contact != null ){
           List<Email> emails_actuales = actual_contact.getEmails();
           for (int i = 0 ; i<emails_actuales.size() ; i++){
               Email tp1 = emails_actuales.get(i);
               HBox new_email = createHBox_edit("Iconos/eliminar.png", "TYPE", "EMAIL", 150, 500,tp1.getEmailType(),tp1.getEmail());
               emails.getChildren().add(new_email);
               new_email.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> emails.getChildren().remove(new_email));
           }
        }
        emails.getChildren().add(add_email);
        contentBox.getChildren().add(emails);
    }

    private HBox createAddOptions(String labelText, String iconPath, EventHandler<MouseEvent> handler) {
        HBox addBox = new HBox(20);
        addBox.setAlignment(Pos.CENTER_LEFT);
        addBox.getStyleClass().add("text-field");
        addBox.setPrefSize(width_container, 60);
        addBox.setMaxSize(width_container, 60);
        addBox.setMinSize(width_container, 60);

        ImageView icon_add = new ImageView(new Image(iconPath));
        icon_add.setFitHeight(30);
        icon_add.setFitWidth(30);

        Label label = new Label(labelText);
        label.getStyleClass().add("sign-up");

        addBox.getChildren().addAll(icon_add, label);
        addBox.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        return addBox;
    }

    private HBox createHBox(String iconPath, String promptText1, String promptText2, double prefWidth1, double prefWidth2) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(width_container, 60);
        itemBox.setMaxSize(width_container, 60);
        itemBox.setMinSize(width_container, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        TextField textField1 = new TextField();
        textField1.setPromptText(promptText1);
        textField1.setPrefSize(prefWidth1, 30);
        textField1.setStyle("-fx-text-fill: blue;");

        TextField textField2 = new TextField();
        textField2.setPromptText(promptText2);
        textField2.setPrefSize(prefWidth2, 30);

        itemBox.getChildren().addAll(icon_delete, textField1, textField2);
        return itemBox;
    }
    private HBox createHBox_edit(String iconPath, String promptText1, String promptText2, double prefWidth1, double prefWidth2,String text1,String text2) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(width_container, 60);
        itemBox.setMaxSize(width_container, 60);
        itemBox.setMinSize(width_container, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        TextField textField1 = new TextField();
        textField1.setPromptText(promptText1);
        textField1.setPrefSize(prefWidth1, 30);
        textField1.setText(text1);
        textField1.setStyle("-fx-text-fill: blue;");

        TextField textField2 = new TextField();
        textField2.setPromptText(promptText2);
        textField2.setPrefSize(prefWidth2, 30);
        textField2.setText(text1);

        itemBox.getChildren().addAll(icon_delete, textField1, textField2);
        return itemBox;
    }

//    private void container_address() {
//        HBox container_address = new HBox();
//        container_address.setAlignment(Pos.CENTER);
//        container_address.getStyleClass().add("blackbackgorund");
//        container_address.setPrefSize(width_container, 60);
//        container_address.setMaxSize(width_container, 60);
//        container_address.setMinSize(width_container, 60);
//        
//        TextField textField1 = new TextField();
//        textField1.setPromptText("Tipo dirección");
//        textField1.setPrefSize(150, 30);
//        textField1.setStyle("-fx-text-fill: blue;");
//
//        TextArea textField2 = new TextArea();
//        textField2.setPromptText("Descripción");
//        textField2.setPrefSize(600, 200);
//        textField2.setStyle("-fx-text-fill: blue;");
//        
//        TextField textField3 = new TextField();
//        textField3.setPromptText("Link");
//        textField3.setPrefSize(100, 30);
//        textField3.setStyle("-fx-text-fill: blue;");
//
//        container_address.getChildren().addAll(textField1, textField2, textField3);
//        
//
////        TextField address = new TextField();
////        address.setPromptText("ADDRESS");
////        address.getStyleClass().add("text-field");
////        address.setPrefSize(width_container, 60);
////        address.setMaxSize(width_container, 60);
////        address.setMinSize(width_container, 60);
////        container_address.getChildren().add(address);
//        contentBox.getChildren().add(container_address);
//    }
    private void container_address() {
        VBox address = new VBox();
        address.getStyleClass().add("blackbackgorund");
        address.setAlignment(Pos.CENTER);

        HBox add_address = createAddOptions("ADD ADDRESS", "Iconos/agregar.png", event -> {
            HBox new_addres = createHBox_Addres("Iconos/eliminar.png", "DESCRIPTION", "DIRECTION");
            address.getChildren().add(new_addres);
            new_addres.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> address.getChildren().remove(new_addres));
        });
        if (actual_contact != null ){
           List<Address> address_actuales = actual_contact.getAddress();
           for (int i = 0 ; i<address_actuales.size() ; i++){
               Address tp1 = address_actuales.get(i);
               HBox new_addres = createHBox_Addres_edit("Iconos/eliminar.png", "DESCRIPTION", "DIRECTION",tp1.getDescription(),tp1.getGeographyUbication());
               address.getChildren().add(new_addres);
               new_addres.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> address.getChildren().remove(new_addres));
           }
        }
        address.getChildren().add(add_address);
        contentBox.getChildren().add(address);
    }
    private HBox createHBox_Addres(String iconPath, String promptText1, String promptText2) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(width_container, 60);
        itemBox.setMaxSize(width_container, 60);
        itemBox.setMinSize(width_container, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        TextField textField1 = new TextField();
        textField1.setPromptText(promptText1);
        textField1.setPrefSize(300, 30);
        textField1.setStyle("-fx-text-fill: blue;");

        TextField textField2 = new TextField();
        textField2.setPromptText(promptText2);
        textField2.setPrefSize(420, 30);

        itemBox.getChildren().addAll(icon_delete, textField1, textField2);
        return itemBox;
    }
    private HBox createHBox_Addres_edit(String iconPath, String promptText1, String promptText2,String Text1, String Text2) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(width_container, 60);
        itemBox.setMaxSize(width_container, 60);
        itemBox.setMinSize(width_container, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        TextField textField1 = new TextField();
        textField1.setPromptText(promptText1);
        textField1.setPrefSize(300, 30);
        textField1.setText(Text1);
        textField1.setStyle("-fx-text-fill: blue;");

        TextField textField2 = new TextField();
        textField2.setPromptText(promptText2);
        textField2.setText(Text2);
        textField2.setPrefSize(420, 30);

        itemBox.getChildren().addAll(icon_delete, textField1, textField2);
        return itemBox;
    }
    private void container_SocialMedia() {
        VBox social_medias = new VBox();
        social_medias.getStyleClass().add("blackbackgorund");
        social_medias.setAlignment(Pos.CENTER);

        HBox add_social_media = createAddOptions("ADD SOCIAL MEDIA", "Iconos/agregar.png", event -> {
            HBox new_socialmedia = createHBox("Iconos/eliminar.png", "SOCIAL MEDIA", "USER", 150, 620);
            social_medias.getChildren().add(new_socialmedia);
            new_socialmedia.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> social_medias.getChildren().remove(new_socialmedia));
        });
        if (actual_contact != null ){
           List<SocialMedia> SocialMedias_actuales = actual_contact.getSocialsMedia();
           for (int i = 0 ; i<SocialMedias_actuales.size() ; i++){
               SocialMedia tp1 = SocialMedias_actuales.get(i);
               HBox new_socialmedia = createHBox_edit("Iconos/eliminar.png", "SOCIAL MEDIA", "USER", 150, 500,tp1.getSocialMedia(),tp1.getUser());
               social_medias.getChildren().add(new_socialmedia);
               new_socialmedia.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> social_medias.getChildren().remove(new_socialmedia));
           }
        }
        social_medias.getChildren().add(add_social_media);
        contentBox.getChildren().add(social_medias);
    }

    private void createHBox_photo() {
        VBox container_photos = new VBox();
        container_photos.getStyleClass().add("blackbackgorund");
        container_photos.setAlignment(Pos.CENTER);
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(width_container, 60);
        itemBox.setMaxSize(width_container, 60);
        itemBox.setMinSize(width_container, 60);

        Button btn_photo = new Button("ADD PHOTO");
        btn_photo.getStyleClass().add("text-field");
        btn_photo.setPrefSize(300, 30);
        btn_photo.setStyle("-fx-text-fill: blue;");

        Text process = new Text();
        process.setText("");

        itemBox.getChildren().addAll(btn_photo, process);
        container_photos.getChildren().add(itemBox);
        btn_photo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                FileChooser file = new FileChooser();
                file.setTitle("CHOOSE PROFILE PICTURE ");
                file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                File selectedFile = file.showOpenDialog(null);
                if (selectedFile != null) {
                    Image image = new Image(selectedFile.toURI().toString());
                
                    Path sourcePath = selectedFile.toPath();
//                  String name_folder = id_registro;
                    createFolder();
                    Path targetPath = Paths.get(directoryPath + "/" + number_photos + ".png");
                    System.out.println(targetPath);
                    try {
                        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        Platform.runLater(() -> {
                            process.getStyleClass().add("confirmation");
                            number_photos++;
                            process.setText("NEW IMAGE " +number_photos+" CONFIRMED");
                        });
                    } catch (IOException e) {
                        Platform.runLater(() -> {
                            process.setText("IMAGE UPLOAD FAILED\n TRY AGAIN");
                            process.getStyleClass().add("negation");
                        });
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ISSUES TO MOVE FOLDER");
                        alert.setTitle("MOVE FOLDER");
                        alert.setHeaderText("INFORMATION");
                        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(okButton);
                        alert.showAndWait();
                    }
            }
            }
            });
        contentBox.getChildren().add(container_photos);
    }

    private void createFolder() {
        try {
            String baseDir = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
            directoryPath = baseDir + "/src/main/resources/Contacts/" + id_registro;
            Path directory = Paths.get(directoryPath);

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("FOLDER NOT CREATED");
            alert.setHeaderText("WARNING");
            ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    private void container_AddImportantsDate() {
        VBox dates = new VBox();
        dates.getStyleClass().add("blackbackgorund");
        dates.setAlignment(Pos.CENTER);

        HBox add_date = createAddOptions("ADD DATES", "Iconos/agregar.png", event -> {
            HBox new_hbox_date = createHBox_dates("Iconos/eliminar.png", "DESCRIPTION", "DATE", 200, 420);
            dates.getChildren().add(new_hbox_date);
            new_hbox_date.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> dates.getChildren().remove(new_hbox_date));
        });
        if (actual_contact != null ){
           List<Date> dates_actuales = actual_contact.getDatesInterest();
           for (int i = 0 ; i<dates_actuales.size() ; i++){
               Date tp1 = dates_actuales.get(i);
               HBox new_date = createHBox_dates("Iconos/eliminar.png", "TYPE", "EMAIL", 200, 500,tp1.getDateType(),tp1.getDate());
               dates.getChildren().add(new_date);
               new_date.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> dates.getChildren().remove(new_date));
           }
        }
        dates.getChildren().add(add_date);
        contentBox.getChildren().add(dates);
    }

    private HBox createHBox_dates(String iconPath, String promptText1, String promptText2, double prefWidth1, double prefWidth2) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(width_container, 60);
        itemBox.setMaxSize(width_container, 60);
        itemBox.setMinSize(width_container, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        TextField textField1 = new TextField();
        textField1.setPromptText(promptText1);
        textField1.setPrefSize(prefWidth1, 30);
        textField1.setStyle("-fx-text-fill: blue;");

        Text date = new Text();
        date.getStyleClass().add("confirmation");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Selecciona una fecha");
        datePicker.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            date.setText(selectedDate + "");
        });

        itemBox.getChildren().addAll(icon_delete, textField1, datePicker, date);
        return itemBox;
    }
    private HBox createHBox_dates(String iconPath, String promptText1, String promptText2, double prefWidth1, double prefWidth2,String text1, LocalDate date1) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(width_container, 60);
        itemBox.setMaxSize(width_container, 60);
        itemBox.setMinSize(width_container, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        TextField textField1 = new TextField();
        textField1.setPromptText(promptText1);
        textField1.setPrefSize(prefWidth1, 30);
        textField1.setStyle("-fx-text-fill: blue;");
        textField1.setText(text1);

        Text date = new Text();
        date.getStyleClass().add("confirmation");
        date.setText(date1+"");
        
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Selecciona una fecha");
        datePicker.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            date.setText(selectedDate + "");
        });
        datePicker.setValue(date1);
        
        itemBox.getChildren().addAll(icon_delete, textField1, datePicker, date);
        return itemBox;
    }
    private void container_AddRelatedContacts(String LabelText1, String confirmation) {
        VBox contacts = new VBox();
        contacts.getStyleClass().add("blackbackgorund");
        contacts.setAlignment(Pos.CENTER);

        add_RC = createAddOptions(LabelText1, "Iconos/agregar.png", event -> {
            try {
                AgregarContacto();
            } catch (IOException ex) {

            }
        });
        process = new Label();
        process.setText(confirmation);
        process.getStyleClass().add("confirmation");
        contacts.getChildren().addAll(add_RC, process);
        contentBox.getChildren().add(contacts);
    }

    private VBox container_AddRelatedContacts_2(String LabelText1, String confirmation) {
        VBox contacts = new VBox();
        contacts.getStyleClass().add("blackbackgorund");
        contacts.setAlignment(Pos.CENTER);

        add_RC = createAddOptions(LabelText1, "Iconos/agregar.png", event -> {
            try {
                AgregarContacto();
            } catch (IOException ex) {
                System.out.println("error de que? ns");
            }
        });
        process = new Label();
        process.setText(confirmation);
        process.getStyleClass().add("confirmation");
        contacts.getChildren().addAll(add_RC, process);
        contentBox.getChildren().add(contacts);
        return contacts;
    }

    
   private void updateRelatedContact() {
    lista_contacto = Util.changetoDoublyLinkedList(Util.readListFromFileSer("ContactosSeleccionados.ser"));
    if (lista_contacto.size() != 0 && edit_new ) {
        Platform.runLater(() -> {
            process.setText("NEW CONTACTS SUCCESSFULLY ADDED");
            Label add_date_l = (Label) add_RC.getChildren().get(1);
            add_date_l.setText("EDIT RELEATED CONTACT");
        });
    }
}
    private void AgregarContacto() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add_releatedContact.fxml"));
        Parent root = loader.load();
        Add_releatedContactController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage dialogStage = new Stage();
        dialogStage.setTitle("RELEATED CONTACT");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(principal.getScene().getWindow());
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        updateRelatedContact();
    }

    // setear lista ; 
    private void regresar(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("contacts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) newcontact_page.getScene().getWindow();
    }

    private void SaveContact(){
            HBox container_btn = new HBox();
            container_btn.setStyle("-fx-background-color: #345a70;");
            container_btn.setPrefSize(750, 60);
            container_btn.setMaxSize(750, 60);
            container_btn.setMinSize(750, 60);
            container_btn.setAlignment(Pos.CENTER);
            Button btn_save = new Button("SAVE CONTACTS");
            btn_save.setPrefSize(150, 70);
            btn_save.setMaxSize(150, 70);
            btn_save.setMinSize(150, 70);
            container_btn.getChildren().add(btn_save);
            btn_save.getStyleClass().add("button2_");
            contentBox.getChildren().add(container_btn);
            btn_save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                @Override
                public void handle(Event event) {
                    
                }            
        });
       
    }

    private String get_NameOrLastname (int i){
        VBox container_Name_last = (VBox) contentBox.getChildren().get(indices_ContentBox.get("Container_Name_Last"));
        TextField type = (TextField) container_Name_last.getChildren().get(i);
        return type.getText(); 
    }
    
    private <T> List<T> extractInfo(VBox container, BiFunction<String, String, T> creador) {
        List<T> list = new ArrayList<>();
        for (Node node : container.getChildren()) {
            HBox hbox = (HBox) node;
            TextField type = (TextField) hbox.getChildren().get(1);
            TextField value_E = (TextField) hbox.getChildren().get(2);
            T item = creador.apply(type.getText(), value_E.getText());
            list.add(item);
        }
        return list;
    }


    private List<Telephone> getPhones(){
        VBox container_AddPhone = (VBox) contentBox.getChildren().get(indices_ContentBox.get("container_AddPhone"));
        return extractInfo(container_AddPhone, (type, number) -> new Telephone(type, number));
    }

    private List<Email> getEmails(){
        VBox container_AddEmail = (VBox) contentBox.getChildren().get(indices_ContentBox.get("container_AddEmail"));
        return extractInfo(container_AddEmail, (type, address) -> new Email(type, address));
    }

    private List<Address> getAddresses(){
        VBox container_AddAddress = (VBox) contentBox.getChildren().get(indices_ContentBox.get("container_address"));
        return extractInfo(container_AddAddress, (type, location) -> new Address(type, location));
    }

    private List<SocialMedia> getSocialMedias(){
        VBox container_SocialMedias = (VBox) contentBox.getChildren().get(indices_ContentBox.get("container_SocialMedia"));
        return extractInfo(container_SocialMedias, (type, account) -> new SocialMedia(type, account));
    }

    private List<Date> getDates(){
        VBox container_dates = (VBox) contentBox.getChildren().get(indices_ContentBox.get("container_SocialMedia")); // Aquí debe haber un error, debería ser "container_Dates"
        return extractInfo(container_dates, (type, dateString) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);
            return new Date(type, date);
        });
    }
}

//   private Contact newContact(){
//        String type_Contact = typeDropdown.getValue();
//   
//        TipoContact miEnumValor = TipoContact.valueOf(type_Contact);
//        
//        String name = get_NameOrLastname(0);
//        List<Telephone> l_telephones = getPhones();
//        List<Email> l_email = getEmails();
//        List<Address> l_address = getAddresses();
//        List<SocialMedia> l_socialmedias = getSocialMedias();
//        List<Date> l_dates = getDates();
//        
//        
//        if (miEnumValor == TipoContact.person) {
//           String last_name = get_NameOrLastname(1);
//           Contact c1 = new Person(name,last_name,l_telephones,l_email,l_address,l_socialmedias,l_dates,lista_contacto);
//        }
//        
//        
//        
//        return null;
//       
//   }

