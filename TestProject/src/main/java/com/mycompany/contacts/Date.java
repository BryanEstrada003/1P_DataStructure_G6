/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import java.time.*;

/**
 *
 * @author HOME
 */
public class Date {
    private String dateType;
    private LocalDate date;

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

    @Override
    public String toString() {
        return "Date{" + "dateType=" + dateType + ", date=" + date + '}';
    }
    
    
}
