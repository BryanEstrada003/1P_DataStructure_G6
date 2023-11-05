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
public class Person extends Contact{
    
    public Person(String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<String> emails, List<SocialMedia> socialsMedia, List<LocalDate> datesInterest, List<Contact> relatedContacts) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
    }
    
}
