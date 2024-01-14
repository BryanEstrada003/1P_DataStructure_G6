/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Cuadro;
import ec.edu.espol.Clases.Games;
import ec.edu.espol.Clases.HistoryToReview;
import ec.edu.espol.Clases.User;
import ec.edu.espol.TDAs.Heap;
import ec.edu.espol.TDAs.Tree;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class GeneralTreeController implements Initializable {

    @FXML
    private ImageView flecha_regresar;
    @FXML
    private VBox vbox_tree;
    private LinkedList<int[][]> l_matrices = new LinkedList<>(); 
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
    //llevar el control
    private int jugador = 1;
    TreeItem<GridPane>  tree_guardado ;
    TreeItem<GridPane>  tree_matrizIgual;
    private ArrayList<String> colores;
    private boolean isEqualMinimax;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateColores();
        us1 = User.getPassUser();
        HistoryToReview h1 = HistoryToReview.getPassHistoryToReview();
        
        System.out.println("Id recibido");
        state = h1.getState();       
        history = us1.getHistory();
//        updateHistoryArrayLists();
//        setgameActual_info();
        gameActual = Games.getPassGame();
        System.out.println(gameActual);
        TreeItem<GridPane> generalTree = createTree();
        generalTree.setExpanded(true);
        TreeView<GridPane> treeView = new TreeView<>(generalTree);
        treeView.setShowRoot(true);
        treeView.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
        treeView.setCellFactory(tv -> new TreeCell<GridPane>() {
            @Override
            protected void updateItem(GridPane item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(5); 
                    hbox.setAlignment(Pos.CENTER_LEFT); 
                    
                    TreeItem<GridPane> treeItem = getTreeItem();
                    // La raiz 
                    if (treeItem.getParent() == null) {
                         hbox.getChildren().add(item); 
                    }
                    else if (treeItem instanceof ColoredTreeItem) {
                        ColoredTreeItem coloredItem = (ColoredTreeItem) treeItem;
                        int level = coloredItem.getLevel();
                        String color = colores.get(level % colores.size());
                        int padding = level * 50;
                        item.setStyle("-fx-background-color: " + color + ";");
                        item.setPadding(new Insets(0, 20, 0, padding));
                        hbox.getChildren().add(item); 
                        
                        
                        if (coloredItem.isBestoption()) {
                            Label bestOptionLabel = new Label("Best Option");
                            bestOptionLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
                            hbox.getChildren().add(bestOptionLabel); 
                        }                     
                    }
                    setGraphic(hbox);
                }
            }
        });
        vbox_tree.getChildren().add(treeView);        
    }    
    
    public class ColoredTreeItem extends TreeItem<GridPane> {
        private int level;
        private boolean bestoption ; 

        public ColoredTreeItem(GridPane value, int level, boolean bestoption) {
            super(value);
            this.level = level;
            this.bestoption = bestoption;
        }

        public int getLevel() {
            return level;
        }

        public boolean isBestoption() {
            return bestoption;
        }
        
    }
    public void updateColores(){
        colores = new ArrayList<String>();
        colores.add("#FFD1DC"); // Pastel Pink
        colores.add("#AEC6CF"); // Pastel Blue
        colores.add("#77DD77"); // Pastel Green
        colores.add("#FDFD96"); // Pastel Yellow
        colores.add("#C3B1E1"); // Pastel Purple
        colores.add("#FFB347"); // Pastel Orange
        colores.add("#CFCFC4"); // Pastel Gray
        colores.add("#FF6961"); // Pastel Red
        colores.add("#B4E7CE"); // Pastel Mint
    }
    public TreeItem<GridPane> createTree(){
        TreeItem<GridPane> tree = new TreeItem<>();
        int[][] root_m = new int[3][3];
        ArrayList<int[][]> tableros = gameActual.getTableros();
        System.out.println(tableros.size());
        l_matrices.add(root_m);
        l_matrices.addAll(tableros);
        jugador = starting_player(tableros.get(0));
        System.out.println("el jugador actual es :" + jugador);
        GridPane g1 = newGridPane(root_m);
        tree.setValue(g1);
        int[][] m_guardada ;
        tree_guardado = tree ;
        tree_matrizIgual = tree ;
        
        // aqui realmente es hasta una antes de la ultima porque en la ultima ya es gano o empato
        // en el peor de los casos esta llena
        // en si vamos a recorrer l_matrices tantas veces como cantidad de tableros tengamos 
//        for(int[][] m1 : l_matrices)
        for(int u = 0 ; u<tableros.size()-1 ; u++){
            int[][] m1 = l_matrices.get(u);
            LinkedList<int[][]> posiblesEstados1 = posiblesEstados1(jugador , m1);
            int[][] miniMax = miniMax(m1,jugador);
            String color = colores.get(u);
            for(int[][] m_posible : posiblesEstados1){
                GridPane g2 = newGridPane(m_posible);
//                TreeItem<GridPane> t_g2 = new TreeItem<>();
//                t_g2.setValue(g2);
                isEqualMinimax = Arrays.deepEquals(m_posible, miniMax);
                ColoredTreeItem t_g2 = new ColoredTreeItem(g2, u + 1,isEqualMinimax); // u+1 porque es el nivel del hijo
                tree_guardado.getChildren().add(t_g2);
                //es comparar la matriz siguiente a la del for
                // entonces seria u+1
                boolean matriz_igual = Arrays.deepEquals(l_matrices.get(u+1), m_posible);
                if(matriz_igual){
                    tree_matrizIgual = t_g2;
                }
            }
            changeJugador();
            tree_guardado = tree_matrizIgual;
        }  
        return tree;
    }
    
    public int changeJugador(){
        if (jugador==1){
            jugador = 2;
        }
        else if(jugador == 2){
            jugador = 1;
        }
        return jugador;
    }
