/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class ContactnewController implements Initializable {
    
    @FXML
    private AnchorPane newcontact_page;
    @FXML
    private ImageView flecha_regresar;
    private double dimension_alto_e;
    private double dimension_alto_e2;    
    @FXML
    private ScrollPane principal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        principal.setContent(newcontact_page);
        principal.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        principal.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        newcontact_page.getStyleClass().add("blackbackgorund");
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        
        ImageView profile_picture = new ImageView(new Image("Iconos/cambiar_foto.png"));
        profile_picture.setFitHeight(88);
        profile_picture.setFitWidth(153);

        Text info_upload = new Text("UPLOAD IMAGE");
        info_upload.getStyleClass().add("upload_image");

        header.getChildren().addAll(profile_picture, info_upload);

        VBox name_last = new VBox(10);
        name_last.setAlignment(Pos.CENTER);

        TextField name = new TextField();
        name.setPromptText("NAME");
        name.getStyleClass().add("text-field");
        name.setPrefSize(500, 60);

        TextField last = new TextField();
        last.setPromptText("LASTNAME");
        last.getStyleClass().add("text-field");
        last.setPrefSize(500, 60);

        name_last.getChildren().addAll(name, last);
        
        HBox add_phone = new HBox(10);
        add_phone.setAlignment(Pos.CENTER_LEFT);
        ImageView icon_add = new ImageView(new Image("Iconos/agregar.png"));
        icon_add.setFitHeight(30);
        icon_add.setFitWidth(30);
        Label phone = new Label("ADD PHONE");
        phone.getStyleClass().add("sign-up");
        add_phone.getStyleClass().add("text-field");
        add_phone.setPrefSize(700, 60);
        add_phone.setSpacing(20);
        add_phone.getChildren().addAll(icon_add, phone);
        
        HBox add_email = new HBox(10);
        add_email.setAlignment(Pos.CENTER_LEFT);
        ImageView icon_add2 = new ImageView(new Image("Iconos/agregar.png"));
        icon_add2.setFitHeight(30);
        icon_add2.setFitWidth(30);
        Label email = new Label("ADD EMAIL");
        email.getStyleClass().add("sign-up");
        add_email.getStyleClass().add("text-field");
        add_email.setPrefSize(700, 60);
        add_email.setSpacing(20);
        add_email.getChildren().addAll(icon_add2, email);

        AnchorPane.setTopAnchor(header, 70.0); 
        AnchorPane.setLeftAnchor(header, (newcontact_page.getPrefWidth() - profile_picture.getFitWidth()) / 2);
        AnchorPane.setRightAnchor(header, (newcontact_page.getPrefWidth() - profile_picture.getFitWidth()) / 2);

        AnchorPane.setTopAnchor(name_last, 200.0); 
        AnchorPane.setLeftAnchor(name_last, (newcontact_page.getPrefWidth() - name.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(name_last, (newcontact_page.getPrefWidth() - name.getPrefWidth()) / 2);
        
        

//        AnchorPane.setTopAnchor(add_phone, 350.0);
        VBox phones = new VBox();
        phones.setPrefSize(700, 60);
        
        VBox emails = new VBox();
        emails.setPrefSize(700, 60);
        
        
        AnchorPane.setTopAnchor(add_phone, 350.0);
        AnchorPane.setLeftAnchor(add_phone, (newcontact_page.getPrefWidth() - add_phone.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(add_phone, (newcontact_page.getPrefWidth() - add_phone.getPrefWidth()) / 2);
        
        AnchorPane.setTopAnchor(phones, 410.0);
        AnchorPane.setLeftAnchor(phones, (newcontact_page.getPrefWidth() - phones.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(phones, (newcontact_page.getPrefWidth() - phones.getPrefWidth()) / 2);
        
        dimension_alto_e = 410.0;
        
        AnchorPane.setTopAnchor(add_email, dimension_alto_e);
        AnchorPane.setLeftAnchor(add_email, (newcontact_page.getPrefWidth() - add_email.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(add_email, (newcontact_page.getPrefWidth() - add_email.getPrefWidth()) / 2);
        
        dimension_alto_e2 = dimension_alto_e+60.0;
        AnchorPane.setTopAnchor(emails, dimension_alto_e2); 
        AnchorPane.setLeftAnchor(emails, (newcontact_page.getPrefWidth() - emails.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(emails, (newcontact_page.getPrefWidth() - emails.getPrefWidth()) / 2);    
        
        newcontact_page.getChildren().addAll(header, name_last,add_phone,phones,add_email,emails);
        
        add_phone.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
            @Override
            public void handle(Event event) {
                HBox new_phone = new HBox(20);
                new_phone.getStyleClass().add("text-field");
                add_phone.setAlignment(Pos.CENTER_LEFT);
                ImageView icon_dele = new ImageView(new Image("Iconos/eliminar.png"));
                icon_dele.setFitHeight(30);
                icon_dele.setFitWidth(30);
                // Implementar poder eliminar
                TextField tipo = new TextField();
                tipo.setPromptText("TIPO");
                tipo.setPrefSize(150, 30);
                tipo.setStyle("-fx-text-fill: blue;");
                // Validar Numero
                TextField phone_a = new TextField();
                phone_a.setPromptText("PHONE");
                phone_a.setPrefSize(600, 30);
                new_phone.getChildren().addAll(icon_dele,tipo,phone_a);
                Platform.runLater(()->{
                    phones.getChildren().add(new_phone); 
                    dimension_alto_e += 60.0;
                    AnchorPane.setTopAnchor(add_email, dimension_alto_e);
                    AnchorPane.setLeftAnchor(add_email, (newcontact_page.getPrefWidth() - add_email.getPrefWidth()) / 2);
                    AnchorPane.setRightAnchor(add_email, (newcontact_page.getPrefWidth() - add_email.getPrefWidth()) / 2);
                    dimension_alto_e2 = dimension_alto_e+60.0;
                    AnchorPane.setTopAnchor(emails, dimension_alto_e2); 
                    AnchorPane.setLeftAnchor(emails, (newcontact_page.getPrefWidth() - emails.getPrefWidth()) / 2);
                    AnchorPane.setRightAnchor(emails, (newcontact_page.getPrefWidth() - emails.getPrefWidth()) / 2);        
                    newcontact_page.getChildren().addAll(header, name_last,add_phone,phones,add_email,emails);
                });
            }  
            });
        
        add_email.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
            @Override
            public void handle(Event event) {
                HBox new_email = new HBox(20);
                new_email.getStyleClass().add("text-field");
                add_email.setAlignment(Pos.CENTER_LEFT);
                ImageView icon_dele = new ImageView(new Image("Iconos/eliminar.png"));
                icon_dele.setFitHeight(30);
                icon_dele.setFitWidth(30);
                // Implementar poder eliminar
                TextField tipo = new TextField();
                tipo.setPromptText("TIPO");
                tipo.setPrefSize(150, 30);
                tipo.setStyle("-fx-text-fill: blue;");
                // Validar correo
                TextField email_a = new TextField();
                email_a.setPromptText("EMAIL");
                email_a.setPrefSize(600, 30);
                new_email.getChildren().addAll(icon_dele,tipo,email_a);
                Platform.runLater(()->{
                    emails.getChildren().add(new_email);
                    dimension_alto_e2 += 60.0;
                });
            }  
            });
    }
}