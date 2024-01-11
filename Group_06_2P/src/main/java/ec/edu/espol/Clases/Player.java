/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author angelozurita
 */
public class Player implements Serializable {
    private String name;
    private boolean fase; // Descalificado false o Activo true
    private ArrayList<Match> l_matches;
    private User us1;  // creador del torneo
    private boolean isUser;// para saber si es el usuario
    private Result result_torneo;
    private int winner_matches;
    private int default_matches;

    public Player(String name, boolean fase, ArrayList<Match> l_matches, User us1,Boolean isUser) {
        this.name = name;
        this.fase = fase;
        this.l_matches = l_matches;
        this.us1 = us1;
        this.isUser = isUser;
        this.result_torneo= new Result();
    }

    public Player(String name, boolean fase, ArrayList<Match> l_matches, User us1) {
        this.name = name;
        this.fase = fase;
        this.l_matches = l_matches;
        this.us1 = us1;
        this.result_torneo= new Result();
    }

    public Player(String name, boolean fase, User us1) {
        this.name = name;
        this.fase = fase;
        this.us1 = us1;
        this.result_torneo= new Result();
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFase() {
        return fase;
    }

    public void setFase(boolean fase) {
        this.fase = fase;
    }

    public ArrayList<Match> getL_matches() {
        return l_matches;
    }

    public void setL_matches(ArrayList<Match> l_matches) {
        this.l_matches = l_matches;
    }

    public User getUs1() {
        return us1;
    }

    public void setUs1(User us1) {
        this.us1 = us1;
    }

    public boolean isIsUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }
    
    public Result getresults_computer() {
        return result_torneo;
    }

    public void setresults_computer(Result result_torneo) {
        this.result_torneo = result_torneo;
    }
    
    public int getVictories(){
        return this.result_torneo.getVictories();
    }
    
    public int getDraws(){
        return this.result_torneo.getDraw();
    }
    
    public int getDefeats(){
        return this.result_torneo.getDefeat();
    }
    
    public void setVictories(int newvalue){
        this.result_torneo.setVictories(newvalue);
    }
    
    public void setDraws(int newvalue){
        this.result_torneo.setDraw(newvalue);
    }
    
    public void setDefeats(int newvalue){
        this.result_torneo.setDefeat(newvalue);
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", fase=" + fase + ", l_matches=" + l_matches + ", us1=" + us1 + ", isUser=" + isUser + ", result_torneo=" + result_torneo + ", winner_matches=" + winner_matches + ", default_matches=" + default_matches + '}';
    }
    
}
