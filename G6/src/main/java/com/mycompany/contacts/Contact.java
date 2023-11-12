/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import ec.edu.espol.TDAs.*;
import javafx.scene.image.Image;

/**
 *
 * @author HOME
 */
public abstract class Contact {
    private String name;
    private List<Telephone> telephoneNumbers;
    private List<Image> photos;
    private Address address;
    private List<Email> emails;
    private List<SocialMedia> socialsMedia;
    private List<Date> datesInterest;
    private List<RelatedContact> relatedContacts;

    public Contact(String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        this.name = name;
        this.telephoneNumbers = telephoneNumbers;
        this.photos = photos;
        this.address = address;
        this.emails = emails;
        this.socialsMedia = socialsMedia;
        this.datesInterest = datesInterest;
        this.relatedContacts = relatedContacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Telephone> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(List<Telephone> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public List<Image> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Image> photos) {
        this.photos = photos;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<SocialMedia> getSocialsMedia() {
        return socialsMedia;
    }

    public void setSocialsMedia(List<SocialMedia> socialsMedia) {
        this.socialsMedia = socialsMedia;
    }

    public List<Date> getDatesInterest() {
        return datesInterest;
    }

    public void setDatesInterest(List<Date> datesInterest) {
        this.datesInterest = datesInterest;
    }

    public List<RelatedContact> getRelatedContacts() {
        return relatedContacts;
    }

    public void setRelatedContacts(List<RelatedContact> relatedContacts) {
        this.relatedContacts = relatedContacts;
    }
    
    
    
    
    
}
