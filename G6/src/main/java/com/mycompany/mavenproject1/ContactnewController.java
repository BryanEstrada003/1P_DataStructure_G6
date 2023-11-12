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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    @FXML
    private AnchorPane body_2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        HBox add_email = new HBox(10);
        add_email.setAlignment(Pos.CENTER_LEFT);
        ImageView icon_add = new ImageView(new Image("Iconos/agregar.png"));
        icon_add.setFitHeight(30);
        icon_add.setFitWidth(30);
        Label email = new Label("ADD EMAIL");
        email.getStyleClass().add("sign-up");
        add_email.getStyleClass().add("text-field");
        add_email.setPrefSize(813, 60);
        add_email.setSpacing(20);
        add_email.getChildren().addAll(icon_add, email);

        AnchorPane.setTopAnchor(header, 70.0); 
        AnchorPane.setLeftAnchor(header, (newcontact_page.getPrefWidth() - profile_picture.getFitWidth()) / 2);
        AnchorPane.setRightAnchor(header, (newcontact_page.getPrefWidth() - profile_picture.getFitWidth()) / 2);

        AnchorPane.setTopAnchor(name_last, 200.0); 
        AnchorPane.setLeftAnchor(name_last, (newcontact_page.getPrefWidth() - name.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(name_last, (newcontact_page.getPrefWidth() - name.getPrefWidth()) / 2);

        AnchorPane.setTopAnchor(add_email, 350.0); 
        VBox emails = new VBox();
        emails.setPrefSize(813, 60);
        AnchorPane.setTopAnchor(emails, 410.0); 
        newcontact_page.getChildren().addAll(header, name_last, add_email,emails);
        
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
                });

            }  
            });
    }
}
