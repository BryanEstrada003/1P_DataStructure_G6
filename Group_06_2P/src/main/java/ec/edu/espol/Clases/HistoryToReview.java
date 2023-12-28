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

/**
 *
 * @author angelozurita
 */
public class HistoryToReview implements Serializable{
    private int id_actual;
    private char state;

    public HistoryToReview(int id_actual, char state) {
        this.id_actual = id_actual;
        this.state = state;
    }
    public HistoryToReview() {
        this.state = 'N';
    }
    
    public int getId_actual() {
        return id_actual;
    }

    public void setId_actual(int id_actual) {
        this.id_actual = id_actual;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }
    
    public static void passHistoryToReview (HistoryToReview h1){
         try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("InfoHistoryToReview.ser")))
        {
            out.writeObject(h1);
        }
        catch(IOException ioe){
        }   
    }
    
    public static HistoryToReview getPassHistoryToReview(){
        HistoryToReview u1 = new HistoryToReview();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("InfoHistoryToReview.ser")))
        {
            u1 = (HistoryToReview)in.readObject();
            File archivo = new File("InfoHistoryToReview.ser");
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
}
