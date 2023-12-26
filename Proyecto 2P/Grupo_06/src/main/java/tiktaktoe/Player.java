/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiktaktoe;

/**
 *
 * @author Michael Estrada
 */
public class Player {
    private String name;
    private String plType; // X, O
    private String whoPlayer; //quien juega: humano o maquina

    public Player(String name, String plType, String whoPlayer) {
        this.name = name;
        this.plType = plType;
        this.whoPlayer = whoPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlType() {
        return plType;
    }

    public void setPlType(String plType) {
        this.plType = plType;
    }

    public String getWhoPlayer() {
        return whoPlayer;
    }

    public void setWhoPlayer(String whoPlayer) {
        this.whoPlayer = whoPlayer;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", plType=" + plType + ", whoPlayer=" + whoPlayer + '}';
    }
    
    
    
    

    
}
