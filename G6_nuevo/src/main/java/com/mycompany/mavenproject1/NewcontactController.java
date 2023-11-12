/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class NewcontactController implements Initializable {

    @FXML
    private ImageView flecha_regresar;
    @FXML
    private VBox body;
    @FXML
    private AnchorPane newcontact_page;
    @FXML
    private Pane pane_p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            newcontact_page.getStyleClass().add("blackbackgorund");
            pane_p.getStyleClass().add("blackbackgorund");
            VBox header  = new VBox();
            header.getStyleClass().add("blackbackgorund");
            ImageView profile_picture = new ImageView(new Image("Iconos/cambiar_foto.png"));
            Text info_upload = new Text();
            info_upload.setText("UPLOAD IMAGE");
            profile_picture.setFitHeight(88);
            profile_picture.setFitWidth(153);
            header.setSpacing(10);
            info_upload.getStyleClass().add("upload_image");
            header.getChildren().addAll(profile_picture,info_upload);
            
            VBox name_last = new VBox();
            name_last.getStyleClass().add("Null");
            TextField name = new TextField();
            name.setPromptText("NAME");
            TextField last = new TextField();
            last.setPromptText("LASTNAME");
            double heigth_tf = 60;
            double width_tf = 500;
            name.setPrefSize(width_tf, heigth_tf);
            name.setMinSize(width_tf, heigth_tf);
            name.setMaxSize(width_tf, heigth_tf);
            
            last.setPrefSize(width_tf, heigth_tf);
            last.setMinSize(width_tf, heigth_tf);
            last.setMaxSize(width_tf, heigth_tf);
            name.getStyleClass().add("text-field");
            last.getStyleClass().add("text-field");
            name_last.getStyleClass().add("centrar");
            name_last.getChildren().addAll(name,last);
            
            VBox emails = new VBox();
            HBox add_email = new HBox();
            ImageView icon_add = new ImageView(new Image("Iconos/agregar.png"));
            Label email = new Label("ADD EMAIL"); 
            emails.getStyleClass().add("text-field");
            add_email.getChildren().addAll(icon_add,email);
            icon_add.setFitHeight(50);
            icon_add.setFitWidth(50);
            emails.getChildren().addAll(add_email);
            add_email.setSpacing(20);
            add_email.setPrefSize(785, 60);
            
            body.getChildren().addAll(header,name,last,emails);
    }    

    
}
