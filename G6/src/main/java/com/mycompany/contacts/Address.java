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
class Address implements Serializable {
    private String addressType;
    private String description;
    private String geographyUbication;
    private static final long serialVersionUID = 274965647874716638L;

    public Address(String addressType, String description, String geographyUbication) {
        this.addressType = addressType;
        this.description = description;
        this.geographyUbication = geographyUbication;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
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
