/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Clases;

import ec.edu.espol.TDAs.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;

/**
 *
 * @author angelozurita
 */
public class User implements Serializable {
    private int id_user;
    private String Nickname;
    private String password;
    private String user;
    private Result results_players_2;
    private Result results_computer;
    private ArrayList<Games> history;
    private static final long serialVersionUID = 274965647874716638L;
    
    public User(){
        
    }
    public User(int id_user, String Nickname, String user, String password) {
        this.id_user = id_user;
        this.Nickname = Nickname;
        this.password = password;
        this.user = user;
        this.results_computer = new Result(Type_game.COMPUTER,0,0,0);
        this.results_players_2 = new Result(Type_game.PLAYERS_2,0,0,0);
        this.history = new ArrayList<Games>();
    }
    
    public User(int id_user, String user, String password) {
        this.id_user = id_user;
        this.password = password;
        this.user = user;
        this.results_computer = new Result(Type_game.COMPUTER,0,0,0);
        this.results_players_2 = new Result(Type_game.PLAYERS_2,0,0,0);
        this.history = new ArrayList<Games>();
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        this.Nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Result getResults_players_2() {
        return results_players_2;
    }

    public void setResults_players_2(Result results_players_2) {
        this.results_players_2 = results_players_2;
    }

    public Result getResults_computer() {
        return results_computer;
    }

    public void setResults_computer(Result results_computer) {
        this.results_computer = results_computer;
    }

    public int  getVictories_players_2(){
        return this.results_players_2.getVictories();
    }
    public int  getDraws_players_2(){
        return this.results_players_2.getDraw();
    }
    public int  getDefeats_players_2(){
        return this.results_players_2.getDefeat();
    }
    
    public void  setVictories_players_2(int newvalue){
        this.results_players_2.setVictories(newvalue);
    }
    public void  setDraws_players_2(int newvalue){
        this.results_players_2.setDraw(newvalue);
    }
    public void  setDefeats_players_2(int newvalue){
        this.results_players_2.setDefeat(newvalue);
    }

    public ArrayList<Games> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Games> history) {
        this.history = history;
    }
    
    
            
    public static ArrayList<User> readListFromFileSer(String nombre) 
    {
        ArrayList<User> usuarios = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre)))
        {
            usuarios = (ArrayList<User>)in.readObject();
        }
        catch(IOException ioe){  
        }
        catch(ClassNotFoundException c ){        
        }
        return usuarios;
    }
    
    public static void saveListToFileSer (String nombre, ArrayList<User> usuarios)
    {
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(nombre)))
        {
            out.writeObject(usuarios);
        }
        catch(IOException ioe){
        }
    }
    
    public static ArrayList<User>  add_Usertolist (User user,ArrayList<User> usuarios)
    {
        usuarios.add(user);
        return usuarios;
    }
    
    public static void  add_user_file(User user,String namefile){
        if(user != null){
            ArrayList<User> usuarios =  readListFromFileSer (namefile);
            usuarios.add(user);
            User.saveListToFileSer (namefile,usuarios);        
        }

    }
    
    public static boolean checkUserFile(String user){
        // Retorna true si todo esta correcto y puedo ser utilizado el user, eso quiere decir que no este repetido
        if(user != null){
            ArrayList<User> users = readListFromFileSer("User.ser");
            for(User u1 :users){
                if(u1.getUser().compareTo(user) == 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean checkLogin(String user, String pswd){
        
        if( user != null  && pswd != null){
            ArrayList<User> users = readListFromFileSer("User.ser");
            for(User u1 :users){
                if( (u1.getUser().compareTo(user) == 0 ) && (u1.getPassword().compareTo(pswd) == 0 ) ){
                    return true;
                }
            }
        }
        return false;
    }
    public static User getUser(String user, String pswd){
        User u = new User();
        if( user != null  && pswd != null){
            ArrayList<User> users = readListFromFileSer("User.ser");
            for(User u1 :users){
                if( (u1.getUser().compareTo(user) == 0 ) && (u1.getPassword().compareTo(pswd) == 0 ) ){
                    u = u1;
                    break;
                }
            }
        }
        return u;
    }
    
    public static int nextId(){
        File user_ser = new File("User.ser");
        if(! user_ser.exists()){
            ArrayList<User> usuarios = new ArrayList<>();
            saveListToFileSer ("User.ser", usuarios);
        }
        ArrayList<User> users = readListFromFileSer("User.ser");
        int id_Actual = users.size();
        return id_Actual++;
    }
    
    
    public static User getPassUser(){
        User u1 = new User();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("User_provisional.ser")))
        {
            u1 = (User)in.readObject();
            File archivo = new File("User_provisional.ser");
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
    
    public static void passUser(User us1){
         try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("User_provisional.ser")))
        {
            out.writeObject(us1);
        }
        catch(IOException ioe){
        }   
    }

    public static void updateUser(User u){
        ArrayList<User> users = readListFromFileSer("User.ser");
        ArrayList<User> new_users = new ArrayList<User>();
            for(User u1 :users){
                if(u.getId_user() == u1.getId_user()){
                    new_users.add(u);
                }
                else{
                   new_users.add(u1); 
                }
            }
        User.saveListToFileSer ("User.ser",new_users);
    }
    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", Nickname=" + Nickname + ", password=" + password + ", user=" + user + ", results_players_2=" + results_players_2 + ", results_computer=" + results_computer + ", history=" + history + '}';
    }
    
    
}
