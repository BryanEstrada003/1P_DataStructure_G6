/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.treeview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class NewController implements Initializable {

    @FXML
    private AnchorPane game;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TreeItem<String> rootNode = new TreeItem<>("Root Node");

        TreeItem<String> childNode1 = new TreeItem<>("Child Node 1");
        TreeItem<String> childNode2 = new TreeItem<>("Child Node 2");
        rootNode.getChildren().addAll(childNode1, childNode2);

        TreeItem<String> grandChildNode1 = new TreeItem<>("Grandchild Node 1");
        childNode1.getChildren().add(grandChildNode1);

        rootNode.setExpanded(true);

        TreeView<String> treeView = new TreeView<>(rootNode);
        treeView.setShowRoot(true);

        VBox layout = new VBox(treeView);
        game.getChildren().add(layout);
    }    
   
}
