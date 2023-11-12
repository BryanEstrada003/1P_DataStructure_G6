/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;

/**
 *
 * @author angelozurita
 */
public class Util implements Serializable{
    
    public static String title(String palabra){
        String result = palabra.toUpperCase().charAt(0) + palabra.substring(1, palabra.length()).toLowerCase();
        return result;
    }
    
    
}
