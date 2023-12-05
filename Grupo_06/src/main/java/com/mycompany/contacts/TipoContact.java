/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;

/**
 *
 * @author Jonanyu 11.1
 */
public enum TipoContact implements Serializable  {
    person,company;
    
    public static TipoContact parse(String text){
        if(person.toString().compareTo(text)==0){
            return person;
        } else{
            return company;
        }
    }
}
