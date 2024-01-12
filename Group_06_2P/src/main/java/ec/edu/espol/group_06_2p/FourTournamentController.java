/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Match;
import ec.edu.espol.Clases.Player;
import ec.edu.espol.Clases.Tournament;
import ec.edu.espol.Clases.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class FourTournamentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane page;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private Label player1;
    @FXML
    private Label player2;
    @FXML
    private Label player4;
    @FXML
    private Label player3;
    @FXML
    private Label final1;
    @FXML
    private Label final2;
    @FXML
    private Label winner;
    @FXML
    private Label player_jugar1;
    @FXML
    private Label player_jugar2;
    @FXML
    private Button btn_play;
    private User us;
    private ArrayList<Label> labels ;
    private ArrayList<Player> players ; 
    private static ArrayList<Match> matches ;
    private Player player1_match;
    private Player player2_match;
    private static Match match_Actual;
    private Tournament  torneo ;
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        matches = new ArrayList<>();
        us = User.getPassUser();
        torneo = Tournament.getPassTournament();
        System.out.println(torneo);
        players = torneo.getPlayers();
        getListLabel();        
        updateLabels();
        createMatches();
        updateMatch();
    }    

    private void getListLabel(){
        labels = new ArrayList<>();
        labels.add(player1);
        labels.add(player4);
        labels.add(player3);
        labels.add(player2);
    }
    private void updateLabels(){
        for(int i = 0 ; i<4; i++){
            labels.get(i).setText(players.get(i).getName());
        }
    }
    
    private void createMatches(){
        Match m1 = new Match(players.get(0),players.get(1),us);
        Match m2 = new Match(players.get(1),players.get(2),us);
        Match final_ = new Match(us);
        matches.add(m1);
        matches.add(m2);
        matches.add(final_);
    }
    
    private void updateMatch(){
        for(Match m : matches){
            if(m.isPlayed() && m.getP1()!=null && m.getP2()!=null){
                match_Actual = m;
                player1_match = m.getP1();
                player2_match = m.getP2();
                player_jugar1.setText(m.getP1().getName());
                player_jugar2.setText(m.getP2().getName());
            }
        }
    }
                
    @FXML
    private void regresar(MouseEvent event) throws IOException {
        User.passUser(us);
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) page.getScene().getWindow();
        stage.setScene(scene);  
    }

    @FXML
    private void play_match(MouseEvent event) throws IOException {
        User.passUser(us);
        Tournament.passTournament(torneo);
        Match.passMatch(match_Actual);
        Parent root = FXMLLoader.load(getClass().getResource("GameTournament.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) page.getScene().getWindow();
        stage.setScene(scene);  
    }
    
}
