/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.treeview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class PruebaController implements Initializable {

    @FXML
    private Group root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Graph graph = new Graph();
        graph.addNode(new Rectangle(0, 10, 50, 50));
        graph.addNode(new Rectangle(70, 40, 50, 50));
        graph.addEdge(new Line(25, 25, 75, 25));

        root.getChildren().addAll(graph.getNodes());
        root.getChildren().addAll(graph.getEdges());
    }    
    
}
