/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author angelozurita
 */
public class Games implements Serializable{
    private User us1 ;
    private String name_player;
    private int winner;
    private int[][] matriz_game;
    private ArrayList<int[][]> tableros;

    public Games(User us1, String name_player, int[][] matriz_game, int winner) {
        this.us1 = us1;
        this.name_player = name_player;
        this.matriz_game = matriz_game;
        this.winner = winner;
        this.tableros = new ArrayList<int[][]>();
    }
    public Games(User us1, String name_player, int[][] matriz_game, int winner, ArrayList<int[][]> tableros) {
        this.us1 = us1;
        this.name_player = name_player;
        this.matriz_game = matriz_game;
        this.winner = winner;
        this.tableros = tableros;
    }
    public User getUs1() {
        return us1;
    }

    public void setUs1(User us1) {
        this.us1 = us1;
    }

    public String getName_player() {
        return name_player;
    }

    public void setName_player(String name_player) {
        this.name_player = name_player;
    }

    public int[][] getMatriz_game() {
        return matriz_game;
    }

    public void setMatriz_game(int[][] matriz_game) {
        this.matriz_game = matriz_game;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public ArrayList<int[][]> getTableros() {
        return tableros;
    }

    public void setTableros(ArrayList<int[][]> tableros) {
        this.tableros = tableros;
    }
    
    
        public static ArrayList<Games> readListFromFileSer(String nombre) 
    {
        ArrayList<Games> games = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre)))
        {
            games = (ArrayList<Games>)in.readObject();
        }
        catch(IOException ioe){  
        }
        catch(ClassNotFoundException c ){        
        }
        return games;
    }
    
    public static void saveListToFileSer (String nombre, ArrayList<Games> usuarios)
    {
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(nombre)))
        {
            out.writeObject(usuarios);
        }
        catch(IOException ioe){
        }
    }
    
    public static ArrayList<Games>  add_Gametolist (Games game,ArrayList<Games> games)
    {
        games.add(game);
        return games;
    }
    
    public static void  add_game_file(Games game,String namefile){
        if(game != null){
            ArrayList<Games> games =  readListFromFileSer (namefile);
            games.add(game);
            Games.saveListToFileSer (namefile,games);        
        }

    }
    
    
}
