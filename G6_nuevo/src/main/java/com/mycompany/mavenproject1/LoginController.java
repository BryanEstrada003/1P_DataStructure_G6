/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.User;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView logo_user;
    @FXML
    private TextField user;
    @FXML
    private ImageView logo_pasword;
    @FXML
    private ImageView logo_app;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane login_page;
    @FXML
    private Label sign_up;
    @FXML
    private Label title_app;
    private User use;
    @FXML
    private Button btn_login;
    @FXML
    private Label firsttime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo_app.setImage(new Image("Iconos/Logo-sf.png"));
        logo_user.setImage(new Image("Iconos/usuario.png"));
        logo_pasword.setImage(new Image("Iconos/password.png"));
        login_page.getStyleClass().add("blackbackgorund");
        logo_user.getStyleClass().add("icon");
        logo_pasword.getStyleClass().add("icon");
        btn_login.getStyleClass().add("button_");
        user.getStyleClass().add("text-field");
        password.getStyleClass().add("text-field");
        firsttime.getStyleClass().add("sign-up");
        sign_up.getStyleClass().add("sign-up");
        

    }    

    @FXML
    private void login(MouseEvent event) throws IOException {
        if (user.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"All FIELDS ARE REQUIRED TO REGISTER.");
            alert.setTitle("LOGIN");
            alert.setHeaderText("WARNING");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
            
        } 
        else if((User.validarLogin(user.getText().trim(), password.getText().trim())) !=  null){ 
           // si quiero puedo poner que me diga ingreso exitoso
            use = User.validarLogin(user.getText().trim(), password.getText().trim());
            user.setText("");
            password.setText("");
            user_login_temp(use);
            Parent root = FXMLLoader.load(getClass().getResource("contacts.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) user.getScene().getWindow();
            stage.setScene(scene);  
            }       
        
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"INVALID USER O PASSWORD");
            alert.setTitle("FIELD VALIDATION");
            alert.setHeaderText("INFORMATION");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    @FXML
    private void sign_up(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sign_up.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) user.getScene().getWindow();
        stage.setScene(scene); 
    }
    
    // Verificar downcasting
    public void user_login_temp(User use){
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("User_login.ser")))
        {
            out.writeObject(use);
        }
        catch(IOException ioe)
        {
            
        }        
    }
    
}
