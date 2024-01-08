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
public class Tournament implements Serializable {
    private ArrayList<Player> players;
    private Player winner;
    private TypeTournament tipo;
    private boolean state;
    private ArrayList<Match> matches ; 
    private User us;

    public Tournament(ArrayList<Player> players, Player winner, TypeTournament tipo, boolean state, ArrayList<Match> matches, User us) {
        this.players = players;
        this.winner = winner;
        this.tipo = tipo;
        this.state = state;
        this.matches = matches;
        this.us = us;
    }

    
    public Tournament(ArrayList<Player> players, Player winner, TypeTournament tipo, boolean state, ArrayList<Match> matches) {
        this.players = players;
        this.winner = winner;
        this.tipo = tipo;
        this.state = state;
        this.matches = matches;
    }

    public Tournament(ArrayList<Player> players, TypeTournament tipo, boolean state, User us) {
        this.players = players;
        this.tipo = tipo;
        this.state = state;
        this.matches =  new ArrayList<Match>();
        this.us = us;
    }

    public Tournament(User us){
        this.us = us; 
    }

    public Tournament() {
    }
    

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public TypeTournament getTipo() {
        return tipo;
    }

    public void setTipo(TypeTournament tipo) {
        this.tipo = tipo;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public User getUs() {
        return us;
    }

    public void setUs(User us) {
        this.us = us;
    }
    
    
    public static Tournament getPassTournament(){
        Tournament u1 = new Tournament();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Tournament_provi.ser")))
        {
            u1 = (Tournament)in.readObject();
            File archivo = new File("Tournament_provi.ser");
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
    public static void passTournament(Tournament us1){
         try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("Tournament_provi.ser")))
        {
            out.writeObject(us1);
        }
        catch(IOException ioe){
        }   
    }

    @Override
    public String toString() {
        return "Tournament{" + "players=" + players + ", winner=" + winner + ", tipo=" + tipo + ", state=" + state + ", matches=" + matches + ", us=" + us + '}';
    }
    
    
    
    
    
}
