/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

/**
 *
 * @author HOME
 */
public class Telephone {
    private String typeNumber;
    private String number;

    public Telephone(String typeNumber, String number) {
        this.typeNumber = typeNumber;
        this.number = number;
    }

    public String getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(String typeNumber) {
        this.typeNumber = typeNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Telephone{" + "typeNumber=" + typeNumber + ", number=" + number + '}';
    }
    
    
    
}
