/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.contacts.Address;
import com.mycompany.contacts.Contact;
import com.mycompany.contacts.Date;
import com.mycompany.contacts.Email;
import com.mycompany.contacts.RelatedContact;
import com.mycompany.contacts.SocialMedia;
import com.mycompany.contacts.Telephone;
import com.mycompany.contacts.Util;
import ec.edu.espol.TDAs.DoublyLinkedList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jonanyu
 */
public class ShowContactController implements Initializable, Serializable {

    @FXML
    private ImageView btnPrevious;
    @FXML
    private ImageView btnNext;
    @FXML
    private ImageView imageProfile;
    @FXML
    private ImageView regresarScene;
    @FXML
    private Label nameContact;

    private Contact co;
    @FXML
    private ScrollPane infoContact;
    @FXML
    private VBox ifocon;
    @FXML
    private VBox headContact;
    @FXML
    private BorderPane generalPane;
    private DoublyLinkedList<Contact> contactos;
    private ListIterator<Contact> itera;
    private int nvezBo = 0;
    private boolean prebutn = false;
    private boolean nextbutn = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        headContact.getStyleClass().add("hbox-background");
        btnPrevious.setImage(new Image("Iconos/anterior.png"));
        btnNext.setImage(new Image("Iconos/proximo.png"));
        imageProfile.setImage(new Image("Iconos/cambiar_foto.png"));
        headContact.setAlignment(Pos.CENTER);
        regresarScene.setImage(new Image("Iconos/flecha-izquierda.png"));
        ifocon.getStyleClass().add("hbox-backgroundInfoCon");
        generalPane.getStyleClass().add("hbox-backgroundInfoCon");
        this.contactos = Util.deserializeDoublyLinkedList("ContactsOrderFilter");
        this.itera = this.contactos.listIterator();
    }

    public void setContact(Contact c) {
        this.co = c;
        updateUI();
    }

    private void updateUI() {
        ifocon.getChildren().clear();
        if (co != null) {
            try {
                imageProfile.setImage(new Image(Util.convertirUrl(co.getProfilePhoto())));
            } catch (IllegalArgumentException E) {
                imageProfile.setImage(new Image("Iconos/cambiar_foto.png"));
                System.out.println("No found ImageProfile URL");
            }
            headContact.setAlignment(Pos.CENTER);
            nameContact.setText(Util.identificarContact(co));
            nameContact.getStyleClass().add("text-title2");
            VBox telephones = new VBox();
            HBox titletelf = new HBox();
            ImageView imgTel = new ImageView(new Image("Iconos/phono.png"));
            imgTel.setFitWidth(30);
            imgTel.setFitHeight(30);
            imgTel.setPreserveRatio(true);
            Text tituloTelf = new Text(" Telefonos");
            titletelf.getChildren().addAll(imgTel, tituloTelf);
            telephones.getChildren().add(titletelf);
            if (co.getTelephoneNumbers() != null) {
                for (Telephone te : co.getTelephoneNumbers()) {
                    HBox infoNum = new HBox();
                    Text numberType = new Text(te.getNumberType() + ": ");
                    numberType.getStyleClass().add("text-normal");
                    Text number = new Text(te.getTelephoneNumber());
                    number.getStyleClass().add("text-normal");
                    infoNum.getChildren().addAll(numberType, number);
                    infoNum.getStyleClass().add("hbox-style");
                    telephones.getChildren().add(infoNum);
                }
            }

            VBox Date = new VBox();
            HBox titleImgDate = new HBox();
            ImageView imgDate = new ImageView(new Image("Iconos/dateIcon.png"));
            imgDate.setFitWidth(30);
            imgDate.setFitHeight(30);
            imgDate.setPreserveRatio(true);
            Text tituloDate = new Text(" Dates Interest");
            titleImgDate.getChildren().addAll(imgDate, tituloDate);
            Date.getChildren().add(titleImgDate);
            if (co.getDatesInterest() != null) {
                for (Date dt : co.getDatesInterest()) {
                    HBox infoDate = new HBox();
                    Text nameDate = new Text(dt.getDateType() + ": ");
                    nameDate.getStyleClass().add("text-normal");
                    Text date = new Text(dt.getDateAsString());
                    date.getStyleClass().add("text-normal");
                    infoDate.getChildren().addAll(nameDate, date);
                    infoDate.getStyleClass().add("hbox-style");
                    Date.getChildren().add(infoDate);
                }
            }

            VBox Email = new VBox();
            HBox titleImage = new HBox();
            ImageView imgV = new ImageView(new Image("Iconos/email.png"));
            imgV.setFitWidth(30);
            imgV.setFitHeight(30);
            imgV.setPreserveRatio(true);
            Text tituloEmail = new Text(" Emails");
            titleImage.getChildren().addAll(imgV, tituloEmail);
            Email.getChildren().add(titleImage);
            if (co.getEmails() != null) {
                for (Email em : co.getEmails()) {
                    HBox infoEmail = new HBox();
                    Text typeemail = new Text(em.getEmailType() + ": ");
                    typeemail.getStyleClass().add("text-normal");
                    Text email = new Text(em.getEmail());
                    email.getStyleClass().add("text-normal");
                    infoEmail.getChildren().addAll(typeemail, email);
                    infoEmail.getStyleClass().add("hbox-style");
                    Email.getChildren().add(infoEmail);
                }
            }

            VBox direccion = new VBox();
            HBox titleDireccion = new HBox();
            ImageView imgV2 = new ImageView(new Image("Iconos/address.png"));
            imgV2.setFitWidth(30);
            imgV2.setFitHeight(30);
            imgV2.setPreserveRatio(true);
            Text titiloAdress = new Text(" Adress");
            titleDireccion.getChildren().addAll(imgV2, titiloAdress);
            direccion.getChildren().add(titleDireccion);
            if (co.getAddress() != null) {
                for (Address adre : co.getAddress()) {
                    HBox infoAdress = new HBox();
                    Text desAdress = new Text(adre.getDescription() + ": ");
                    desAdress.getStyleClass().add("text-normal");
                    Text geAdrre = new Text(adre.getGeographyUbication());
                    geAdrre.getStyleClass().add("text-normal");
                    infoAdress.getChildren().addAll(desAdress, geAdrre);
                    infoAdress.getStyleClass().add("hbox-style");
                    direccion.getChildren().add(infoAdress);
                }
            }

            VBox mediaSocial = new VBox();
            HBox titleImgSocial = new HBox();
            ImageView imgsoci = new ImageView(new Image("Iconos/redes-sociales.png"));
            imgsoci.setFitWidth(35);
            imgsoci.setFitHeight(35);
            imgsoci.setPreserveRatio(true);
            Text tituloSocial = new Text(" Social Media");
            titleImgSocial.getChildren().addAll(imgsoci, tituloSocial);
            mediaSocial.getChildren().add(titleImgSocial);
            if (co.getSocialsMedia() != null) {
                for (SocialMedia scm : co.getSocialsMedia()) {
                    HBox infoSocial = new HBox();
                    Text socialMedia = new Text(scm.getSocialMedia() + ": ");
                    socialMedia.getStyleClass().add("text-normal");
                    Text userSocia = new Text(scm.getUser());
                    userSocia.getStyleClass().add("text-normal");
                    infoSocial.getChildren().addAll(socialMedia, userSocia);
                    infoSocial.getStyleClass().add("hbox-style");
                    mediaSocial.getChildren().add(infoSocial);
                }
            }

            VBox ContactosRelated = new VBox();
            HBox titleImaCOn = new HBox();
            ImageView imgCOn = new ImageView(new Image("Iconos/relacion.png"));
            imgCOn.setFitWidth(38);
            imgCOn.setFitHeight(38);
            imgCOn.setPreserveRatio(true);
            Text tituloConta = new Text(" Contactos Relates");
            titleImaCOn.getChildren().addAll(imgCOn, tituloConta);
            ContactosRelated.getChildren().add(titleImaCOn);
            if (co.getRelatedContacts() != null) {
                for (RelatedContact ctr : co.getRelatedContacts()) {
                    HBox infoContac = new HBox();
                    Text typeContact = new Text(ctr.getContactType() + ": ");
                    typeContact.getStyleClass().add("text-normal");
                    Text contactName = new Text(ctr.getContact().getName());
                    contactName.getStyleClass().add("text-normal");
                    infoContac.getChildren().addAll(typeContact, contactName);
                    infoContac.getStyleClass().add("hbox-style");
                    infoContac.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                        setContact(ctr.getContact());
                    });
                    ContactosRelated.getChildren().add(infoContac);
                }
            }

            VBox recorrerPhothos = new VBox(10);
            recorrerPhothos.setAlignment(Pos.CENTER);
            HBox flachasImga = new HBox(20);
            flachasImga.setAlignment(Pos.CENTER);
            ListIterator<String> iterador;
            DoublyLinkedList fotosUrl = Util.converUrlDoublyLinked(co.getPhotos());
            ImageView nextBtn = new ImageView(new Image("Iconos/nextbtn.png"));
            ImageView preBtn = new ImageView(new Image("Iconos/prebtn.png"));
            nextBtn.setFitWidth(50);
            nextBtn.setFitHeight(50);
            nextBtn.setPreserveRatio(true);
            preBtn.setFitWidth(50);
            preBtn.setFitHeight(50);
            preBtn.setPreserveRatio(true);
            Text tiuloPhothos = new Text("Photos of " + co.getName());
            if (fotosUrl != null) {
                iterador = fotosUrl.listIterator();
                try {
                    ImageView foto = new ImageView(new Image(iterador.next()));
                    foto.getStyleClass().add("imageViewStyle");
                    foto.setFitWidth(260);
                    foto.setFitHeight(200);
                    Rectangle clip = new Rectangle(
                            foto.getFitWidth(), foto.getFitHeight()
                    );
                    clip.setArcWidth(25);
                    clip.setArcHeight(25);
                    foto.setClip(clip);
                    StackPane imageContainer = new StackPane();
                    imageContainer.setPrefSize(foto.getFitWidth(), foto.getFitHeight());
                    imageContainer.getChildren().add(foto);
                    nextBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                        if (prebutn == true) {
                            iterador.next();
                            iterador.next();
                        }
                        prebutn = false;
                        foto.setImage(new Image(iterador.next()));
                        nextbutn = true;
                        nvezBo++;
                    });
                    preBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                        if (nvezBo == 0) {
                            iterador.previous();
                            iterador.previous();
                        } else if (nextbutn == true) {
                            iterador.previous();
                            iterador.previous();
                        }
                        nextbutn = false;
                        foto.setImage(new Image(iterador.previous()));
                        prebutn = true;
                        nvezBo++;
                    });

                    flachasImga.getChildren().addAll(preBtn, nextBtn);

                    recorrerPhothos.getChildren().addAll(tiuloPhothos, foto, flachasImga);
                } catch (IllegalArgumentException E) {
                    System.out.println("No found URLs Image");
                }
            }

            ifocon.getChildren().addAll(telephones, Date, Email, direccion, mediaSocial, ContactosRelated, recorrerPhothos);
            tituloTelf.getStyleClass().add("text-title");
            tiuloPhothos.getStyleClass().add("text-title");
            tituloDate.getStyleClass().add("text-title");
            titleDireccion.getStyleClass().add("text-title");
            tituloEmail.getStyleClass().add("text-title");
            tituloSocial.getStyleClass().add("text-title");
            tituloConta.getStyleClass().add("text-title");
            Date.getStyleClass().add("vbox-style");
            Email.getStyleClass().add("vbox-style");
            telephones.getStyleClass().add("vbox-style");
            direccion.getStyleClass().add("vbox-style");
            mediaSocial.getStyleClass().add("vbox-style");
            ContactosRelated.getStyleClass().add("vbox-style");

        }
    }

    public void encontrarConta() {
        try {
            while (itera.hasNext()) {
                Contact contactoActual = itera.next();
                if (contactoActual.getName().equals(co.getName())) {
                    break;
                }
            }
        } catch (NullPointerException E) {
            System.out.println("Contact NullPointerException");
        }

    }

    private int nvez = 0;
    private boolean pre = false;
    private boolean next = false;

    @FXML
    private void PreviousContact(MouseEvent event) {
        try {
            if (nvez == 0) {
                encontrarConta();
                itera.previous();
                itera.previous();
            } else if (next == true) {
                itera.previous();
                itera.previous();
            }
            next = false;
            setContact(itera.previous());
            pre = true;
            nvez++;
        } catch (NullPointerException E) {
            System.out.println("Contact NullPointerException");
        }
    }

    @FXML
    private void NextContact(MouseEvent event) {
        try {
            if (nvez == 0) {
                encontrarConta();
            } else if (pre == true) {
                itera.next();
                itera.next();
            }
            pre = false;
            setContact(itera.next());
            next = true;
            nvez++;
        } catch (NullPointerException E) {
            System.out.println("Contact NullPointerException");
        }
    }

    @FXML
    private void clickEditar(MouseEvent event) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PassInformation/Contact_edit.ser"))) {
            out.writeObject(co);
        } catch (IOException ioe) {
        }
        Parent root = FXMLLoader.load(getClass().getResource("newcontact.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) generalPane.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void btnRgresar(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("contacts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) generalPane.getScene().getWindow();
        stage.setScene(scene);
        if (!stage.isShowing()) {
            stage.show();
        }
    }
}
