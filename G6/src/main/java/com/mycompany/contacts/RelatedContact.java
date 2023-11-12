/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

/**
 *
 * @author HOME
 */
public class RelatedContact {
    private String contactType;
    private Contact contact;

    public RelatedContact(String contactType, Contact contact) {
        this.contactType = contactType;
        this.contact = contact;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "tipo=" + contactType + ", contacto=" + contact + '}';
    }
    
    
    
}
