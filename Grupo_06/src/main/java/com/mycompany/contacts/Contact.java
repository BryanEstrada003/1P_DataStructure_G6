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
 * @author HOME
 */
public abstract class Contact implements Serializable  {
    private String ID_re;
    private String name;
    private String lastname;
    private String profilePhoto;
    private List<Telephone> telephoneNumbers;
    private List<String> photos;
    private List<Address> address;
    private List<Email> emails;
    private List<SocialMedia> socialsMedia;
    private List<Date> datesInterest;
    private List<RelatedContact> relatedContacts;
    private static final long serialVersionUID = 274965647874716638L;

    public Contact(String ID_re, String name, String profilePhoto, List<Telephone> telephoneNumbers, List<String> photos, List<Address> address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        this.ID_re = ID_re;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.telephoneNumbers = telephoneNumbers;
        this.photos = photos;
        this.address = address;
        this.emails = emails;
        this.socialsMedia = socialsMedia;
        this.datesInterest = datesInterest;
        this.relatedContacts = relatedContacts;
    }
    public Contact(String ID_re, String name, String lastname,String profilePhoto, List<Telephone> telephoneNumbers, List<String> photos, List<Address> address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<RelatedContact> relatedContacts) {
        this.ID_re = ID_re;
        this.name = name;
        this.lastname = lastname;
        this.profilePhoto = profilePhoto;
        this.telephoneNumbers = telephoneNumbers;
        this.photos = photos;
        this.address = address;
        this.emails = emails;
        this.socialsMedia = socialsMedia;
        this.datesInterest = datesInterest;
        this.relatedContacts = relatedContacts;
    }
    public String getID_re() {
        return ID_re;
    }

    public void setID_re(String ID_re) {
        this.ID_re = ID_re;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    
    public List<Telephone> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(List<Telephone> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
