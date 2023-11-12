/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.time.LocalDate;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author HOME
 */
public class Company extends Contact{
    
    public Company(){
        super(null, null, null, null, null, null, null, null, null);
    }
    public Company(String name,String lastname, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<Contact> relatedContacts) {
        super(name,lastname, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
    }
    //Para prueba 
    public Company (String name,String lastname){
        super(name,lastname,null, null, null, null, null, null, null);
    }
    

}
