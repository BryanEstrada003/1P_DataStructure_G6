/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Cuadro;
import ec.edu.espol.Clases.Games;
import ec.edu.espol.Clases.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class GameController implements Initializable {

    @FXML
    private BorderPane game;
    @FXML
    private HBox Hbox_top;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private Label victories_p1;
    @FXML
    private Label draws_p1;
    @FXML
    private Label defeats_p1;
    @FXML
    private ImageView image_user;
    @FXML
    private Label name_user;
    @FXML
    private Label user_play;
    @FXML
    private HBox Hbox_bottom;
    @FXML
    private ImageView image_computer;
    @FXML
    private Label name_computer;
    @FXML
    private Label computer_play;
    @FXML
    private Label victories_p2;
    @FXML
    private Label draws_p2;
    @FXML
    private Label defeats_p2;
    @FXML
    private Pane pane_game;
    @FXML
    private GridPane tres_en_raya;
    private boolean turno_user= true;
    private boolean turno_computer = false;
    private int winner_n = 0;
    private boolean draw = false;
    private int[][] games;
    private ArrayList<Cuadro> cuadros = new ArrayList<>();
    private User us1 ;
    @FXML
    private VBox Vbox_btn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us1 = User.getPassUser();
        games = new int[3][3];
        if(turno_user){
            user_play.setText("START");
            user_play.setStyle("-fx-text-fill: #0c57ed; -fx-font-weight: bold");
            computer_play.setText("");
        }
        else if(turno_computer){
            computer_play.setText("START");
            computer_play.setStyle("-fx-text-fill: #0c57ed; -fx-font-weight: bold");
            user_play.setText("");                    
        }
        setNameUser();
        paint_table();
    }    

    public void setName_computer(String name_computer) {
        this.name_computer.setText(name_computer);
    }
    
   public void setNameUser(){
       if(us1.getNickname() != null){
           name_user.setText(us1.getNickname());
       }
       else{
           name_user.setText(us1.getUser());
       }
   }
    
    public void paint_table(){
        for(int x = 0; x < 3 ;x++){
            for(int y = 0; y <3 ; y++){
                Cuadro c1 = new Cuadro(x, y,false);
                cuadros.add(c1);
                tres_en_raya.add(c1, y, x);
                c1.addEventHandler(MouseEvent.MOUSE_CLICKED,(Event t)->{
                    if(! c1.isOcupado()){
                        if(turno_user){
                            Platform.runLater(()->{
                                Image icon_x = new Image("Iconos_game/X_sinfondo.png");
                                ImageView imv1 = new ImageView(icon_x);
                                imv1.setFitWidth(100);
                                imv1.setFitHeight(100);
                                imv1.setPreserveRatio(true);
                                c1.getChildren().add(imv1);
                                c1.setOcupado(true);
                                computer_play.setText("YOUR TURN");
                                computer_play.setStyle("-fx-text-fill: #cc5d08; -fx-font-weight: bold");
                                user_play.setText("");
                            });
                            games[c1.getXpos()][c1.getYpos()] = 1;

                            
                        }
                        else if(turno_computer){
                            Platform.runLater(()->{
                                Image icon_x = new Image("Iconos_game/O_sinfondo.png");
                                ImageView imv1 = new ImageView(icon_x);
                                imv1.setFitWidth(100);
                                imv1.setFitHeight(100);
                                imv1.setPreserveRatio(true);
                                c1.getChildren().add(imv1);
                                c1.setOcupado(true);
                                user_play.setText("YOUR TURN");
                                user_play.setStyle("-fx-text-fill: #cc5d08; -fx-font-weight: bold");
                                computer_play.setText("");
                                
                            });
                            games[c1.getXpos()][c1.getYpos()] = 2;
                        }
                        validarGanador(games);
                        Platform.runLater(()->{
                            if(winner_n == 1){
                                user_play.setText("WINNER");
                                user_play.setStyle("-fx-text-fill: #08c20e; -fx-font-weight: bold");
                                computer_play.setText("LOSER");
                                computer_play.setStyle("-fx-text-fill: #c20e08; -fx-font-weight: bold");
                                victories_p1.setText( (Integer.parseInt(victories_p1.getText())+1) +"");
                                defeats_p2.setText( (Integer.parseInt(defeats_p2.getText())+1) +"");
                                int new_victories = us1.getVictories_players_2()+1;
                                us1.setVictories_players_2(new_victories);
                                
                            }
                            else if(winner_n == 2){
                                user_play.setText("LOSSER");
                                user_play.setStyle("-fx-text-fill: #c20e08; -fx-font-weight: bold");
                                computer_play.setText("WINNER");
                                computer_play.setStyle("-fx-text-fill: #08c20e; -fx-font-weight: bold");
                                victories_p2.setText( (Integer.parseInt(victories_p2.getText())+1) +"");
                                defeats_p1.setText( (Integer.parseInt(defeats_p1.getText())+1) +"");
                                int new_defeats = us1.getDefeats_players_2()+1;
                                us1.setDefeats_players_2(new_defeats);
                            }
                            if(draw){
                                user_play.setText("DRAW");
                                user_play.setStyle("-fx-text-fill: #0c57ed; -fx-font-weight: bold");
                                computer_play.setText("DRAW");
                                computer_play.setStyle("-fx-text-fill: #0c57ed; -fx-font-weight: bold");
                                draws_p1.setText( (Integer.parseInt(draws_p1.getText())+1) +"");
                                draws_p2.setText( (Integer.parseInt(draws_p2.getText())+1) +"");
                                int new_draws = us1.getDraws_players_2()+1;
                                us1.setDraws_players_2(new_draws);
                            }
                            if(winner_n>0 || draw){
                                allOcupated();
                                ArrayList<Games> history = us1.getHistory();
                                Games g1 = new Games(us1,name_computer.getText(),games,winner_n);
                                Games.add_game_file(g1, "HistoryGames.ser");
                                history.add(g1);
                                us1.setHistory(history);
                                User.updateUser(us1);
                                Platform.runLater(()->{
                                    createBtnNewGame();
                                });
                            }
                        });
                    if(turno_user == true)
                        turno_user = false;
                    else if(turno_user == false)
                        turno_user = true;
                    if(turno_computer == true)
                        turno_computer = false;
                    else if(turno_computer == false)
                        turno_computer = true;
                    }
                    
                });
            }
        }
    }
    public void createBtnNewGame(){
        Button btn_newgame = new Button("NEW GAME");
        Vbox_btn.getChildren().add(btn_newgame);
        btn_newgame.setPrefWidth(190);
        btn_newgame.setPrefHeight(190);
        btn_newgame.setStyle("-fx-font-size: 20px;");
        btn_newgame.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
            @Override
            public void handle(Event event) {
                games = new int[3][3];
                for(Cuadro c: cuadros){
                    c.setOcupado(false);
                    c.getChildren().clear(); 
                }
                Vbox_btn.getChildren().clear(); 
                winner_n = 0;
                draw = false;
                if(turno_user){
                    user_play.setText("START");
                    user_play.setStyle("-fx-text-fill: #0c57ed; -fx-font-weight: bold");
                    computer_play.setText("");
                }
                else if(turno_computer){
                    computer_play.setText("START");
                    computer_play.setStyle("-fx-text-fill: #0c57ed; -fx-font-weight: bold");
                    user_play.setText("");                    
                }
            }
        });
    }
    public void validarGanador(int[][] games) {
        for (int i = 0; i < 3; i++) {
            if (games[i][0] == games[i][1] && games[i][0] == games[i][2] && games[i][0] != 0) {
                winner_n = games[i][0];
                return;
            }
            if (games[0][i] == games[1][i] && games[0][i] == games[2][i] && games[0][i] != 0) {
                winner_n = games[0][i];
                return;
            }
        }
        if (games[0][0] == games[1][1] && games[0][0] == games[2][2] && games[0][0] != 0) {
            winner_n = games[0][0];
            return;
        }
        if (games[0][2] == games[1][1] && games[0][2] == games[2][0] && games[0][2] != 0) {
            winner_n = games[0][2];
            return;
        }
        // Verifica si hay empate
        if (isBoardFull(games)) {
            draw = true;
            return;
        }
    }

    private boolean isBoardFull(int[][] games) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (games[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public void allOcupated(){
        for (Cuadro c2 : cuadros){
            c2.setOcupado(true);
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
    
}
