/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.contacts;

import java.io.Serializable;

/**
 *
 * @author HOME
 */
public enum TipoRelacion implements Serializable {
    familiar, amistad, colega, asistente, sitio_laboral, asociacion, ninguno;
    
    public static TipoRelacion parse(String text){
        if(familiar.toString().compareTo(text)==0){
            return familiar;
        } else if(amistad.toString().compareTo(text)==0){
            return amistad;
        } else if(colega.toString().compareTo(text)==0){
            return colega;
        } else if(asistente.toString().compareTo(text)==0){
            return asistente;
        } else if(sitio_laboral.toString().compareTo(text)==0){
            return sitio_laboral;
        }   else if(asociacion.toString().compareTo(text)==0){
            return asociacion;
        } else{
            return ninguno;
        }
    }
}
