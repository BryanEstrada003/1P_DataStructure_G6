/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class EigthTournamentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private User us;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us = User.getPassUser();
    }    
    
}
