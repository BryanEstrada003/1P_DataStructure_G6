/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Clases;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author angelozurita
 */
public class Cuadro extends StackPane{
    private int xpos;
    private int ypos;
    private boolean ocupado;

    public Cuadro(int xpos, int ypos, boolean ocupado) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.ocupado = ocupado;
        
        this.setStyle("-fx-background-color: #36454F; -fx-border-color: white; -fx-border-width: 2;");
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }


    
    
    
}
