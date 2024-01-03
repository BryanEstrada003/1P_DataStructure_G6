/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.treeview;

/**
 *
 * @author angelozurita
 */
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Rectangle> nodes;
    private List<Line> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(Rectangle node) {
        nodes.add(node);
    }

    public void addEdge(Line edge) {
        edges.add(edge);
    }

    public List<Rectangle> getNodes() {
        return nodes;
    }

    public List<Line> getEdges() {
        return edges;
    }
}
