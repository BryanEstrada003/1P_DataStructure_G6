/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Luis Romero
 */
public class Person extends Contact {

    private String lastName;
    private Company company;
    private ArrayList<Person> contactPerson;

    public Person(String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<Contact> relatedContacts, String LastName) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.lastName = LastName;
    }

    public Person(String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<Contact> relatedContacts, String LastName, Company company) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.lastName = LastName;
        this.company = company;
    }

    public Person(String name, List<Telephone> telephoneNumbers, List<Image> photos, Address address, List<Email> emails, List<SocialMedia> socialsMedia, List<Date> datesInterest, List<Contact> relatedContacts, String LastName, Company company, ArrayList<Person> contactPerson) {
        super(name, telephoneNumbers, photos, address, emails, socialsMedia, datesInterest, relatedContacts);
        this.lastName = LastName;
        this.company = company;
        this.contactPerson = contactPerson;
    }

    public ArrayList<Person> getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ArrayList<Person> contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
