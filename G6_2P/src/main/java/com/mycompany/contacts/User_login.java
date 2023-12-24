/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;

/**
 *
 * @author angelozurita
 */
public class User_login implements Serializable {
    
    private String p_user;
    private String psw ;
    private static final long serialVersionUID = 274965647874716638L;
    
    public User_login(String p_user, String psw) {
        this.p_user = p_user;
        this.psw = psw;
    }

    public String getP_user() {
        return p_user;
    }

    public void setP_user(String p_user) {
        this.p_user = p_user;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
    
    
    
}
