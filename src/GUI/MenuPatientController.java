/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import service.PatientService;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class MenuPatientController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsup;
    @FXML
    private Button btnlister;
    @FXML
    private Button btnmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPatient.fxml"));
        Parent root=loader.load();
        btnajouter.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeletePatient.fxml"));
        Parent root=loader.load();
        btnlister.getScene().setRoot(root);
    }

    @FXML
    private void lister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPatient.fxml"));
        Parent root=loader.load();
        btnlister.getScene().setRoot(root);
        
        
    }

    @FXML
    private void modifier(ActionEvent event) {
    }
    }
    

