/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author angelozurita
 */
public class TournamentGame implements Serializable {
    private Player p1;
    private Player p2;
    private Player winner;
    private int[][] matriz;
    private ArrayList<int[][]> tableros;

    public TournamentGame(Player p1, Player p2, Player winner, int[][] matriz, ArrayList<int[][]> tableros) {
        this.p1 = p1;
        this.p2 = p2;
        this.winner = winner;
        this.matriz = matriz;
        this.tableros = tableros;
    }
    

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public ArrayList<int[][]> getTableros() {
        return tableros;
    }

    public void setTableros(ArrayList<int[][]> tableros) {
        this.tableros = tableros;
    }
    
    
}
