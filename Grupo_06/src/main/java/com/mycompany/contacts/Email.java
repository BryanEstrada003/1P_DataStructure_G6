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
public class Email implements Serializable {
    private String emailType;
    private String email;
    private static final long serialVersionUID = 274965647874716638L;
    
    public Email(String emailType, String email) {
        this.emailType = emailType;
        this.email = email;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email{" + "emailType=" + emailType + ", email=" + email + '}';
    }
    
    
    
}
