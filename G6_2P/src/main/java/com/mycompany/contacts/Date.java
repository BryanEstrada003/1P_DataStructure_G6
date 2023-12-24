/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;
import java.time.*;

/**
 *
 * @author HOME
 */
public class Date implements Serializable {

    private String dateType;
    private LocalDate date;
    private static final long serialVersionUID = 274965647874716638L;

    public Date(String dateType, LocalDate date) {
        this.dateType = dateType;
        this.date = date;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDateAsString() {
        return date.toString();
    }

    @Override
    public String toString() {
        return "Date{" + "dateType=" + dateType + ", date=" + date + '}';
    }

}
