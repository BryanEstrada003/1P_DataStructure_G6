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
        telephoneNumbers.add(new Telephone("home", "2345678"));
        telephoneNumbers.add(new Telephone("cell", "0987629425"));
        
        DoublyLinkedList<String> photos = new DoublyLinkedList<>();
        photos.addLast("file1: url");
        photos.addLast("file2: url");
        
        ArrayList<Email> emails = new ArrayList<>();
        emails.add(new Email("personal", "mestrada@gmail.com"));
        emails.add(new Email("trabajo", "mestrada@espol.edu.ec"));
        
        DoublyLinkedList<SocialMedia> socialsMedia = new DoublyLinkedList<>();
        socialsMedia.addLast(new SocialMedia("github","BryanEstrada003"));
        socialsMedia.addLast(new SocialMedia("instagram","mai_lavender003"));
        
        ArrayList<Date> datesInterest = new ArrayList<>();
        LocalDate fechaNac = LocalDate.parse("1/4/2003", DateTimeFormatter.ofPattern("d/M/yyyy") );
        Date date=new Date("cumpleaños", fechaNac);
        datesInterest.add(date);
        LocalDate otroDia = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy") );
        datesInterest.add(new Date("amistad", otroDia));
        
        ArrayList<RelatedContact> relatedContacts = new ArrayList<>();
        
        
        
        
        System.out.println("telephoneNumbers.toString() "+telephoneNumbers.toString());
        System.out.println("socialsMedia.toString() "+ socialsMedia.toString());
        System.out.println( socialsMedia);
        //List<RelatedContact> relatedContacts
        Contact user1 = new Person(
                "Michael",
                "Estrada",
                "file: url",
                telephoneNumbers,
                photos,
                new Address("home", "bloque acacias", "https://maps.app.goo.gl/8RyubBEH9WpFHQyv8"),
                emails,
                socialsMedia,
                datesInterest,
                relatedContacts
        );
        
        
        ArrayList<Telephone> telephoneNumbers2 = new ArrayList<>();
        telephoneNumbers.add(new Telephone("home", "32489023"));
        telephoneNumbers.add(new Telephone("cell", "0953343514"));
        
        DoublyLinkedList<String> photos2 = new DoublyLinkedList<>();
        photos.addLast("file1: url");
        photos.addLast("file2: url");
        
        ArrayList<Email> emails2 = new ArrayList<>();
        emails.add(new Email("personal", "raul@gmail.com"));
        emails.add(new Email("trabajo", "raul@espol.edu.ec"));
        
        DoublyLinkedList<SocialMedia> socialsMedia2 = new DoublyLinkedList<>();
        socialsMedia.addLast(new SocialMedia("facebook","Raul R"));
        socialsMedia.addLast(new SocialMedia("instagram","mai_lavender003"));
        
        ArrayList<Date> datesInterest2 = new ArrayList<>();
        LocalDate fechaNac2 = LocalDate.parse("6/6/2004", DateTimeFormatter.ofPattern("d/M/yyyy") );
        Date date2=new Date("cumpleaños", fechaNac2);
        datesInterest.add(date2);
        LocalDate otroDia2 = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy") );
        datesInterest2.add(new Date("amistad", otroDia2));
        
        ArrayList<RelatedContact> relatedContacts2 = new ArrayList<>();
        
        Contact user2 = new Person(
                "Pepe",
                "Ramirez",
                "file: url",
                telephoneNumbers2,
                photos2,
                new Address("home", "Florida Norte", "https://maps.app.goo.gl/GRMPAsvCxAosm3YA9"),
                emails2,
                socialsMedia2,
                datesInterest2,
                relatedContacts2
        );

        ArrayList<Telephone> telephoneNumbers3 = new ArrayList<>();
        telephoneNumbers.add(new Telephone("oficina", "32489023"));
        telephoneNumbers.add(new Telephone("ventas", "0953343514"));
        
        DoublyLinkedList<String> photos3 = new DoublyLinkedList<>();
        photos.addLast("file1: url");
        photos.addLast("file2: url");
        
        ArrayList<Email> emails3 = new ArrayList<>();
        emails.add(new Email("correo empresarial", "company@gmail.com"));
        emails.add(new Email("gerente general", "steven@espol.edu.ec"));
        
        DoublyLinkedList<SocialMedia> socialsMedia3 = new DoublyLinkedList<>();
        socialsMedia.addLast(new SocialMedia("facebook","Company"));
        socialsMedia.addLast(new SocialMedia("instagram","company_sa"));
        
        ArrayList<Date> datesInterest3 = new ArrayList<>();
        LocalDate fundacion = LocalDate.parse("9/9/2010", DateTimeFormatter.ofPattern("d/M/yyyy") );
        Date date3=new Date("Fundación", fundacion);
        datesInterest3.add(date3);
        
        ArrayList<RelatedContact> relatedContacts3 = new ArrayList<>();
        
        
        
        Contact user3 = new Company(
                "Company S.A",
                "file: url",
                telephoneNumbers3,
                photos3,
                new Address("Sucursal", "Florida Norte", "https://maps.app.goo.gl/GRMPAsvCxAosm3YA9"),
                emails3,
                socialsMedia3,
                datesInterest3,
                relatedContacts3
        );
        
        relatedContacts.add(new RelatedContact("Amigo", user2));
        relatedContacts.add(new RelatedContact("Empresa", user3));
        relatedContacts2.add(new RelatedContact("Amigo", user1));
        relatedContacts2.add(new RelatedContact("Empresa", user3));
        relatedContacts3.add(new RelatedContact("Asistente de ventas", user1));
        relatedContacts3.add(new RelatedContact("Asistente de finanzas", user2));
        
        DoublyLinkedList<Contact> contacts = new DoublyLinkedList<Contact>();
        contacts.addLast(user1);
        contacts.addLast(user2);
        contacts.addLast(user3);
        
    }
}
