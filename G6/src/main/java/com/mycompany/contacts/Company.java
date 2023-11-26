/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import ec.edu.espol.TDAs.*;
import java.io.Serializable;
import javafx.scene.image.Image;

/**
 *
 * @author Luis Romero
 */
public class Company extends Contact implements Serializable {
    private static final long serialVersionUID = 274965647874716638L;
    
    public Company(String Id_re,String name, String profilePhoto, List<Telephone> telephoneNumbers, List<String> photos, List<Address> address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        super(Id_re,name, profilePhoto,telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
    }

    

}