//    public void setgameActual_info(){
//        if( state == 'P'){
//            gameActual = history_2players.get(id_actual);
//        }
//        else if (state == 'C'){
//            gameActual = history_computer.get(id_actual);
//        }
//    }
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
    public int starting_player(int[][] matriz){
        // solo sirve para la matriz donde haya una sola jugada
        for(int i = 0; i<3 ; i++){
            for (int j = 0 ; j<3 ; j++){
                int num = matriz[i][j];
                if(num == 1){
                    return 1;
                }
                else if(num == 2){
                    return 2;
                }
            }
        }
        return 0;
        
        
    }
    public GridPane newGridPane(int[][] matrizgame){
        GridPane juego = new GridPane();
        juego.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            juego.getColumnConstraints().add(new ColumnConstraints(45));
            juego.getRowConstraints().add(new RowConstraints(45));
        }
        juego.setPrefWidth(135);
        juego.setPrefHeight(135);
        updateGame(matrizgame,juego);
        return juego;
    }
    public void updateGame(int[][] matrizgame, GridPane game) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int num_matriz = matrizgame[x][y];
                Cuadro c1 = new Cuadro(x, y, false);
                if (num_matriz == 1) {
                    Image icon_x = new Image("Iconos_game/X_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(40);
                    imv1.setFitHeight(40);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                } else if (num_matriz == 2) {
                    Image icon_x = new Image("Iconos_game/O_sinfondo.png");
                    ImageView imv1 = new ImageView(icon_x);
                    imv1.setFitWidth(40);
                    imv1.setFitHeight(40);
                    imv1.setPreserveRatio(true);
                    c1.getChildren().add(imv1);
                }
                game.add(c1, y, x);
            }
        }
    }
   public int pj(int player, int[][] games) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            if (validar_neighbourRow(i, player, games)) {
                total++;
            }
            if (validar_neighbourCol(i, player, games)) {
                total++;
            }
        }
        if (validar_diagonal1(player, games)) {
            total++;
        }
        if (validar_diagonal2(player, games)) {
            total++;
        }
        int zeros = fila_col_dia_zeros(games);
        return total + zeros;
    }

    public int utilidadTablero(int px, int po, int jugador, int[][] games) {
        if (jugador == 1) {
            return pj(px, games) - pj(po, games);
        } else if (jugador == 2) {
            return pj(po, games) - pj(px, games);
        }
        return 0;
    }

    public boolean validar_neighbourRow(int fila, int jugador, int[][] games) {
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

    public boolean validar_neighbourCol(int col, int jugador, int[][] games) {
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

    public boolean validar_diagonal1(int jugador, int[][] games) {
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

    public boolean validar_diagonal2(int jugador, int[][] games) {
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

    public int fila_col_dia_zeros(int[][] games) {
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
        if (matrices1.size() > 1) {
            for (int[][] m1 : matrices1) {

                LinkedList<int[][]> matrices2 = posiblesEstados2(jugador, m1);
                LinkedList<Integer> utilidades2 = new LinkedList<>();
                Tree children = new Tree(m1);
                treeMatriz.addChildren(children);
                for (int[][] m2 : matrices2) {
                    Tree children2 = new Tree(m2);
                    int utilidad = utilidadTablero(1, 2, jugador, m2);
                    utilidades2.add(utilidad);
                    children.addChildren(children2);
   
                }

                int min_util = 0;
                if (matrices2.size() > 1) {
                    Heap<Integer> h2 = new Heap(cmp_i_maxheap, utilidades2,false);
                    min_util = h2.desencolar();
//                    min_util = (int) Collections.min(utilidades2);
                } else if (matrices2.size() == 1) {
                    min_util = utilidades2.get(0);
                }
//            System.out.println("Minimo-> "+ min_util);
                utilidades1.add(min_util);
            }
            Heap<Integer> h1 = new Heap(cmp_i_maxheap, utilidades1,true);
            int max_util = h1.desencolar();
//            int max_util = (int) Collections.max(utilidades1);
            int index = utilidades1.indexOf(max_util);
            matriz = matrices1.get(index);
        } else if (matrices1.size() == 1) {
            matriz = matrices1.get(0);
        }
  
        return matriz;
    }
    Comparator<Integer> cmp_i_maxheap = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }            
    };

    @FXML
    private void regresar(MouseEvent event) throws IOException {
       User.passUser(us1);
       HistoryToReview h1 = new HistoryToReview(id_actual,state);
       HistoryToReview.passHistoryToReview(h1);
       FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
       Parent root = loader.load();
       HistoryController historyController = loader.getController();
       Scene scene = new Scene(root);
       Stage stage = (Stage) vbox_tree.getScene().getWindow(); 
       stage.setScene(scene);
    }
    
}
