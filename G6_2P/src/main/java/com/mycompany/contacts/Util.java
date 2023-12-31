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
import ec.edu.espol.TDAs.List;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author angelozurita
 */
public class Util<E> implements Serializable {

    public static String title(String palabra) {
        String result = palabra.toUpperCase().charAt(0) + palabra.substring(1, palabra.length()).toLowerCase();
        return result;
    }

    public static DoublyLinkedList<Contact> contactosSeleccionado(Contact c) {
        DoublyLinkedList<Contact> nuevaLisr = new DoublyLinkedList<>();
        nuevaLisr.add(c);
        return nuevaLisr;
    }

    public static <E> void saveListToFile(String nameFile, DoublyLinkedList<E> contacs) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nameFile))) {
            out.writeObject(contacs);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static <E> DoublyLinkedList<E> readListFromFileSer(String nombre) {
        DoublyLinkedList<E> users = new DoublyLinkedList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre))) {
            users = (DoublyLinkedList<E>) in.readObject();
        } catch (IOException ioe) {

        } catch (ClassNotFoundException c) {

        }
        return users;
    }

    public static DoublyLinkedList<Contact> changetoDoublyLinkedList(ArrayList<Contact> al_contacts) {
        DoublyLinkedList<Contact> contactos = new DoublyLinkedList();
        contactos.addAll(al_contacts);
        return contactos;

    }

    public static DoublyLinkedList<Contact> listaContacto2() {
        Util.defecto();
        DoublyLinkedList<Contact> contacts = Util.<Contact>readListFromFileSer("Contactos.ser");
        return contacts;
    }

    public static void defecto() {
        File f = new File("Contactos.ser");
        if (!f.exists()) {
            ArrayList<Address> direcciones = new ArrayList<>();
            direcciones.add(new Address("home", "bloque acacias"));
            direcciones.add(new Address("work", "9 de octubre"));
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
                    "A0001",
                    "Michael",
                    "Estrada",
                    "file: url",
                    telephoneNumbers,
                    photos,
                    direcciones,
                    emails,
                    socialsMedia,
                    datesInterest,
                    relatedContacts
            );

            ArrayList<Telephone> telephoneNumbers_2 = new ArrayList<>();
            telephoneNumbers_2.add(new Telephone("home", "32489023"));
            telephoneNumbers_2.add(new Telephone("cell", "0953343514"));
            telephoneNumbers_2.add(new Telephone("fsdfdsf", "09533435ffff14"));
            telephoneNumbers_2.add(new Telephone("cfdsfd", "0953343514"));

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
                    "A0002",
                    "Pepe",
                    "Ramirez",
                    "file: url",
                    telephoneNumbers_2,
                    photos_2,
                    direcciones,
                    emails_2,
                    socialsMedia_2,
                    datesInterest_2,
                    relatedContacts_2
            );

            ArrayList<Telephone> telephoneNumbers_3 = new ArrayList<>();
            telephoneNumbers_3.add(new Telephone("oficina", "32489023"));
            telephoneNumbers_3.add(new Telephone("ventas", "0953343514"));
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
                    "A0003",
                    "Company S.A",
                    "file: url",
                    telephoneNumbers_3,
                    photos_3,
                    direcciones,
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
                    "A0004",
                    "Ana",
                    "López",
                    "file: url",
                    telephoneNumbers_4,
                    photos_4,
                    direcciones,
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
                    "A0005",
                    "Pedro",
                    "García",
                    "file: url",
                    telephoneNumbers_5,
                    photos_5,
                    direcciones,
                    emails_5,
                    socialsMedia_5,
                    datesInterest_5,
                    relatedContacts_5
            );

            Contact user6 = new Company(
                    "A0006",
                    "Tech Solutions",
                    "file: url",
                    telephoneNumbers_3,
                    photos_3,
                    direcciones,
                    emails_3,
                    socialsMedia_3,
                    datesInterest_3,
                    relatedContacts_3
            );

            Contact user7 = new Company(
                    "A0007",
                    "Fashion Trends",
                    "file: url",
                    telephoneNumbers_3,
                    photos_3,
                    direcciones,
                    emails_3,
                    socialsMedia_3,
                    datesInterest_3,
                    relatedContacts_3
            );

            Contact user8 = new Person(
                    "A0008",
                    "María",
                    "Pérez",
                    "file: url",
                    telephoneNumbers_5,
                    photos_5,
                    direcciones,
                    emails_3,
                    socialsMedia_5,
                    datesInterest_5,
                    relatedContacts_5
            );

            Contact user9 = new Person(
                    "A0009",
                    "Juan",
                    "Martínez",
                    "file: url",
                    telephoneNumbers_5,
                    photos_5,
                    direcciones,
                    emails_5,
                    socialsMedia_5,
                    datesInterest_5,
                    relatedContacts_5
            );

            Contact user10 = new Company(
                    "A0010",
                    "Food Delight",
                    "file: url",
                    telephoneNumbers_3,
                    photos_3,
                    direcciones,
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

            DoublyLinkedList<Contact> listContactos = new DoublyLinkedList<>();
            for (int i = 0; i < contacts.size() - 1; i++) {
                listContactos.add(contacts.get(i));

            }

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Contactos.ser"))) {
                out.writeObject(listContactos);
            } catch (IOException ioe) {

            }
        }

    }

    public static void addContact(Contact c) {
        Util.defecto();
        DoublyLinkedList<Contact> contacts = Util.<Contact>readListFromFileSer("Contactos.ser");
        contacts.add(c);
        Util.<Contact>saveListToFile("Contactos.ser", contacts);
    }

    public static DoublyLinkedList<Contact> orderForNameAndType(DoublyLinkedList<Contact> list) {
        if (list.isEmpty()) {
            return null;
        }
        DoublyLinkedList<Contact> persons = new DoublyLinkedList<>();
        DoublyLinkedList<Contact> companies = new DoublyLinkedList<>();
        for (Contact c : list) {
            if (c instanceof Person) {
                persons.add(c);
            } else {
                companies.add(c);
            }
        }
        DoublyLinkedList<Contact> orderForNameAndType = new DoublyLinkedList<>();
        PriorityQueue<Contact> orderNamePerson = new PriorityQueue<>((p1, p2) -> {
            int value = p1.getName().compareTo(p2.getName());
            if (value == 0) {
                value = ((Person) p1).getLastname().compareTo(((Person) p2).getLastname());
            }
            return value;
        });
        for (Contact p : persons) {
            orderNamePerson.offer(p);
        }

        PriorityQueue<Contact> orderNameCompany = new PriorityQueue<>((p1, p2) -> p1.getName().compareTo(p2.getName()));
        for (Contact c : companies) {
            orderNameCompany.offer(c);
        }

        while (!orderNamePerson.isEmpty()) {
            orderForNameAndType.add(orderNamePerson.poll());
        }
        while (!orderNameCompany.isEmpty()) {
            orderForNameAndType.add(orderNameCompany.poll());
        }
        return orderForNameAndType;
    }

    public static DoublyLinkedList<Contact> orderForName(DoublyLinkedList<Contact> list) {
        if (list.isEmpty()) {
            return null;
        }

        DoublyLinkedList<Contact> orderForName = new DoublyLinkedList<>();
        PriorityQueue<Contact> order = new PriorityQueue<>((c1, c2) -> {
            int value = c1.getName().compareTo(c2.getName());
            if (value == 0 && c1.getLastname() != null && c2.getLastname() != null) {
                value = c1.getLastname().compareTo(c2.getLastname());
            }
            return value;
        });

        for (Contact c : list) {
            order.offer(c);
        }

        while (!order.isEmpty()) {
            orderForName.add(order.poll());
        }

        return orderForName;
    }

    public static DoublyLinkedList<Contact> orderForTelephoneSize(DoublyLinkedList<Contact> list) {
        DoublyLinkedList<Contact> orderForTelephoneSize = new DoublyLinkedList<>();

        Comparator<Contact> phoneCountComparator = Comparator.comparingInt(c -> c.getTelephoneNumbers().size());

        PriorityQueue<Contact> order = new PriorityQueue<>(phoneCountComparator);

        for (Contact c : list) {
            order.offer(c);
        }

        while (!order.isEmpty()) {
            orderForTelephoneSize.add(order.poll());
        }

        return orderForTelephoneSize;
    }

    public static DoublyLinkedList<Contact> filterByTypeContact(DoublyLinkedList<Contact> list, TipoContact type) {
        if (list.isEmpty()) {
            return null;
        }
        if (type == TipoContact.person) {
            DoublyLinkedList<Contact> persons = new DoublyLinkedList<>();
            for (Contact c : list) {
                if (c instanceof Person) {
                    persons.add(c);
                }
            }
            return persons;
        } else {
            DoublyLinkedList<Contact> companies = new DoublyLinkedList<>();
            for (Contact c : list) {
                if (c instanceof Company) {
                    companies.add(c);
                }
            }
            return companies;
        }
    }

    public static DoublyLinkedList<Contact> filterIfEmails(DoublyLinkedList<Contact> list) {
        if (list.isEmpty()) {
            return null;
        }
        DoublyLinkedList<Contact> ifEmails = new DoublyLinkedList<>();
        for (Contact c : list) {
            if (!c.getEmails().isEmpty()) {
                ifEmails.add(c);
            }
        }
        return ifEmails;
    }

    public static DoublyLinkedList<Contact> filterIfSocialMedia(DoublyLinkedList<Contact> list) {
        if (list.isEmpty()) {
            return null;
        }
        DoublyLinkedList<Contact> ifEmails = new DoublyLinkedList<>();
        for (Contact c : list) {
            if (!c.getSocialsMedia().isEmpty()) {
                ifEmails.add(c);
            }
        }

        return null;
    }

    public static DoublyLinkedList<String> converUrlDoublyLinked(List<String> listPhoto) {
        DoublyLinkedList<String> phothos = new DoublyLinkedList<>();
        try {

            for (String ph : listPhoto) {
                phothos.add(convertirUrl(ph));
            }

        } catch (Exception E) {
            System.out.println("List Null");
        }
        return phothos;
    }

    public static String convertirUrl(String url) {
        String[] token = url.split("\\\\");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < token.length; i++) {
            if (token[i].compareTo("Contacts") == 0) {
                sb.append(token[i]).append("/");
                sb.append(token[i + 1]).append("/");
                sb.append(token[i + 2]);
            }
        }
        return sb.toString();
    }

    public static String identificarContact(Contact co) {
        if (co == null) {
            return "";
        }
        if (co instanceof Company) {
            return co.getName();
        }
        return co.getName() + " " + co.getLastname();
    }

    public static void serializeDoublyLinkedList(DoublyLinkedList<Contact> listContact, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(listContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DoublyLinkedList<Contact> deserializeDoublyLinkedList(String filename) {
        DoublyLinkedList<Contact> listContact = new DoublyLinkedList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            listContact = (DoublyLinkedList<Contact>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listContact;
    }
    
    public static void DeleteContactfromFile(Contact e){
        DoublyLinkedList<Contact> contactos = Util.listaContacto2();
        Comparator<Contact> cmpID = (Contact c1, Contact c2) -> c1.getID_re().compareTo(c2.getID_re());
        for(Contact co : contactos){
            if(co.getID_re().compareTo(e.getID_re())== 0){
                contactos.remove(e, cmpID);
            }
        }
        serializeDoublyLinkedList(contactos,"Contactos.ser");
    }
}
