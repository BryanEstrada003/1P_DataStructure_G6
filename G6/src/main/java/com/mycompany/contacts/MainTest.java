/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;
import ec.edu.espol.TDAs.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
/**
 *
 * @author HOME
 */
public class MainTest {
    public static void main(String args[]){
        System.out.println("hola mundo");
        //List<Date> datesInterest, List<RelatedContact> relatedContacts
        ArrayList<Telephone> telephoneNumbers = new ArrayList<>();
        telephoneNumbers.addLast(new Telephone("home", "2345678"));
        telephoneNumbers.addLast(new Telephone("cell", "0987629425"));
        DoublyLinkedList<Image> photos = new DoublyLinkedList<Image>();
        ArrayList<Email> emails = new ArrayList<Email>();
        emails.addLast(new Email("personal", "mestrada@gmail.com"));
        emails.addLast(new Email("trabajo", "mestrada@espol.edu.ec"));
        DoublyLinkedList<SocialMedia> socialsMedia = new DoublyLinkedList<SocialMedia>();
        socialsMedia.addLast(new SocialMedia("github","BryanEstrada003"));
        socialsMedia.addLast(new SocialMedia("instagram","mai_lavender003"));
        ArrayList<Date> datesInterest = new ArrayList<Date>();
        LocalDate fechaNac = LocalDate.parse("1/4/2003", DateTimeFormatter.ofPattern("d/M/yyyy") );
        datesInterest.addLast(new Date("cumpleaños", fechaNac));
        LocalDate otroDia = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy") );
        datesInterest.addLast(new Date("cumpleaños", otroDia));
        ArrayList<RelatedContact> relatedContacts = new ArrayList<RelatedContact>();
        Contact otro1 = new Person("Juan", "Castro", telephoneNumbers, photos, new Address("Home", "bloques de las acacias", "https://maps.app.goo.gl/umG9d4u3L7fTbhDu9"),emails, socialsMedia, datesInterest, relatedContacts);
        Contact otro2 = new Company("Espol", telephoneNumbers, photos, new Address("Home", "bloques de las acacias", "https://maps.app.goo.gl/umG9d4u3L7fTbhDu9"),emails, socialsMedia, datesInterest, relatedContacts);
        relatedContacts.addLast(new RelatedContact("Amigo", otro1));
        relatedContacts.addLast(new RelatedContact("Empresa", otro2));
        
        
        Contact yo = new Person("Michael", "Estrada", telephoneNumbers, photos, new Address("Home", "bloques de las acacias", "https://maps.app.goo.gl/umG9d4u3L7fTbhDu9"),emails, socialsMedia, datesInterest, relatedContacts);
        System.out.println("telephoneNumbers.toString() "+telephoneNumbers.toString());
//        System.out.println("socialsMedia.toString() "+ socialsMedia.toString());
        System.out.println("get head" + socialsMedia.getLast());
    }
}
