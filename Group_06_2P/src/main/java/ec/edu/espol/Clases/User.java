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
    private Result results_easy;
    private Result results_difi;
    private static final long serialVersionUID = 274965647874716638L;
    
    public User(){
        
    }
    public User(int id_user, String Nickname, String user, String password) {
        this.id_user = id_user;
        this.Nickname = Nickname;
        this.password = password;
        this.user = user;
        this.results_difi = new Result(Type_game.DIFFICULT,0,0,0);
        this.results_easy = new Result(Type_game.EASY,0,0,0);
    }
    
    public User(int id_user, String user, String password) {
        this.id_user = id_user;
        this.password = password;
        this.user = user;
        this.results_difi = new Result(Type_game.DIFFICULT,0,0,0);
        this.results_easy = new Result(Type_game.EASY,0,0,0);
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

    public Result getResults_easy() {
        return results_easy;
    }

    public void setResults_easy(Result results_easy) {
        this.results_easy = results_easy;
    }

    public Result getResults_difi() {
        return results_difi;
    }

    public void setResults_difi(Result results_difi) {
        this.results_difi = results_difi;
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
    
    
    
}
