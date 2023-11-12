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
<<<<<<< HEAD
public class Person extends Contact{
    
    public Person(String name, String lastname, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<Contact> relatedContacts) {
        super(name,lastname, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
    }
     //Para prueba 
    public Person (String name,String lastname){
        super(name,lastname,null, null, null, null, null, null,null);
    }
=======
public class Person extends Contact {

    private String lastName;
    private ArrayList<Contact> contacts;

    public Person(String name, String LastName, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.lastName = LastName;
    }
    
    public Person(ArrayList<Contact> contacts,String name, String LastName, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.lastName = LastName;
        this.contacts=contacts;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
   
>>>>>>> c2d80c9ba5f8010fcaa21ae547842ef879a42fa9
}
