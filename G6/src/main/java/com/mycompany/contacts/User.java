/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import java.nio.file.FileSystems;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author angelozurita
 */
public class User implements Serializable {
    
    private String name  ; 
    private String lastname ;
    private String personal_user;
    private String pasword ;  // creo que debemos poner transient para no serializarlo;
    private String personal_path;
    private static final long serialVersionUID = 274965647874716638L;

    public User(String name, String lastname, String personal_user, String pasword) {
        this.name = name;
        this.lastname = lastname;
        this.personal_user = personal_user;
        this.pasword = pasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPersonal_user() {
        return personal_user;
    }

    public void setPersonal_user(String personal_user) {
        this.personal_user = personal_user;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getPersonal_path() {
        return personal_path;
    }

    public void setPersonal_path(String personal_path) {
        this.personal_path = personal_path;
    }

    
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", lastname=" + lastname + ", personal_user=" + personal_user + ", pasword=" + pasword + ", personal_path=" + personal_path + '}';
    }
    
//    public void createFolder(){
//        personal_path = "/src/main/resources/Profile_pictures/"+personal_user.trim() ; 
//        Path newfolder = Paths.get(personal_path);
//        try {
//            Files.createDirectories(newfolder);
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.WARNING,"FOLDER NOT CREATED ");
//            alert.setTitle("FOLDER NOT CREATED");
//            alert.show();
//        }
//    }
    public void createFolder() {
        String baseDir = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        personal_path = baseDir + "/src/main/resources/Profile_pictures/" + personal_user.trim();
        
        Path newfolder = Paths.get(personal_path);
        try {
            Files.createDirectories(newfolder);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "FOLDER NOT CREATED ");
            alert.setTitle("FOLDER NOT CREATED");
            alert.setHeaderText("WARNING");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    public void moveImageToPersonalPath() {
        createFolder();
        String baseDir = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        Path actualPath = Paths.get(baseDir + "/src/main/resources/Users/p_user.png");
        Path targetPath = Paths.get(baseDir + "/src/main/resources/Profile_pictures/" + personal_user.trim() + "/" + personal_user.trim() + ".png");
        
        try {
            Files.move(actualPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING, "ISSUES TO MOVE PERSONAL FOLDER");
            alert.setTitle("MOVE FOLDER");
            alert.setHeaderText("WARNING");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    public static ArrayList<User> readListFromFileSer(String nombre) {
        ArrayList<User> users = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre)))
        {
            users = (ArrayList<User>)in.readObject();
        }
        catch(IOException ioe)
        {
            
        }
        catch(ClassNotFoundException c )
        {
                
        }
        return users;
    }
    
    public static void saveListToFileSer (String nombre, ArrayList<User> usuarios){
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(nombre)))
        {
            out.writeObject(usuarios);
        }
        catch(IOException ioe)
        {
            
        }
    }
    
    public static ArrayList<User>  add_Usertolist (User userp,ArrayList<User> usuarios) {
        usuarios.add(userp);
        return usuarios;
    }
    
    public static void crearArchivoser(){
        ArrayList<User> users = new ArrayList();
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("Users.ser")))
        {
            out.writeObject(users);
        }
        catch(IOException ioe)
        {
            
        }        
    }
    
    public static void add_UsertoUser_Ser (User userp) {
        ArrayList<User> users = readListFromFileSer("Users.ser");
        users.add(userp);
        saveListToFileSer("Users.ser",users);
    }
    
    public static ArrayList<String> getPersonalUsers ( ){
        
        ArrayList<String> p_users = new ArrayList();
        ArrayList<User> users = readListFromFileSer("Users.ser");
        for (User us : users){
            p_users.add(us.personal_user);
        }
      
        return p_users;   
    }
    
    public static User validarLogin (String personal_user, String password){
        ArrayList<User> users = readListFromFileSer("Users.ser");
        for (User us: users){
            String personal = us.personal_user;
            String psw = us.pasword;    
            if (personal_user.equals(personal) && password.equals(psw) ){
                return us;
            }      
        }
        return null;
    }
    

}
    
    
    
    
 
