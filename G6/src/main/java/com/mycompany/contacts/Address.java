/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import javafx.scene.image.Image;


/**
 *
 * @author HOME
 */
class Address {
    private String description;
    private Image geographyUbication;

    public Address(String description, Image geographyUbication) {
        this.description = description;
        this.geographyUbication = geographyUbication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getGeographyUbication() {
        return geographyUbication;
    }

    public void setGeographyUbication(Image geographyUbication) {
        this.geographyUbication = geographyUbication;
    }

    @Override
    public String toString() {
        return "Address{" + "description=" + description + ", geographyUbication=" + geographyUbication + '}';
    }
    
    
}
