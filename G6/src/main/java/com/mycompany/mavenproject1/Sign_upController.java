/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class Sign_upController implements Initializable {

    @FXML
    private AnchorPane sign_page;
    @FXML
    private ImageView logo_user;
    @FXML
    private TextField user;
    @FXML
    private ImageView logo_pasword;
    @FXML
    private PasswordField password;
    @FXML
    private Button button_sign_up;
    @FXML
    private ImageView logo_app;
    @FXML
    private Label title_app;
    @FXML
    private Label personal_information;
    @FXML
    private ImageView image_user;
    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    private String main_path = "/src/main/resources/" ; 
    @FXML
    private Label verificar_user;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private Label upload_image;
    private int confirmation_photo = 0 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        upload_image.setText("UPLOAD IMAGE");
        logo_app.setImage(new Image("Iconos/Logo-sf.png"));
        logo_user.setImage(new Image("Iconos/usuario.png"));
        logo_pasword.setImage(new Image("Iconos/password.png"));
        image_user.setImage(new Image("Iconos/cambiar_foto.png"));
        flecha_regresar.setImage(new Image("Iconos/flecha-izquierda.png"))  ;        
        sign_page.getStyleClass().add("blackbackgorund");
        name.getStyleClass().add("text-field");
        lastname.getStyleClass().add("text-field");
        user.getStyleClass().add("text-field");
        lastname.getStyleClass().add("text-field");
        button_sign_up.getStyleClass().add("button_");
        generate_image();

    }    

    private void generate_image() {     
        Path sourcePath = Paths.get("src/main/resources/Iconos/cambiar_foto.png");
        Path targetPath = Paths.get("src/main/resources/Users/p_user.png");
        try {
            if (Files.notExists(targetPath.getParent())) {
                Files.createDirectories(targetPath.getParent());
            }
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void register(MouseEvent event) throws IOException {
        ArrayList<String> p_users = User.getPersonalUsers();
        if (p_users.contains(user.getText().trim())){
//            Platform.runLater(()->{
//                verificar_user.setText("USER NOT AVAILABLE ");
//            });
            verificar_user.setText("USER NOT AVAILABLE !!");
            return ;
        }
        if (user.getText().trim().isEmpty() || password.getText().trim().isEmpty() || name.getText().trim().isEmpty() ||lastname.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"All FIELDS ARE REQUIRED TO REGISTER.");
            alert.setTitle("REGISTRATION");
            alert.setHeaderText("WARNING");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        } else {
            User user1 = new User(name.getText().trim(),lastname.getText().trim(),user.getText().trim(),password.getText().trim());
            user1.moveImageToPersonalPath(); 
            User.add_UsertoUser_Ser(user1);
            verificar_user.setText("");
            name.setText("");
            lastname.setText("");
            user.setText("");
            password.setText("");
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) user.getScene().getWindow();
            stage.setScene(scene);  
        }
}


    @FXML
    private void change_image(MouseEvent event) {
        FileChooser file = new FileChooser();
        file.setTitle("CHOOSE PROFILE PICTURE ");
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = file.showOpenDialog(null);
        
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            image_user.setImage(image);
            upload_image.setText("");
            
            Path sourcePath = selectedFile.toPath();
            Path targetPath = Paths.get("src/main/resources/Users/p_user.png");
            
            try {
                if (Files.notExists(targetPath.getParent())) {
                    System.out.println(targetPath.getParent());
                    Files.createDirectories(targetPath.getParent());
                }
                
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                Files.delete(sourcePath);
                confirmation_photo = 1;
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"ISSUES TO MOVE FOLDER");
                alert.setTitle("MOVE FOLDER");
                alert.setHeaderText("INFORMATION");
                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
        }
    }
    @FXML
    private void regresar(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) user.getScene().getWindow();
        stage.setScene(scene); 
    }
    
}
    
