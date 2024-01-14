/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Cuadro;
import ec.edu.espol.Clases.Games;
import ec.edu.espol.Clases.HistoryToReview;
import ec.edu.espol.Clases.Result;
import ec.edu.espol.Clases.User;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
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
    private static User us1;
    private int id_game_actual;
    private ArrayList<Games> history;
    private ArrayList<Games> history_2players = new ArrayList<Games>();
    private ArrayList<Games> history_computer = new ArrayList<Games>();
    private String path = "src/main/resources/Users/Profile_Images/";
    // saber si esta viendo el historial de partidas jugadas entre dos jugadores o con la computadora
    // true -- > 2 players
    // false --> computer
    private char state = 'P';
    @FXML
    private HBox hbox_btns;
    private static Games game_last;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("HISTORY CONTROLLLER ON");
        us1 = User.getPassUser();
        history = us1.getHistory();
        updateHistoryArrayLists();
        id_game_actual = history_2players.size() - 1;

        HistoryToReview h1 = HistoryToReview.getPassHistoryToReview();
        System.out.println("h1" + h1);
        System.out.println("h1.getState() != 'N': " + (h1.getState() != 'N'));
        if (h1.getState() != 'N') {
            id_game_actual = h1.getId_actual();
            state = h1.getState();
        }

        setNameUser();
        updateInfoResults();
        updateImageProfile();

        if (id_game_actual >= 0 && state == 'P') {
            updateLastGame(id_game_actual, history_2players);
            updateHboxBtns();
        } else if (id_game_actual >= 0 && state == 'C') {
            updateLastGame(id_game_actual, history_computer);
            updateHboxBtns();
        }

    }

    public int getId_game_actual() {
        return id_game_actual;
    }

    public void setId_game_actual(int id_game_actual) {
        this.id_game_actual = id_game_actual;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public void updateHistoryArrayLists() {
        for (Games g : history) {
            if (g.getName_player().compareTo("COMPUTER") == 0) {
                history_computer.add(g);
            } else {
                history_2players.add(g);
            }
        }
    }

    public void updateHboxBtns() {
        if (isOkupdateHboxBtns()) {
            hbox_btns.getChildren().clear();
            Button btn_tree = new Button("GENERAL TREE");
            Button btn_game = new Button("REVIEW GAME");
            btn_tree.setPrefWidth(150); // Ancho preferido
            btn_tree.setPrefHeight(50); // Altura preferida
            btn_game.setPrefWidth(150); // Ancho preferido
            btn_game.setPrefHeight(50); // Altura preferida
            hbox_btns.getChildren().addAll(btn_tree, btn_game);
            btn_tree.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
                @Override
                public void handle(Event event) {
                    try {
                        User.passUser(us1);
                        HistoryToReview h1 = new HistoryToReview(id_game_actual, state);
                        System.out.println("h1: " + h1);
                        HistoryToReview.passHistoryToReview(h1);
                        if (game_last != null) {
                            Games.passGame(game_last);
                        }
                        if (game_last == null) {
                            System.out.println("game_last es nulo");
                        }

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("generalTree.fxml"));
                        Parent root = loader.load();
                        GeneralTreeController generalTreeController = loader.getController();
                        Scene scene = new Scene(root);
                        // Crea un nuevo Stage
                        Stage newStage = new Stage();
                        // Asigna la escena al nuevo Stage
                        newStage.setScene(scene);
                        // Muestra el nuevo Stage
                        newStage.show();

                    } catch (IOException ex) {
                        System.out.println("se cae " + ex.getMessage());
                    }
                }
            });
            btn_game.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
                @Override
                public void handle(Event event) {
                    try {
                        User.passUser(us1);
                        HistoryToReview h1 = new HistoryToReview(id_game_actual, state);
                        HistoryToReview.passHistoryToReview(h1);
                        if (game_last != null) {
                            Games.passGame(game_last);
                        }
                        if (game_last == null) {
                            System.out.println("game_last es nulo");
                        }
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("review_game.fxml"));
                        Parent root = loader.load();
                        ReviewGameController reviewController = loader.getController();
                        Scene scene = new Scene(root);
                        // Crea un nuevo Stage
                        Stage newStage = new Stage();
                        // Asigna la escena al nuevo Stage
                        newStage.setScene(scene);
                        // Muestra el nuevo Stage
                        newStage.show();
                    } catch (IOException ex) {
                        System.out.println("se cae " + ex.getMessage());
                    }
                }

            });
        }

    }

    public boolean isOkupdateHboxBtns() {
        if (state == 'P') {
            if (history_2players.size() > 0) {
                return true;
            } else {
                return false;
            }
        } else if (state == 'C') {
            if (history_computer.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void updateImageProfile() {
        File file2 = new File(path + us1.getId_user() + ".png");
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
                System.out.println("Se cayo " + ex.getMessage());
            }
        } else {
            image_user.setImage(new Image("Iconos/relacion.png"));
        }
    }

    public void setNameUser() {
        if (us1.getNickname() != null) {
            name_user.setText(us1.getNickname());
            if (id_game_actual >= 0) {
                name_user1.setText(us1.getNickname());
            } else {
                name_user1.setText("");
            }
        } else {
            name_user.setText(us1.getUser());
            if (id_game_actual >= 0) {
                name_user1.setText(us1.getUser());
            } else {
                name_user1.setText("");
            }
        }
    }

    public void updateInfoResults() {
        if (state == 'P') {
            btn_2players.setStyle("-fx-border-color: green; -fx-border-width: 10px;");
            btn_computer.setStyle("-fx-border-width: 0;");
            Result results_players_2 = us1.getResults_players_2();
            num_vic.setText(results_players_2.getVictories() + "");
            num_draws.setText(results_players_2.getDraw() + "");
            num_def.setText(results_players_2.getDefeat() + "");
        } else if (state == 'C') {
            btn_computer.setStyle("-fx-border-color: green; -fx-border-width: 10px;");
            btn_2players.setStyle("-fx-border-width: 0;");
            Result results_computer = us1.getResults_computer();
            num_vic.setText(results_computer.getVictories() + "");
            num_draws.setText(results_computer.getDraw() + "");
            num_def.setText(results_computer.getDefeat() + "");
            updateLastGame(id_game_actual, history_computer);
        }

    }

    public void updateLastGame(int id_game, ArrayList<Games> history) {
        hbox_btns.getChildren().clear();
        if (id_game >= 0) {
            if (!history.isEmpty()) {
                game_last = history.get(id_game);
            }
            updateHboxBtns();
            setNameUser();
            name_user2.setText(game_last.getName_player());
            int ganador = game_last.getWinner();
            if (ganador == 1) {
                result1.setText("WINNER");
                result2.setText("LOSER");
            } else if (ganador == 2) {
                result2.setText("WINNER");
                result1.setText("LOSER");
            } else if (ganador == 0) {
                result2.setText("DRAW");
                result1.setText("DRAW");
            }
            updateGame(game_last.getMatriz_game());
        } else {
            result2.setText("");
            result1.setText("");
            name_user2.setText("");
            setNameUser();
        }
    }

    public void updateGame(int[][] matrizgame) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int num_matriz = matrizgame[x][y];
                Cuadro c1 = new Cuadro(x, y, false);
                if (num_matriz == 1) {
                    Image icon_x = new Image("Iconos_game/X_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(90);
                    imv1.setFitHeight(55);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                } else if (num_matriz == 2) {
                    Image icon_x = new Image("Iconos_game/O_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(90);
                    imv1.setFitHeight(55);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                }
                game.add(c1, y, x);
            }
        }
    }

    public void cleanGame() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                game.getChildren().clear();
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
        cleanGame();
        state = 'P';
        id_game_actual = history_2players.size() - 1;
        if (!history_2players.isEmpty()) {
            game_last = history_2players.get(id_game_actual);
        }
        Platform.runLater(() -> {
            btn_2players.setStyle("-fx-border-color: green; -fx-border-width: 10px;");
            btn_computer.setStyle("-fx-border-width: 0;");
            Result results_players_2 = us1.getResults_players_2();
            num_vic.setText(results_players_2.getVictories() + "");
            num_draws.setText(results_players_2.getDraw() + "");
            num_def.setText(results_players_2.getDefeat() + "");
            updateLastGame(id_game_actual, history_2players);
        });
    }

    @FXML
    private void updateInfo_Com(MouseEvent event) {
        cleanGame();
        state = 'C';
        id_game_actual = history_computer.size() - 1;
        if (!history_computer.isEmpty()) {
            game_last = history_computer.get(id_game_actual);
        }

        Platform.runLater(() -> {
            btn_computer.setStyle("-fx-border-color: green; -fx-border-width: 10px;");
            btn_2players.setStyle("-fx-border-width: 0;");
            Result results_computer = us1.getResults_computer();
            num_vic.setText(results_computer.getVictories() + "");
            num_draws.setText(results_computer.getDraw() + "");
            num_def.setText(results_computer.getDefeat() + "");
            updateLastGame(id_game_actual, history_computer);
        });

    }

    // Recordar que primero se muestran las ultimas partidas
