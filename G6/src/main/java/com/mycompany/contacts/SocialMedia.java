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
class SocialMedia implements Serializable  {
    private String socialMedia;
    private String user;
    private static final long serialVersionUID = 274965647874716638L;
    
    public SocialMedia(String socialMedia, String user) {
        this.socialMedia = socialMedia;
        this.user = user;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SocialMedia{" + "socialMedia=" + socialMedia + ", user=" + user + '}';
    }
    
}
