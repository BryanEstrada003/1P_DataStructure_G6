/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Cuadro;
import ec.edu.espol.Clases.Games;
import ec.edu.espol.Clases.Result;
import ec.edu.espol.Clases.User;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class HistoryController implements Initializable {

    @FXML
    private BorderPane history_page;
    @FXML
    private HBox header;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private Label name_user;
    @FXML
    private Label name_user1;
    @FXML
    private Label name_user2;
    @FXML
    private Label result1;
    @FXML
    private ImageView anterior;
    @FXML
    private ImageView proximo;
    @FXML
    private GridPane game;
    @FXML
    private Button btn_2players;
    @FXML
    private Button btn_computer;
    @FXML
    private Label result2;
    @FXML
    private Label num_vic;
    @FXML
    private Label num_draws;
    @FXML
    private Label num_def;
    @FXML
    private ImageView image_user;
    private User us1;
    private int id_game_actual ; 
    private ArrayList<Games> history;
    private ArrayList<Games> history_2players = new ArrayList<Games>();
    private ArrayList<Games> history_computer = new ArrayList<Games>();
    private String  path = "src/main/resources/Users/Profile_Images/";
    // saber si esta viendo el historial de partidas jugadas entre dos jugadores o con la computadora
    // true -- > 2 players
    // false --> computer
    private char state = 'P';
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us1 = User.getPassUser();
        history = us1.getHistory();
        updateHistoryArrayLists();
        id_game_actual = history_2players.size()-1;
        setNameUser();
        updateInfoResults();
        updateImageProfile();
        if(id_game_actual >= 0){
           updateLastGame(id_game_actual,history_2players);
        }

        
    }  
    public void updateHistoryArrayLists(){
        for(Games g : history){
            if(g.getName_player().compareTo("COMPUTER")== 0){
                history_computer.add(g);
            }
            else{
                history_2players.add(g);
            }
        }
    }
   
    public void updateImageProfile(){
        File file2 = new File( path + us1.getId_user() + ".png");
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
                image_user.setImage(new Image("Iconos/relacion.png"));
            }
    }
    public void setNameUser(){
       if(us1.getNickname() != null){
           name_user.setText(us1.getNickname());
           if(id_game_actual >= 0){
                name_user1.setText(us1.getNickname());
           }
           else{
               name_user1.setText("");
           }
       }
       else{
           name_user.setText(us1.getUser());
           if(id_game_actual >= 0){
                name_user1.setText(us1.getUser());
           }
           else{
               name_user1.setText("");
           }
       }
    }
    public void updateInfoResults(){
        btn_2players.setStyle("-fx-border-color: green; -fx-border-width: 10px;");
        btn_computer.setStyle("-fx-border-width: 0;");
        Result results_players_2 = us1.getResults_players_2();
        num_vic.setText(results_players_2.getVictories()+"");
        num_draws.setText(results_players_2.getDraw()+"");
        num_def.setText(results_players_2.getDefeat()+"");  
    }
    public void updateLastGame(int id_game,ArrayList<Games> history){
        if(id_game>= 0){
            Games game_last = history.get(id_game);
            name_user2.setText(game_last.getName_player());
            int ganador = game_last.getWinner();
            if(ganador == 1 ){
                result1.setText("WINNER");
                result2.setText("LOSER");  
            }
            else if(ganador == 2 ){
                result2.setText("WINNER");
                result1.setText("LOSER");  
            }
            else if(ganador == 0) {
                result2.setText("DRAW");
                result1.setText("DRAW");              
            }
            updateGame(game_last.getMatriz_game());
        }
        else{
            result2.setText("");
            result1.setText("");
            name_user2.setText("");
            setNameUser();
        }

        
    }
    
    public void updateGame(int [][] matrizgame){
        for(int x = 0; x < 3 ;x++){
            for(int y = 0; y <3 ; y++){
                int num_matriz = matrizgame[x][y];
                Cuadro c1 = new Cuadro(x, y,false);
                if(num_matriz == 1){
                    Image icon_x = new Image("Iconos_game/X_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(100);
                    imv1.setFitHeight(64);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);                    
                }
                else if(num_matriz == 2){
                    Image icon_x = new Image("Iconos_game/O_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(100);
                    imv1.setFitHeight(64);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                }
                game.add(c1, y, x);
            }
        }
    }
    
    @FXML
    private void regresar(MouseEvent event) throws IOException {
        User.passUser(us1);
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) game.getScene().getWindow();
        stage.setScene(scene);  
    }

    @FXML
    private void updateInfo_P(MouseEvent event) {
        state = 'P';
        id_game_actual = history_2players.size()-1;
        Platform.runLater(()->{
            btn_2players.setStyle("-fx-border-color: green; -fx-border-width: 10px;");
            btn_computer.setStyle("-fx-border-width: 0;");
            Result results_players_2 = us1.getResults_players_2();
            num_vic.setText(results_players_2.getVictories()+"");
            num_draws.setText(results_players_2.getDraw()+"");
            num_def.setText(results_players_2.getDefeat()+"");
            updateLastGame(id_game_actual,history_2players);
        });
    }

    @FXML
    private void updateInfo_Com(MouseEvent event) {
        state = 'C';
        id_game_actual = history_computer.size()-1;
        Platform.runLater(()->{
            btn_computer.setStyle("-fx-border-color: green; -fx-border-width: 10px;");
            btn_2players.setStyle("-fx-border-width: 0;");
            Result results_computer = us1.getResults_computer();
            num_vic.setText(results_computer.getVictories()+"");
            num_draws.setText(results_computer.getDraw()+"");
            num_def.setText(results_computer.getDefeat()+"");   
            updateLastGame(id_game_actual,history_computer);
        });
             
    }

    // Recordar que primero se muestran las ultimas partidas
    @FXML
    private void regresar_game(MouseEvent event) {
        if( state == 'C'){
            if(id_game_actual >= 0){
                if(id_game_actual == history_computer.size()-1){
                    id_game_actual = 0;
                }
                else{
                    if(id_game_actual+1 <= history_computer.size()-1)
                    id_game_actual ++;
                }
                Platform.runLater(()->{
                    updateLastGame(id_game_actual,history_computer);
                });   
            }
        }
        else{
            if(id_game_actual >= 0){
                if(id_game_actual == history_2players.size()-1){
                    id_game_actual = 0;
                }
                else{
                    if(id_game_actual+1 <= history_2players.size()-1)
                    id_game_actual ++;
                }
                Platform.runLater(()->{
                    updateLastGame(id_game_actual,history_2players);
                });   
            }
        }

    }

    @FXML
    private void siguiente_game(MouseEvent event) {
        if( state == 'C'){
            if(id_game_actual >= 0){
                if(id_game_actual == history_computer.size()-1){
                    id_game_actual = 0;
                }
                else{
                    if(id_game_actual-1 >= 0){
                        id_game_actual --;
                    }
                }
                Platform.runLater(()->{
                    updateLastGame(id_game_actual,history_computer);
                });  
            }
        }
        else{
            if(id_game_actual >= 0){
                if(id_game_actual == history_2players.size()-1){
                    id_game_actual = 0;
                }
                else{
                    if(id_game_actual-1 >= 0){
                        id_game_actual --;
                    }
                }
                Platform.runLater(()->{
                    updateLastGame(id_game_actual,history_2players);
                });  
            }            
        }
    }
    
}