//    @FXML
//    private void regresar_game(MouseEvent event) {
//        if( state == 'C'){
//            if(id_game_actual >= 0){
//                if(id_game_actual == history_computer.size()-1){
//                    id_game_actual = 0;
//                }
//                else{
//                    if(id_game_actual+1 <= history_computer.size()-1)
//                    id_game_actual ++;
//                }
//                Platform.runLater(()->{
//                    System.out.println("Actualizacion del juego con id " + id_game_actual);
//                    updateLastGame(id_game_actual,history_computer);
//                });   
//            }
//        }
//        else{
//            if(id_game_actual >= 0){
//                if(id_game_actual == history_2players.size()-1){
//                    id_game_actual = 0;
//                }
//                else{
//                    if(id_game_actual+1 <= history_2players.size()-1)
//                    id_game_actual ++;
//                }
//                Platform.runLater(()->{
//                    System.out.println("Actualizacion del juego con id " + id_game_actual);
//                    updateLastGame(id_game_actual,history_2players);
//                });   
//            }
//        }
//
//    }
//
//    @FXML
//    private void siguiente_game(MouseEvent event) {
//        
//        if( state == 'C'){
//            if(id_game_actual >= 0){
//                if(id_game_actual == history_computer.size()-1){
//                    id_game_actual = 0;
//                }
//
//                else{
//                    if(id_game_actual-1 >= 0){
//                        id_game_actual --;
//                    }
//                }
//                Platform.runLater(()->{
//                    System.out.println("Actualizacion del juego con id " + id_game_actual);
//                    updateLastGame(id_game_actual,history_computer);
//                });  
//            }
//        }
//        else{
//            if(id_game_actual >= 0){
//                if(id_game_actual == history_2players.size()-1){
//                    id_game_actual = 0;
//                }
//                else{
//                    if(id_game_actual-1 >= 0){
//                        id_game_actual --;
//                    }
//                }
//                Platform.runLater(()->{
//                    System.out.println("Actualizacion del juego con id " + id_game_actual);
//                    updateLastGame(id_game_actual,history_2players);
//                });  
//            }            
//        }
//    }
    @FXML
    private void regresar_game(MouseEvent event) {
        // This is to navigate to the previous game
        if (id_game_actual >= 0) {
            if (id_game_actual == 0) {
                id_game_actual = getCurrentHistory().size() - 1;
            } else {
                id_game_actual--;
            }
            updateGameActual(id_game_actual);
            updateGameDisplay();
        }
    }

    @FXML
    private void siguiente_game(MouseEvent event) {
        // This is to navigate to the next game
        if (id_game_actual >= 0) {
            id_game_actual = (id_game_actual + 1) % getCurrentHistory().size();
            updateGameActual(id_game_actual);
            updateGameDisplay();

        }
    }

    private ArrayList<Games> getCurrentHistory() {
        ArrayList<Games> currentHistory;
        if (state == 'C') {
            currentHistory = history_computer;
        } else {
            currentHistory = history_2players;
        }
        return currentHistory;
    }

    private void updateGameDisplay() {
        Platform.runLater(() -> {
            System.out.println("Actualización del juego con id " + id_game_actual);
            updateLastGame(id_game_actual, getCurrentHistory());
        });
    }

    private void updateGameActual(int id) {
        if (state == 'C') {
            game_last = history_computer.get(id);
        } else {
            game_last = history_2players.get(id);
        }
    }

}
