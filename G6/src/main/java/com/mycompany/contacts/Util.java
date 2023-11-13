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
import java.util.ArrayList;

/**
 *
 * @author angelozurita
 */
public class Util<E> implements Serializable{
    
    public static String title(String palabra){
        String result = palabra.toUpperCase().charAt(0) + palabra.substring(1, palabra.length()).toLowerCase();
        return result;
    }
    
    public ArrayList<E> readListFromFileSer(String nombre) {
        ArrayList<E> users = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre)))
        {
            users = (ArrayList<E>)in.readObject();
        }
        catch(IOException ioe)
        {
            
        }
        catch(ClassNotFoundException c )
        {
                
        }
        return users;
    }
    
    public void saveListToFileSer (String nombre, ArrayList<E> usuarios){
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(nombre)))
        {
            out.writeObject(usuarios);
        }
        catch(IOException ioe)
        {
            
        }
    }
    
    public ArrayList<E>  add_Usertolist (E userp,ArrayList<E> usuarios) {
        usuarios.add(userp);
        return usuarios;
    }
    
    public void crearArchivoser(String name){
        ArrayList<E> users = new ArrayList();
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(name+".ser")))
        {
            out.writeObject(users);
        }
        catch(IOException ioe)
        {
            
        }        
    }
    
    public void add_Userto (E userp,String name) {
        ArrayList<E> users = readListFromFileSer(name+".ser");
        users.add(userp);
        saveListToFileSer("Users.ser",users);
    }
    
}
