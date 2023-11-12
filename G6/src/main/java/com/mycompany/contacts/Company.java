/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import ec.edu.espol.TDAs.ArrayList;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Luis Romero
 */
public class Company extends Contact{
    private ArrayList<Contact> contacts;
    
    public Company(String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<Contact> relatedContacts) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
    }
    
    public Company(ArrayList<Contact> contact, String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<Contact> relatedContacts) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.contacts = contact;
    }

    public ArrayList<Contact> getContact() {
        return contacts;
    }

    public void setContact(ArrayList<Contact> contact) {
        this.contacts = contact;
    }

}
