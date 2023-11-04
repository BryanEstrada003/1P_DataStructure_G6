/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.util.*;
import java.time.*;
import javafx.scene.image.Image;

/**
 *
 * @author HOME
 */
public abstract class Contact {
    private String name;
    private List<String> telephoneNumbers;
    private List<Image> photos;
    private Address address;
    private List<String> emails;
    private List<SocialMedia> socialsMedia;
    private List<LocalDate> datesInterest;
    private List<Contact> relatedContacts;
    
    
}
