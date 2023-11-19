/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class RelatedContact implements Serializable  {
    private String contactType;
    private Contact contact;
    private static final long serialVersionUID = 274965647874716638L;
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
