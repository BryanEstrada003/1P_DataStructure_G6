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

public class Person extends Contact implements Serializable  {
    
    private String lastName;
    private ArrayList<Contact> contacts;
   private static final long serialVersionUID = 274965647874716638L;
   
    public Person(String name, String LastName, String profilePhoto,List<Telephone> telephoneNumbers, List<String> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        super(name, profilePhoto,telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.lastName = LastName;
    }
    
    public Person(ArrayList<Contact> contacts,String name, String LastName,String profilePhoto, List<Telephone> telephoneNumbers, List<String> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        super(name, profilePhoto,telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
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

    @Override
    public String toString() {
        return "Person{" + "lastName=" + lastName + ", contacts=" + contacts + '}';
    }
}
