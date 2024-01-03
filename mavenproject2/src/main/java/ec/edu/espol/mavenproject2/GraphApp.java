/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.mavenproject2;

/**
 *
 * @author angelozurita
 */
import ec.edu.espol.treeview.Graph;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GraphApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Graph graph = new Graph();
        graph.addNode(new Rectangle(10, 10, 50, 50));
        graph.addNode(new Rectangle(70, 10, 50, 50));
        graph.addEdge(new Line(25, 25, 65, 25));

        root.getChildren().addAll(graph.getNodes());
        root.getChildren().addAll(graph.getEdges());

        Scene scene = new Scene(root, 300, 200, Color.WHITE);

        primaryStage.setTitle("JavaFX Graph Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}