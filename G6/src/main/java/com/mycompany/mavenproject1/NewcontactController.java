/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.Contact;
import com.mycompany.contacts.Util;
import ec.edu.espol.TDAs.DoublyLinkedList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_registro = "A0000";
        number_photos = 0;
        cssFile = getClass().getResource("/styles/login.css").toExternalForm();
        principal.getStylesheets().add(cssFile);
        newcontact_page.getStylesheets().add(cssFile);

        principal.getStyleClass().add("blackbackground");
        newcontact_page.getStyleClass().add("blackbackground");

        contentBox = new VBox();
        contentBox.setMinSize(835, 751);
        contentBox.setMaxWidth(835);
        contentBox.getStyleClass().add("blackbackground");

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
        updateRelatedContact();
        principal.setContent(contentBox);
        principal.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        principal.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

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

        ImageView profile_picture = new ImageView(new Image("Iconos/cambiar_foto.png"));
        profile_picture.setFitHeight(88);
        profile_picture.setFitWidth(153);

        Text info_upload = new Text("UPLOAD IMAGE");
        info_upload.getStyleClass().add("upload_image");

        header.getChildren().addAll(profile_picture, info_upload);

        ComboBox<String> typeDropdown = new ComboBox<>();
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

        TextField last = new TextField();
        last.setPromptText("LASTNAME");
        last.getStyleClass().add("text-field");
        last.setPrefSize(500, 60);
        last.setMaxSize(500, 60);
        last.setMinSize(500, 60);

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

        TextField last = new TextField();
        last.setPromptText("LASTNAME");
        last.getStyleClass().add("text-field");
        last.setPrefSize(500, 60);
        last.setMaxSize(500, 60);
        last.setMinSize(500, 60);

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

        phones.getChildren().add(add_phone);
        contentBox.getChildren().add(phones);
    }

    private void container_AddEmail() {
        VBox emails = new VBox();
        emails.getStyleClass().add("blackbackgorund");
        emails.setAlignment(Pos.CENTER);

        HBox add_email = createAddOptions("ADD EMAIL", "Iconos/agregar.png", event -> {
            HBox new_email = createHBox("Iconos/eliminar.png", "TIPO", "EMAIL", 150, 620);
            emails.getChildren().add(new_email);
            new_email.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> emails.getChildren().remove(new_email));
        });

        emails.getChildren().add(add_email);
        contentBox.getChildren().add(emails);
    }

    private HBox createAddOptions(String labelText, String iconPath, EventHandler<MouseEvent> handler) {
        HBox addBox = new HBox(20);
        addBox.setAlignment(Pos.CENTER_LEFT);
        addBox.getStyleClass().add("text-field");
        addBox.setPrefSize(750, 60);
        addBox.setMaxSize(750, 60);
        addBox.setMinSize(750, 60);

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
        itemBox.setPrefSize(750, 60);
        itemBox.setMaxSize(750, 60);
        itemBox.setMinSize(750, 60);

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

    private void container_address() {
        HBox container_address = new HBox();
        container_address.setAlignment(Pos.CENTER);
        container_address.getStyleClass().add("blackbackgorund");
        TextField address = new TextField();
        address.setPromptText("ADDRESS");
        address.getStyleClass().add("text-field");
        address.setPrefSize(750, 60);
        address.setMaxSize(750, 60);
        address.setMinSize(750, 60);
        container_address.getChildren().add(address);
        contentBox.getChildren().add(container_address);
    }

    private void container_SocialMedia() {
        VBox social_medias = new VBox();
        social_medias.getStyleClass().add("blackbackgorund");
        social_medias.setAlignment(Pos.CENTER);

        HBox add_social_media = createAddOptions("ADD SOCIAL MEDIA", "Iconos/agregar.png", event -> {
            HBox new_email = createHBox("Iconos/eliminar.png", "SOCIAL MEDIA", "USER", 150, 620);
            social_medias.getChildren().add(new_email);
            new_email.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> social_medias.getChildren().remove(new_email));
        });

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
        itemBox.setPrefSize(750, 60);
        itemBox.setMaxSize(750, 60);
        itemBox.setMinSize(750, 60);

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
                }
                Path sourcePath = selectedFile.toPath();
//            String name_folder = id_registro;
                createFolder();
                Path targetPath = Paths.get(directoryPath + "/" + number_photos + ".png");
                System.out.println(targetPath);
                try {
                    Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    Platform.runLater(() -> {
                        process.setText("NEW IMAGE CONFIRMED");
                        process.getStyleClass().add("confirmation");
                        number_photos++;
                    });
                } catch (IOException e) {
                    Platform.runLater(() -> {
                        process.setText("NEW IMAGE CONFIRMED");
                        process.getStyleClass().add("negation");
                        number_photos++;
                    });
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "ISSUES TO MOVE FOLDER");
                    alert.setTitle("MOVE FOLDER");
                    alert.setHeaderText("INFORMATION");
                    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    alert.showAndWait();
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
            HBox new_hbox_date = createHBox_dates("Iconos/eliminar.png", "DESCRIPTION", "DATE", 150, 420);
            dates.getChildren().add(new_hbox_date);
            new_hbox_date.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> dates.getChildren().remove(new_hbox_date));
        });

        dates.getChildren().add(add_date);
        contentBox.getChildren().add(dates);
    }

    private HBox createHBox_dates(String iconPath, String promptText1, String promptText2, double prefWidth1, double prefWidth2) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(750, 60);
        itemBox.setMaxSize(750, 60);
        itemBox.setMinSize(750, 60);

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
    if (lista_contacto.size() != 0 ) {
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
          stage.setScene(scene); 
    }

        private void SaveContact(){
        
            Button btn_save = new Button("SAVE CONTACTS");
            btn_save.getStyleClass().add("button_");
            contentBox.getChildren().add(btn_save);
            btn_save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                @Override
                public void handle(Event event) {
                    
                }
            
          });
       
        }
        
        // Aquí van los funcionar para guarda todos los datos 
        // por ejemplos 
//    private List<Telephone> getTelephoneNumbers;
//    private List < Address> getAaddress;
//    private List<Email> getEmails;
//    private List<SocialMedia> getSocialsMedia;
//    private List<Date> getDatesInterest;
//    private List<RelatedContact> getRelatedContacts;
    }



