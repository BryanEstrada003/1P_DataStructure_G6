/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.User;
import static ec.edu.espol.Clases.User.getUser;
import static ec.edu.espol.Clases.User.readListFromFileSer;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
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
    private PasswordField password;
    @FXML
    private Button btn_login;
    @FXML
    private Label firsttime;
    @FXML
    private Label sign_up;
    @FXML
    private ImageView logo_app;
    @FXML
    private AnchorPane page;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        page.getStyleClass().add("blackbackgorund");
        logo_user.setImage(new Image("Iconos/usuario.png"));
        logo_pasword.setImage(new Image("Iconos/password.png"));
        logo_user.getStyleClass().add("icon");
        logo_pasword.getStyleClass().add("icon");
        btn_login.getStyleClass().add("button_");
        user.getStyleClass().add("text-field");
        password.getStyleClass().add("text-field");
        firsttime.getStyleClass().add("sign-up");
        sign_up.getStyleClass().add("sign-up");
       
//        ArrayList<User> users = readListFromFileSer("User.ser");
//        System.out.println(users.size());
//            for(User u1 :users){
//                System.out.println("User");
//                System.out.println(u1); 
//            }
        
    }    
    public void passUser(User us1)
    {
         try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("User_provisional.ser")))
        {
            out.writeObject(us1);
        }
        catch(IOException ioe){
        }
         
    }
    @FXML
    private void login(MouseEvent event) throws IOException {
        if(User.checkLogin(user.getText().trim(), password.getText().trim())){
            User user1 = getUser(user.getText().trim(), password.getText().trim());
            passUser(user1);
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) user.getScene().getWindow();
            stage.setScene(scene);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"INCORRECT USER OR PASSWORD");
            alert.setTitle("LOGIN");
            alert.setHeaderText("WARNING");
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
    
}
