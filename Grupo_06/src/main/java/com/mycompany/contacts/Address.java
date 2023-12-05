/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;
import javafx.scene.image.Image;


/**
 *
 * @author HOME
 */
public class Address implements Serializable {
    private String description;
    private String geographyUbication;
    private static final long serialVersionUID = 274965647874716638L;

    public Address(String description, String geographyUbication) {
        this.description = description;
        this.geographyUbication = geographyUbication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeographyUbication() {
        return geographyUbication;
    }

    public void setGeographyUbication(String geographyUbication) {
        this.geographyUbication = geographyUbication;
    }

    @Override
    public String toString() {
        return "Address{" + "description=" + description + ", geographyUbication=" + geographyUbication + '}';
    }
    
    
}
