/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.User;
import ec.edu.espol.TDAs.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane home;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private ImageView logo_app;
    @FXML
    private GridPane grid_pane_info;
    @FXML
    private ImageView image_user;
    @FXML
    private SplitMenuButton menu_options;
    @FXML
    private Label user_name;
    @FXML
    private Button btn_easy;
    @FXML
    private Button btn_difficult;
    private User user1;
    private String  path = "src/main/resources/Users/Profile_Images/";
    @FXML
    private AnchorPane back_info_home;
    @FXML
    private AnchorPane back_info_home1;
    @FXML
    private GridPane grid_pane_info1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        user1 = getPassUser();
        home.getStyleClass().add("blackbackgorund");
        btn_difficult.getStyleClass().add("button");
        btn_easy.getStyleClass().add("button");
        back_info_home.getStyleClass().add("blackbackgorund");
        back_info_home1.getStyleClass().add("blackbackgorund");
        grid_pane_info.getStyleClass().add("back_info");
        grid_pane_info1.getStyleClass().add("back_info");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        completInfo();
    }    

    public User getPassUser(){
        User u1 = new User();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("User_provisional.ser")))
        {
            u1 = (User)in.readObject();
        }
        catch(IOException ioe){  
        }
        catch(ClassNotFoundException c ){        
        }
        return u1;
    }
    
    public void completInfo(){
        if(user1 != null ){
            if(user1.getNickname() != null) {
                user_name.setText(user1.getNickname().toUpperCase());
            }
            else if(user1.getUser() != null){
                user_name.setText(user1.getUser().toUpperCase());
            }
               
            File file2 = new File( path + user1.getId_user() + ".png");
            if (file2.exists()) {
                try {
                    String imagePath = file2.toURI().toURL().toExternalForm();
                    Image image = new Image(imagePath);
                    Platform.runLater(() -> {
                        image_user.setImage(image);
                        Circle clipCircle = new Circle(image_user.getFitWidth() / 2, image_user.getFitHeight() / 2, 50); // Ajusta el radio según sea necesario
                        image_user.setClip(clipCircle);
                    });
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("la imagen no existe");
                image_user.setImage(new Image("Iconos/relacion.png"));
            }
            File archivo = new File("User_provisional.ser");
            if (archivo.exists()) {
                archivo.delete();
            }
        }
        else{
            user_name.setText("USER !!");
            image_user.setImage(new Image("Iconos/relacion.png"));
        }
    }
    
    @FXML
    private void regresar(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) home.getScene().getWindow();
        stage.setScene(scene);  
    }

    @FXML
    private void edit_profile(ActionEvent event) {
        FileChooser file = new FileChooser();
        file.setTitle("CHOOSE PROFILE PICTURE ");
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.avif","*.jpeg"));
        File selectedFile = file.showOpenDialog(null);
        
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            image_user.setImage(image);
            Circle clipCircle = new Circle(image_user.getFitWidth() / 2, image_user.getFitHeight() / 2, 50);
            image_user.setClip(clipCircle);
            
            Path sourcePath = selectedFile.toPath();
            Path targetPath = Paths.get("src/main/resources/Users/Profile_Images/"+user1.getId_user()+".png");
            
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
    
}
