/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Clases;

import java.io.File;
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
public class Match implements Serializable{
    private Player p1;
    private Player p2;
    private Player winner;
    private User us;
    private ArrayList<TournamentGame> jugadas;
    private Round round;

    public Match(Player p1, Player p2, Player winner, User us, ArrayList<TournamentGame> jugadas,Round round) {
        this.p1 = p1;
        this.p2 = p2;
        this.winner = winner;
        this.us = us;
        this.jugadas = jugadas;
        this.round = round;
    }

    public Match(Player p1, Player p2,Round round, User u) {
        this.p1 = p1;
        this.p2 = p2;
        this.round = round;
        this.us = us;
        this.jugadas = new ArrayList<>();
    }

    public Match(Round round,User us) {
        this.round = round;
        this.us = us;
        this.jugadas = new ArrayList<>();
    }

    public Match() {
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

    public User getUs() {
        return us;
    }

    public void setUs(User us) {
        this.us = us;
    }

    public ArrayList<TournamentGame> getJugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<TournamentGame> jugadas) {
        this.jugadas = jugadas;
    }
    
    public boolean isPlayed(){
        return winner != null;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
    
        
    public static Match getPassMatch(){
        Match u1 = new Match();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Match_provi.ser")))
        {
            u1 = (Match)in.readObject();
            File archivo = new File("Match_provi.ser");
            if (archivo.exists()) {
                archivo.delete();
            }
        }
        catch(IOException ioe){  
        }
        catch(ClassNotFoundException c ){        
        }
        return u1;
    }
    public static void passMatch(Match us1){
         try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("Match_provi.ser")))
        {
            out.writeObject(us1);
        }
        catch(IOException ioe){
        }   
    }

    @Override
    public String toString() {
        return "Match{" + "p1=" + p1 + ", p2=" + p2 + ", winner=" + winner + ", us=" + us + ", jugadas=" + jugadas + ", round=" + round + '}';
    }
    
}
