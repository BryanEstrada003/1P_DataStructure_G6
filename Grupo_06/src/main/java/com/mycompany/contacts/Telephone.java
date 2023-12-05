/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class Telephone implements Serializable  {
    private String numberType;
    private String telephoneNumber;
    private static final long serialVersionUID = 274965647874716638L;
    
    public Telephone(String numberType, String telephoneNumber) {
        this.numberType = numberType;
        this.telephoneNumber = telephoneNumber;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Telephone{" + "numberType=" + numberType + ", telephoneNumber=" + telephoneNumber + '}';
    }    
}
