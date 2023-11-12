/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import ec.edu.espol.TDAs.*;
import javafx.scene.image.Image;

/**
 *
 * @author Luis Romero
 */
public class Company extends Contact{
    private ArrayList<Contact> contacts;
    
<<<<<<< HEAD
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
    
=======
    public Company(String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
    }
    
    public Company(ArrayList<Contact> contact, String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.contacts = contact;
    }

    public ArrayList<Contact> getContact() {
        return contacts;
    }

    public void setContact(ArrayList<Contact> contact) {
        this.contacts = contact;
    }
>>>>>>> c2d80c9ba5f8010fcaa21ae547842ef879a42fa9

}
