/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contacts;

import ec.edu.espol.TDAs.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ec.edu.espol.TDAs.DoublyLinkedList;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author angelozurita
 */
public class Util implements Serializable {

    public static String title(String palabra) {
        String result = palabra.toUpperCase().charAt(0) + palabra.substring(1, palabra.length()).toLowerCase();
        return result;
    }

    public static ArrayList<Contact> listaContacto() {

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
        socialsMedia.addLast(new SocialMedia("github", "BryanEstrada003"));
        socialsMedia.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest = new ArrayList<>();
        LocalDate fechaNac = LocalDate.parse("1/4/2003", DateTimeFormatter.ofPattern("d/M/yyyy"));
        Date date = new Date("cumpleaños", fechaNac);
        datesInterest.add(date);
        LocalDate otroDia = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"));
        datesInterest.add(new Date("amistad", otroDia));

        ArrayList<RelatedContact> relatedContacts = new ArrayList<>();

//        System.out.println("telephoneNumbers.toString() " + telephoneNumbers.toString());
//        System.out.println("socialsMedia.toString() " + socialsMedia.toString());
//        System.out.println(socialsMedia);
        //List<RelatedContact> relatedContacts
        Contact user1 = new Person(
                "Michael",
                "Estrada",
                "file: url",
                telephoneNumbers,
                photos,
                new Address("home", "bloque acacias"),
                emails,
                socialsMedia,
                datesInterest,
                relatedContacts
        );

        ArrayList<Telephone> telephoneNumbers_2 = new ArrayList<>();
        telephoneNumbers_2.add(new Telephone("home", "32489023"));
        telephoneNumbers_2.add(new Telephone("cell", "0953343514"));

        DoublyLinkedList<String> photos_2 = new DoublyLinkedList<>();
        photos_2.addLast("file1: url");
        photos_2.addLast("file2: url");

        ArrayList<Email> emails_2 = new ArrayList<>();
        emails_2.add(new Email("personal", "raul@gmail.com"));
        emails_2.add(new Email("trabajo", "raul@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_2 = new DoublyLinkedList<>();
        socialsMedia_2.addLast(new SocialMedia("facebook", "Raul R"));
        socialsMedia_2.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest_2 = new ArrayList<>();
        LocalDate fechaNac_2 = LocalDate.parse("6/6/2004", DateTimeFormatter.ofPattern("d/M/yyyy"));
        Date date_2 = new Date("cumpleaños", fechaNac_2);
        datesInterest_2.add(date_2);
        LocalDate otroDia_2 = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"));
        datesInterest_2.add(new Date("amistad", otroDia_2));

        ArrayList<RelatedContact> relatedContacts_2 = new ArrayList<>();

        Contact user2 = new Person(
                "Pepe",
                "Ramirez",
                "file: url",
                telephoneNumbers_2,
                photos_2,
                new Address("home", "Florida Norte"),
                emails_2,
                socialsMedia_2,
                datesInterest_2,
                relatedContacts_2
        );

        ArrayList<Telephone> telephoneNumbers_3 = new ArrayList<>();
        telephoneNumbers_3.add(new Telephone("oficina", "32489023"));
        telephoneNumbers_3.add(new Telephone("ventas", "0953343514"));

        DoublyLinkedList<String> photos_3 = new DoublyLinkedList<>();
        photos_3.addLast("file1: url");
        photos_3.addLast("file2: url");

        ArrayList<Email> emails_3 = new ArrayList<>();
        emails_3.add(new Email("correo empresarial", "company@gmail.com"));
        emails_3.add(new Email("gerente general", "steven@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_3 = new DoublyLinkedList<>();
        socialsMedia_3.addLast(new SocialMedia("facebook", "Company"));
        socialsMedia_3.addLast(new SocialMedia("instagram", "company_sa"));

        ArrayList<Date> datesInterest_3 = new ArrayList<>();
        LocalDate fundacion = LocalDate.parse("9/9/2010", DateTimeFormatter.ofPattern("d/M/yyyy"));
        Date date_3 = new Date("Fundación", fundacion);
        datesInterest_3.add(date_3);

        ArrayList<RelatedContact> relatedContacts_3 = new ArrayList<>();

        Contact user3 = new Company(
                "Company S.A",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Sucursal", "Florida Norte"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        ArrayList<Telephone> telephoneNumbers_4 = new ArrayList<>();
        telephoneNumbers_4.add(new Telephone("home", "32489023"));
        telephoneNumbers_4.add(new Telephone("cell", "0953343514"));

        DoublyLinkedList<String> photos_4 = new DoublyLinkedList<>();
        photos_4.addLast("file1: url");
        photos_4.addLast("file2: url");

        ArrayList<Email> emails_4 = new ArrayList<>();
        emails_4.add(new Email("personal", "raul@gmail.com"));
        emails_4.add(new Email("trabajo", "raul@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_4 = new DoublyLinkedList<>();
        socialsMedia_4.addLast(new SocialMedia("facebook", "Raul R"));
        socialsMedia_4.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest_4 = new ArrayList<>();
        Date date_4 = new Date("cumpleaños", LocalDate.parse("6/6/2004", DateTimeFormatter.ofPattern("d/M/yyyy")));
        datesInterest_4.add(date_4);
        datesInterest_4.add(new Date("amistad", LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"))));

        ArrayList<RelatedContact> relatedContacts_4 = new ArrayList<>();
        relatedContacts_4.add(new RelatedContact(TipoRelacion.familiar.toString(), user1));
        relatedContacts_4.add(new RelatedContact(TipoRelacion.amistad.toString(), user2));

        Contact user4 = new Person(
                "Ana",
                "López",
                "file: url",
                telephoneNumbers_4,
                photos_4,
                new Address("Trabajo", "Av. Principal"),
                emails_4,
                socialsMedia_4,
                datesInterest_4,
                relatedContacts_4
        );

        ArrayList<Telephone> telephoneNumbers_5 = new ArrayList<>();
        telephoneNumbers_5.add(new Telephone("home", "32489023"));
        telephoneNumbers_5.add(new Telephone("cell", "0953343514"));

        DoublyLinkedList<String> photos_5 = new DoublyLinkedList<>();
        photos_5.addLast("file1: url");
        photos_5.addLast("file2: url");

        ArrayList<Email> emails_5 = new ArrayList<>();
        emails_5.add(new Email("personal", "raul@gmail.com"));
        emails_5.add(new Email("trabajo", "raul@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_5 = new DoublyLinkedList<>();
        socialsMedia_5.addLast(new SocialMedia("facebook", "Raul R"));
        socialsMedia_5.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest_5 = new ArrayList<>();
        datesInterest_5.add(new Date("cumpleaños", LocalDate.parse("6/6/2004", DateTimeFormatter.ofPattern("d/M/yyyy"))));
        datesInterest_5.add(new Date("amistad", LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"))));

        ArrayList<RelatedContact> relatedContacts_5 = new ArrayList<>();
        relatedContacts_5.add(new RelatedContact(TipoRelacion.amistad.toString(), user4));
        relatedContacts_5.add(new RelatedContact(TipoRelacion.amistad.toString(), user2));

        Contact user5 = new Person(
                "Pedro",
                "García",
                "file: url",
                telephoneNumbers_5,
                photos_5,
                new Address("Casa", "Calle Secundaria"),
                emails_5,
                socialsMedia_5,
                datesInterest_5,
                relatedContacts_5
        );

        Contact user6 = new Company(
                "Tech Solutions",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Oficina", "Tech Street"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        Contact user7 = new Company(
                "Fashion Trends",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Tienda", "Fashion Avenue"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        Contact user8 = new Person(
                "María",
                "Pérez",
                "file: url",
                telephoneNumbers_5,
                photos_5,
                new Address("Hogar", "María's House"),
                emails_3,
                socialsMedia_5,
                datesInterest_5,
                relatedContacts_5
        );

        Contact user9 = new Person(
                "Juan",
                "Martínez",
                "file: url",
                telephoneNumbers_5,
                photos_5,
                new Address("Trabajo", "Juan's Workplace"),
                emails_5,
                socialsMedia_5,
                datesInterest_5,
                relatedContacts_5
        );

        Contact user10 = new Company(
                "Food Delight",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Restaurante", "Food Street"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        relatedContacts.add(new RelatedContact(TipoRelacion.amistad.toString(), user2));
        relatedContacts.add(new RelatedContact(TipoRelacion.asociacion.toString(), user3));
        relatedContacts_2.add(new RelatedContact(TipoRelacion.amistad.toString(), user1));
        relatedContacts_2.add(new RelatedContact(TipoRelacion.asociacion.toString(), user3));
        relatedContacts_3.add(new RelatedContact(TipoRelacion.asistente.toString(), user1));
        relatedContacts_3.add(new RelatedContact(TipoRelacion.asistente.toString(), user2));

        DoublyLinkedList<Contact> contacts = new DoublyLinkedList<Contact>();
        contacts.addLast(user1);
        contacts.addLast(user2);
        contacts.addLast(user3);
        contacts.addLast(user4);
        contacts.addLast(user5);
        contacts.addLast(user6);
        contacts.addLast(user7);
        contacts.addLast(user8);
        contacts.addLast(user9);
        contacts.addLast(user10);

        ArrayList<Contact> listContactos = new ArrayList<>();
        for (int i = 0; i < contacts.size() - 1; i++) {
            listContactos.add(contacts.get(i));

        }
        
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Contactos.ser"))) {
            out.writeObject(listContactos);
        } catch (IOException ioe) {

        }
        return listContactos;

    }

    public static DoublyLinkedList<Contact> contactosSeleccionado(Contact c) {
        DoublyLinkedList<Contact> nuevaLisr = new DoublyLinkedList<>();
        nuevaLisr.add(c);
        return nuevaLisr;
    }

    public static void saveListToFile(String nameFile, ArrayList<RelatedContact> contacs) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nameFile))) {
            out.writeObject(contacs);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static ArrayList<Contact> readListFromFileSer(String nombre) {
        ArrayList<Contact> users = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre)))
        {
            users = (ArrayList<Contact>)in.readObject();
        }
        catch(IOException ioe)
        {
            
        }
        catch(ClassNotFoundException c )
        {
                
        }
        return users;
    }
    
    public static DoublyLinkedList<Contact> changetoDoublyLinkedList(ArrayList<Contact> al_contacts)  {
        DoublyLinkedList<Contact> contactos = new DoublyLinkedList();
         contactos.addAll(al_contacts);
        return contactos;
        
    }
    //Esto es de prueba
  public static DoublyLinkedList<Contact> listaContacto2() {

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
        socialsMedia.addLast(new SocialMedia("github", "BryanEstrada003"));
        socialsMedia.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest = new ArrayList<>();
        LocalDate fechaNac = LocalDate.parse("1/4/2003", DateTimeFormatter.ofPattern("d/M/yyyy"));
        Date date = new Date("cumpleaños", fechaNac);
        datesInterest.add(date);
        LocalDate otroDia = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"));
        datesInterest.add(new Date("amistad", otroDia));

        ArrayList<RelatedContact> relatedContacts = new ArrayList<>();

//        System.out.println("telephoneNumbers.toString() " + telephoneNumbers.toString());
//        System.out.println("socialsMedia.toString() " + socialsMedia.toString());
//        System.out.println(socialsMedia);
        //List<RelatedContact> relatedContacts
        Contact user1 = new Person(
                "Michael",
                "Estrada",
                "file: url",
                telephoneNumbers,
                photos,
                new Address("home", "bloque acacias"),
                emails,
                socialsMedia,
                datesInterest,
                relatedContacts
        );

        ArrayList<Telephone> telephoneNumbers_2 = new ArrayList<>();
        telephoneNumbers_2.add(new Telephone("home", "32489023"));
        telephoneNumbers_2.add(new Telephone("cell", "0953343514"));

        DoublyLinkedList<String> photos_2 = new DoublyLinkedList<>();
        photos_2.addLast("file1: url");
        photos_2.addLast("file2: url");

        ArrayList<Email> emails_2 = new ArrayList<>();
        emails_2.add(new Email("personal", "raul@gmail.com"));
        emails_2.add(new Email("trabajo", "raul@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_2 = new DoublyLinkedList<>();
        socialsMedia_2.addLast(new SocialMedia("facebook", "Raul R"));
        socialsMedia_2.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest_2 = new ArrayList<>();
        LocalDate fechaNac_2 = LocalDate.parse("6/6/2004", DateTimeFormatter.ofPattern("d/M/yyyy"));
        Date date_2 = new Date("cumpleaños", fechaNac_2);
        datesInterest_2.add(date_2);
        LocalDate otroDia_2 = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"));
        datesInterest_2.add(new Date("amistad", otroDia_2));

        ArrayList<RelatedContact> relatedContacts_2 = new ArrayList<>();

        Contact user2 = new Person(
                "Pepe",
                "Ramirez",
                "file: url",
                telephoneNumbers_2,
                photos_2,
                new Address("home", "Florida Norte"),
                emails_2,
                socialsMedia_2,
                datesInterest_2,
                relatedContacts_2
        );

        ArrayList<Telephone> telephoneNumbers_3 = new ArrayList<>();
        telephoneNumbers_3.add(new Telephone("oficina", "32489023"));
        telephoneNumbers_3.add(new Telephone("ventas", "0953343514"));

        DoublyLinkedList<String> photos_3 = new DoublyLinkedList<>();
        photos_3.addLast("file1: url");
        photos_3.addLast("file2: url");

        ArrayList<Email> emails_3 = new ArrayList<>();
        emails_3.add(new Email("correo empresarial", "company@gmail.com"));
        emails_3.add(new Email("gerente general", "steven@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_3 = new DoublyLinkedList<>();
        socialsMedia_3.addLast(new SocialMedia("facebook", "Company"));
        socialsMedia_3.addLast(new SocialMedia("instagram", "company_sa"));

        ArrayList<Date> datesInterest_3 = new ArrayList<>();
        LocalDate fundacion = LocalDate.parse("9/9/2010", DateTimeFormatter.ofPattern("d/M/yyyy"));
        Date date_3 = new Date("Fundación", fundacion);
        datesInterest_3.add(date_3);

        ArrayList<RelatedContact> relatedContacts_3 = new ArrayList<>();

        Contact user3 = new Company(
                "Company S.A",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Sucursal", "Florida Norte"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        ArrayList<Telephone> telephoneNumbers_4 = new ArrayList<>();
        telephoneNumbers_4.add(new Telephone("home", "32489023"));
        telephoneNumbers_4.add(new Telephone("cell", "0953343514"));

        DoublyLinkedList<String> photos_4 = new DoublyLinkedList<>();
        photos_4.addLast("file1: url");
        photos_4.addLast("file2: url");

        ArrayList<Email> emails_4 = new ArrayList<>();
        emails_4.add(new Email("personal", "raul@gmail.com"));
        emails_4.add(new Email("trabajo", "raul@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_4 = new DoublyLinkedList<>();
        socialsMedia_4.addLast(new SocialMedia("facebook", "Raul R"));
        socialsMedia_4.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest_4 = new ArrayList<>();
        Date date_4 = new Date("cumpleaños", LocalDate.parse("6/6/2004", DateTimeFormatter.ofPattern("d/M/yyyy")));
        datesInterest_4.add(date_4);
        datesInterest_4.add(new Date("amistad", LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"))));

        ArrayList<RelatedContact> relatedContacts_4 = new ArrayList<>();
        relatedContacts_4.add(new RelatedContact(TipoRelacion.amistad.toString(), user1));
        relatedContacts_4.add(new RelatedContact(TipoRelacion.amistad.toString(), user2));

        Contact user4 = new Person(
                "Ana",
                "López",
                "file: url",
                telephoneNumbers_4,
                photos_4,
                new Address("Trabajo", "Av. Principal"),
                emails_4,
                socialsMedia_4,
                datesInterest_4,
                relatedContacts_4
        );

        ArrayList<Telephone> telephoneNumbers_5 = new ArrayList<>();
        telephoneNumbers_5.add(new Telephone("home", "32489023"));
        telephoneNumbers_5.add(new Telephone("cell", "0953343514"));

        DoublyLinkedList<String> photos_5 = new DoublyLinkedList<>();
        photos_5.addLast("file1: url");
        photos_5.addLast("file2: url");

        ArrayList<Email> emails_5 = new ArrayList<>();
        emails_5.add(new Email("personal", "raul@gmail.com"));
        emails_5.add(new Email("trabajo", "raul@espol.edu.ec"));

        DoublyLinkedList<SocialMedia> socialsMedia_5 = new DoublyLinkedList<>();
        socialsMedia_5.addLast(new SocialMedia("facebook", "Raul R"));
        socialsMedia_5.addLast(new SocialMedia("instagram", "mai_lavender003"));

        ArrayList<Date> datesInterest_5 = new ArrayList<>();
        datesInterest_5.add(new Date("cumpleaños", LocalDate.parse("6/6/2004", DateTimeFormatter.ofPattern("d/M/yyyy"))));
        datesInterest_5.add(new Date("amistad", LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy"))));

        ArrayList<RelatedContact> relatedContacts_5 = new ArrayList<>();
        relatedContacts_5.add(new RelatedContact(TipoRelacion.amistad.toString(), user4));
        relatedContacts_5.add(new RelatedContact(TipoRelacion.amistad.toString(), user2));

        Contact user5 = new Person(
                "Pedro",
                "García",
                "file: url",
                telephoneNumbers_5,
                photos_5,
                new Address("Casa", "Calle Secundaria"),
                emails_5,
                socialsMedia_5,
                datesInterest_5,
                relatedContacts_5
        );

        Contact user6 = new Company(
                "Tech Solutions",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Oficina", "Tech Street"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        Contact user7 = new Company(
                "Fashion Trends",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Tienda", "Fashion Avenue"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        Contact user8 = new Person(
                "María",
                "Pérez",
                "file: url",
                telephoneNumbers_5,
                photos_5,
                new Address("Hogar", "María's House"),
                emails_3,
                socialsMedia_5,
                datesInterest_5,
                relatedContacts_5
        );

        Contact user9 = new Person(
                "Juan",
                "Martínez",
                "file: url",
                telephoneNumbers_5,
                photos_5,
                new Address("Trabajo", "Juan's Workplace"),
                emails_5,
                socialsMedia_5,
                datesInterest_5,
                relatedContacts_5
        );

        Contact user10 = new Company(
                "Food Delight",
                "file: url",
                telephoneNumbers_3,
                photos_3,
                new Address("Restaurante", "Food Street"),
                emails_3,
                socialsMedia_3,
                datesInterest_3,
                relatedContacts_3
        );

        relatedContacts.add(new RelatedContact(TipoRelacion.amistad.toString(), user2));
        relatedContacts.add(new RelatedContact(TipoRelacion.asociacion.toString(), user3));
        relatedContacts_2.add(new RelatedContact(TipoRelacion.amistad.toString(), user1));
        relatedContacts_2.add(new RelatedContact(TipoRelacion.asociacion.toString(), user3));
        relatedContacts_3.add(new RelatedContact(TipoRelacion.asistente.toString(), user1));
        relatedContacts_3.add(new RelatedContact(TipoRelacion.asistente.toString(), user2));

        DoublyLinkedList<Contact> contacts = new DoublyLinkedList<>();
        contacts.addLast(user1);
        contacts.addLast(user2);
        contacts.addLast(user3);
        contacts.addLast(user4);
        contacts.addLast(user5);
        contacts.addLast(user6);
        contacts.addLast(user7);
        contacts.addLast(user8);
        contacts.addLast(user9);
        contacts.addLast(user10);

        return contacts;

    }
}
