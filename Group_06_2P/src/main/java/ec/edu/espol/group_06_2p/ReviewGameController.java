/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Cuadro;
import ec.edu.espol.Clases.Games;
import ec.edu.espol.Clases.HistoryToReview;
import ec.edu.espol.Clases.User;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class ReviewGameController implements Initializable {

    @FXML
    private AnchorPane game;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private HBox n1;
    @FXML
    private GridPane game1;
    @FXML
    private HBox n2;
    @FXML
    private GridPane game2;
    @FXML
    private HBox n3;
    @FXML
    private GridPane game3;
    @FXML
    private HBox n4;
    @FXML
    private GridPane game4;
    @FXML
    private HBox n5;
    @FXML
    private GridPane game5;
    @FXML
    private HBox n6;
    @FXML
    private GridPane game6;
    @FXML
    private HBox n7;
    @FXML
    private GridPane game7;
    @FXML
    private HBox n8;
    @FXML
    private GridPane game8;
    @FXML
    private HBox n9;
    @FXML
    private GridPane game9;

    private ArrayList<HBox> hboxs = new ArrayList<>();
    private ArrayList<GridPane> gridpanes = new ArrayList<>();
    private ArrayList<Games> history;
    private ArrayList<Games> history_2players = new ArrayList<Games>();
    private ArrayList<Games> history_computer = new ArrayList<Games>();
    private Games gameActual;
    // cosas del anterior controlador
    private User us1 ;
    private int id_actual;
    private char state = 'P';
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hboxs.addAll(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9));
        gridpanes.addAll(Arrays.asList(game1,game2,game3,game4,game5,game6,game7,game8,game9));
        us1 = User.getPassUser();
        
        HistoryToReview h1 = HistoryToReview.getPassHistoryToReview();
        id_actual = h1.getId_actual();
        state = h1.getState();       
        history = us1.getHistory();
        updateHistoryArrayLists();
        setgameActual_info();
        updateInfo(gameActual);
    }  
    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int getId_actual() {
        return id_actual;
    }

    public void setId_actual(int id_actual) {
        this.id_actual = id_actual;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }
    
    public void setgameActual_info(){
        if( state == 'P'){
            gameActual = history_2players.get(id_actual);
        }
        else if (state == 'C'){
            gameActual = history_computer.get(id_actual);
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

    public void updateInfo(Games gameActual){
        ArrayList<int [][]> tableros = gameActual.getTableros();
        System.out.println(tableros.size()-1);
        int  j = 0 ; 
        for(int i = 0 ; i < tableros.size()-1; i++){
            Label l = new Label((i+1)+"");
            l.getStyleClass().add("mi-label");
            hboxs.get(i).getChildren().add(l);
            updateGame(tableros.get(i),gridpanes.get(i));
        }
        
    }
    public void updateGame(int[][] matrizgame, GridPane game) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int num_matriz = matrizgame[x][y];
                Cuadro c1 = new Cuadro(x, y, false);
                if (num_matriz == 1) {
                    Image icon_x = new Image("Iconos_game/X_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(30);
                    imv1.setFitHeight(30);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                } else if (num_matriz == 2) {
                    Image icon_x = new Image("Iconos_game/O_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(30);
                    imv1.setFitHeight(30);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                }
                game.add(c1, y, x);
            }
        }
    }
    @FXML
    private void regresar(MouseEvent event) throws IOException { 
        /// mandar todo lo necesario 
       User.passUser(us1);
       HistoryToReview h1 = new HistoryToReview(id_actual,state);
       HistoryToReview.passHistoryToReview(h1);
       FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
       Parent root = loader.load();
       HistoryController historyController = loader.getController();
       Scene scene = new Scene(root);
       Stage stage = (Stage) game.getScene().getWindow(); 
       stage.setScene(scene);
    }
    
}
