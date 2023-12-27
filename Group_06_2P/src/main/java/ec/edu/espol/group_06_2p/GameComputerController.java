/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Cuadro;
import ec.edu.espol.Clases.Games;
import ec.edu.espol.Clases.User;
import ec.edu.espol.TDAs.Tree;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class GameComputerController implements Initializable {

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
    private boolean turno_user = true;
    private boolean turno_computer = false;
    private int winner_n = 0;
    private boolean draw = false;
    private int[][] games;
    private ArrayList<Cuadro> cuadros = new ArrayList<>();
    private User us1;
    @FXML
    private VBox Vbox_btn;
    private boolean new_game = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us1 = User.getPassUser();
        games = new int[3][3];
        setNameUser();
        paint_table();
        // turnos validar porque solo tenemos si es turno_user o turno_computer
        int[][] result = miniMax(games, 1);
       
    }
    public void setName_computer(String name_computer) {
        this.name_computer.setText(name_computer);
    }

    public void setNameUser() {
        if (us1.getNickname() != null) {
            name_user.setText(us1.getNickname());
        } else {
            name_user.setText(us1.getUser());
        }
    }

    public void paint_table() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Cuadro c1 = new Cuadro(x, y, false);
                cuadros.add(c1);
                tres_en_raya.add(c1, y, x);
                c1.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                    if (!c1.isOcupado() && turno_user) {
                        if (turno_user) {
                            Platform.runLater(() -> {
                                Image icon_x = new Image("Iconos_game/X_sinfondo.png");
                                ImageView imv1 = new ImageView(icon_x);
                                imv1.setFitWidth(100);
                                imv1.setFitHeight(100);
                                imv1.setPreserveRatio(true);
                                c1.getChildren().add(imv1);
                                c1.setOcupado(true);
                                computer_play.setText("YOUR TURN");
                                user_play.setText("");
                            });
                            games[c1.getXpos()][c1.getYpos()] = 1;                      
                            // aqui responde la computadora
                            changeTurns();                        
                        }
                        validarGanador(games);
                        updateWinner_Loser();
                        if(winner_n == 0){
                            if (turno_computer) {
                                PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
                                pause.setOnFinished(event -> computer());
                                pause.play(); 
                            }
                        }
                    }

                });
            }
        }
    }
    public void changeTurns(){
        if (turno_user == true)
            turno_user = false;
        else if (turno_user == false)
            turno_user = true;
        if (turno_computer == true)
            turno_computer = false;
        else if (turno_computer == false)
            turno_computer = true;
    }

    public void updateWinner_Loser(){
        Platform.runLater(() -> {
            if (winner_n == 1) {
                user_play.setText("WINNER");
                computer_play.setText("LOSER");
                victories_p1.setText((Integer.parseInt(victories_p1.getText()) + 1) + "");
                defeats_p2.setText((Integer.parseInt(defeats_p2.getText()) + 1) + "");
                int new_victories = us1.getVictories_computer() + 1;
                us1.setVictories_computer(new_victories);
            } else if (winner_n == 2) {
                user_play.setText("LOSSER");
                computer_play.setText("WINNER");
                victories_p2.setText((Integer.parseInt(victories_p2.getText()) + 1) + "");
                defeats_p1.setText((Integer.parseInt(defeats_p1.getText()) + 1) + "");
                int new_defeats = us1.getDefeats_computer() + 1;
                us1.setDefeats_computer(new_defeats);
            }
            if (draw) {
                user_play.setText("DRAW");
                computer_play.setText("DRAW");
                draws_p1.setText((Integer.parseInt(draws_p1.getText()) + 1) + "");
                draws_p2.setText((Integer.parseInt(draws_p2.getText()) + 1) + "");
                int new_draws = us1.getDraws_computer() + 1;
                us1.setDraws_computer(new_draws);
            }
            if (winner_n > 0 || draw) {
                new_game = true;
                allOcupated();
                ArrayList<Games> history = us1.getHistory();
                Games g1 = new Games(us1, name_computer.getText(), games, winner_n);
                Games.add_game_file(g1, "HistoryComputerGames.ser");
                history.add(g1);
                us1.setHistory(history);
                User.updateUser(us1);
                Platform.runLater(() -> {
                    createBtnNewGame();
                });
            }
        });
    }
    
    public void computer(){
        // la computadora siempre es jugador que se representa con el 2
        int[][] matriz_r = miniMax(games, 2);
        ArrayList<int[]> coord = getNextMovement(games, matriz_r);
        // Solución rapida
//        if(coord.isEmpty()){
//            coord = getLastMove(games);
//        }
        if(!coord.isEmpty()) {
            int[] nextMove = coord.get(0);
            Node node = getNodeFromGridPane(nextMove[0], nextMove[1], tres_en_raya);
            if (node instanceof Cuadro) {
                Cuadro c1 = (Cuadro) node;
                Platform.runLater(() -> {
                    Image icon_x = new Image("Iconos_game/O_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(100);
                    imv1.setFitHeight(100);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                    c1.setOcupado(true);
                    user_play.setText("YOUR TURN");
                    computer_play.setText("");
                });
                // Actualizar el estado del juego
                games[c1.getXpos()][c1.getYpos()] = 2;
            }
            changeTurns();
            validarGanador(games);
            updateWinner_Loser();
        }
        
    }
    public ArrayList<int[]> getLastMove(int[][]matrix){
        ArrayList<int[]> coord = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] == 0) {
                        coord.add(new int[]{i, j});
                    }
                }
            }  
        return coord;
    }
    public Node getNodeFromGridPane(final int row, final int column, GridPane games) {
        Node result = null;
        ObservableList<Node> childrens = games.getChildren();
        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    public ArrayList<int[]> getNextMovement(int[][] matrix1, int[][] matrix2) {
        ArrayList<int[]> coord = new ArrayList<>();
        
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
           for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    if (matrix1[i][j] != matrix2[i][j]) {
                        coord.add(new int[]{i, j});
                    }
                }
            }  
        }
        return coord;
    }
    
    public void createBtnNewGame() {
        Button btn_newgame = new Button("NEW GAME");
        Vbox_btn.getChildren().add(btn_newgame);
        btn_newgame.setPrefWidth(190);
        btn_newgame.setPrefHeight(190);
        btn_newgame.setStyle("-fx-font-size: 20px;");
        btn_newgame.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {  // Cambiado a MouseEvent
                games = new int[3][3];
                for (Cuadro c : cuadros) {
                    c.setOcupado(false);
                    c.getChildren().clear();
                }
                Vbox_btn.getChildren().clear();
                winner_n = 0;
                draw = false;
                if(turno_user){
                    computer_play.setText("");
                    user_play.setText("START");
                }
                else if(turno_computer){
                    user_play.setText("");
                    computer_play.setText("START");
                    if(new_game){
                        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
                        pause.setOnFinished(e -> computer());  // e es el parámetro del lambda
                        pause.play();
                    }
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

    public void allOcupated() {
        for (Cuadro c2 : cuadros) {
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

    // Proyecto
    public int pj(int player,int[][] games) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            if (validar_neighbourRow(i, player,games)) {
                total++;
            }
            if (validar_neighbourCol(i, player,games)) {
                total++;
            }
        }
        if (validar_diagonal1(player,games)) {
            total++;
        }
        if (validar_diagonal2(player,games)) {
            total++;
        }
        int zeros = fila_col_dia_zeros(games);
        return total + zeros;
    }

    public int utilidadTablero(int px, int po, int jugador, int [][] games) {
        if (jugador == 1) {
            return pj(px,games) - pj(po,games);
        } else if (jugador == 2) {
            return pj(po,games) - pj(px,games);
        }
        return 0;
    }

    public boolean validar_neighbourRow(int fila, int jugador,int [][] games) {
        for (int ic = 0; ic < 3; ic++) {
            boolean c0 = (games[fila][ic] == games[fila][0]) || (games[fila][0] == 0);
            boolean c1 = (games[fila][ic] == games[fila][1]) || (games[fila][1] == 0);
            boolean c2 = (games[fila][ic] == games[fila][2]) || (games[fila][2] == 0);
            if ((games[fila][ic] == jugador) && c0 && c1 && c2) {
                return true;
            }
        }
        return false;

    }

    public boolean validar_neighbourCol(int col, int jugador,int [][] games) {
        for (int i_f = 0; i_f < 3; i_f++) {
            boolean c0 = (games[i_f][col] == games[0][col]) || (games[0][col] == 0);
            boolean c1 = (games[i_f][col] == games[1][col]) || (games[1][col] == 0);
            boolean c2 = (games[i_f][col] == games[2][col]) || (games[2][col] == 0);
            if ((games[i_f][col] == jugador) && c0 && c1 && c2) {
                return true;
            }
        }
        return false;
    }

    public boolean validar_diagonal1(int jugador,int [][] games) {
        for (int i = 0; i < 3; i++) {
            boolean c0 = (games[i][i] == games[0][0]) || (games[0][0] == 0);
            boolean c1 = (games[i][i] == games[1][1]) || (games[1][1] == 0);
            boolean c2 = (games[i][i] == games[2][2]) || (games[2][2] == 0);
            if ((games[i][i] == jugador) && c0 && c1 && c2) {
                return true;
            }
        }
        return false;
    }

    public boolean validar_diagonal2(int jugador,int [][] games) {
        for (int i = 0; i < 3; i++) {
            int j = 0;
            switch (i) {
                case 0:
                    j = 2;
                    break;
                case 1:
                    j = 1;
                    break;
                case 2:
                    j = 0;
                    break;
                default:
                    break;
            }
            boolean c0 = (games[i][j] == games[0][2]) || (games[0][2] == 0);
            boolean c1 = (games[i][j] == games[1][1]) || (games[1][1] == 0);
            boolean c2 = (games[i][j] == games[2][0]) || (games[2][0] == 0);
            if ((games[i][j] == jugador) && c0 && c1 && c2) {
                return true;
            }
        }
        return false;
    }

    public int fila_col_dia_zeros(int [][]games) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            if (games[i][0] == games[i][1] && games[i][0] == games[i][2] && games[i][0] == 0) {
                total++;
            }
            if (games[0][i] == games[1][i] && games[0][i] == games[2][i] && games[0][i] == 0) {
                total++;
            }
        }
        if (games[0][0] == games[1][1] && games[0][0] == games[2][2] && games[0][0] == 0) {
            total++;
        }
        if (games[0][2] == games[1][1] && games[0][2] == games[2][0] && games[0][2] == 0) {
            total++;
        }
        return total;
    }

    // posible
    public LinkedList<int[][]> posiblesEstados1(int jugador, int[][] games) {
        LinkedList<int[][]> lista = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((games[i][j] == 0)) {
                    int[][] matriz_ac = CopyMatrix(games);
                    matriz_ac[i][j] = jugador;
                    lista.add(matriz_ac);
                }
            }
        }
        return lista;
    }

    public LinkedList<int[][]> posiblesEstados2(int jugador, int[][] games) {
        LinkedList<int[][]> lista = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isEmpty(games) && (games[i][j] == 0)) {
                    int[][] matriz_ac = CopyMatrix(games);
                    matriz_ac[i][j] = changePlayer(jugador);
                    lista.add(matriz_ac);
                }
            }
        }
        return lista;
    }

    public boolean isEmpty(int[][] games) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (games[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] CopyMatrix(int[][] original) {
        if (original == null) {
            return null;
        }
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = new int[original[i].length];
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }

    public int changePlayer(int jugador) {
        if (jugador == 1) {
            return 2;
        } else if (jugador == 2) {
            return 1;
        }
        return 0;
    }

    public int[][] miniMax(int[][] games, int jugador) {
        Tree treeMatriz = new Tree();
        treeMatriz.setRoot(games);
        int[][] matriz = new int[3][3];
        LinkedList<int[][]> matrices1 = posiblesEstados1(jugador, games);
        LinkedList<Integer> utilidades1 = new LinkedList<>();
        for (int[][] m1 : matrices1) {
//            System.out.println("MATRICES 1");
//            imprimirMatriz(m1);
            
            LinkedList<int[][]> matrices2 = posiblesEstados2(jugador, m1);
            LinkedList utilidades2 = new LinkedList<>();
            Tree children = new Tree(m1);
            treeMatriz.addChildren(children);
            for (int[][] m2 : matrices2) {
                Tree children2 = new Tree(m2);
                int utilidad = utilidadTablero(1,2, jugador,m2) ; 
                utilidades2.add(utilidad);
                children.addChildren(children2);
                
//                System.out.println("MATRICES 2 PARA MATRICES 1");
//                imprimirMatriz(m2);
//                System.out.println(utilidad);    
                
            }
//            System.out.println("Lista de utilidades ");
//            printLinkedList(utilidades2);
            int min_util = (int) Collections.min(utilidades2);
//            System.out.println("Minimo-> "+ min_util);
            utilidades1.add(min_util);
        }

        int max_util = (int) Collections.max(utilidades1);
        int index = utilidades1.indexOf(max_util);
        matriz = matrices1.get(index);
        
//        System.out.println("Lista de Minimos ");
//        printLinkedList(utilidades1); 
//        System.out.println("Maximo de los minimos --> " + max_util+ "");
//        imprimirMatriz(matriz);
        
        return matriz;
    }
    
//    public static void imprimirMatriz(int[][] matriz) {
//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz[i].length; j++) {
//                System.out.print(matriz[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//    public void printLinkedList(LinkedList<Integer> list) {
//        if (list == null || list.isEmpty()) {
//            System.out.println("[]");
//            return;
//        }
//        
//        System.out.print("[");
//        
//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next());
//            if (iterator.hasNext()) {
//                System.out.print(", ");
//            }
//        }
//        
//        System.out.println("]");
//    }


}