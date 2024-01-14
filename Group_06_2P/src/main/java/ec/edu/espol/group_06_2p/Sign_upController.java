/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class Sign_upController implements Initializable {


    @FXML
    private ImageView logo_user;
    @FXML
    private TextField user;
    @FXML
    private Label verificar_user;
    @FXML
    private ImageView logo_pasword;
    @FXML
    private PasswordField password;
    @FXML
    private Button button_sign_up;
    @FXML
    private ImageView logo_app;
    @FXML
    private Label personal_information;
    @FXML
    private ImageView image_user;
    @FXML
    private Label upload_image;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private AnchorPane page;
    @FXML
    private TextField nicknamex;
    private String path_users = "User.ser";
    private String path_users_images = "Users/Profile_Images";
    private int id_actual = User.nextId();
    private static User user1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        upload_image.setText("UPLOAD IMAGE");
        logo_user.setImage(new Image("Iconos/usuario.png"));
        logo_pasword.setImage(new Image("Iconos/password.png"));
//        image_user.setImage(new Image("Iconos/cambiar_foto.png"));
        flecha_regresar.setImage(new Image("Iconos/prebtn.png"))  ;        
        page.getStyleClass().add("blackbackgorund");
        user.getStyleClass().add("text-field");
        password.getStyleClass().add("text-field");
        nicknamex.getStyleClass().add("text-field");
        button_sign_up.getStyleClass().add("button_");
    }    
    
    @FXML
    private void register(MouseEvent event) throws IOException {
        Boolean cond1 = (nicknamex.getText().isEmpty()||user.getText().isEmpty() || password.getText().isEmpty()  );
        Boolean cond2 =  (user.getText().isEmpty() || password.getText().isEmpty()  );
        if(!cond1){
            if(User.checkUserFile(user.getText().trim())){
              user1 = new User(id_actual,nicknamex.getText().trim(),user.getText().trim(),password.getText().trim());
              User.add_user_file(user1, path_users);
              Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) page.getScene().getWindow();
              stage.setScene(scene);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"INVALID USER");
                alert.setTitle("INVALID USER");
                alert.setHeaderText("WARNING");
                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }

        }
        else if(!cond2){ 
            if(User.checkUserFile(user.getText().trim())){
                user1 = new User(id_actual,user.getText().trim(),password.getText().trim());
                User.add_user_file(user1, path_users);
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) page.getScene().getWindow();
                stage.setScene(scene);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"INVALID USER");
                alert.setTitle("INVALID USER");
                alert.setHeaderText("WARNING");
                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
        }
        else{

            Alert alert = new Alert(Alert.AlertType.WARNING,"SOME FIELDS ARE REQUIRED TO REGISTER.");
            alert.setTitle("REGISTRATION");
            alert.setHeaderText("WARNING");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }

    }


    
    @FXML
    private void change_image(MouseEvent event) {
        FileChooser file = new FileChooser();
        file.setTitle("CHOOSE PROFILE PICTURE ");
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.avif","*.jpeg"));
        File selectedFile = file.showOpenDialog(null);
        
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            image_user.setImage(image);
            Circle clipCircle = new Circle(image_user.getFitWidth() / 2, image_user.getFitHeight() / 2, 50);
            image_user.setClip(clipCircle);
            upload_image.setText("");
            
            Path sourcePath = selectedFile.toPath();
            Path targetPath = Paths.get("src/main/resources/Users/Profile_Images/"+id_actual+".png");
            
            try {
                if (Files.notExists(targetPath.getParent())) {
                    Files.createDirectories(targetPath.getParent());
                }
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                
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
