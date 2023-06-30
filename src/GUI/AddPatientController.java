/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.PatientService;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class AddPatientController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField tel;
    @FXML
    private TextField mail;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private Button btnadd;
    @FXML
    private ImageView back;
    @FXML
    private ComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> list = FXCollections.observableArrayList("VACCIN" , "TEST");
        combo.setItems(list);
        
    }    

    @FXML
    private void add(ActionEvent event) throws IOException {
        String s = combo.getSelectionModel().getSelectedItem().toString();
        
         PatientService ps=new PatientService();
         ps.insert(new Patient(nom.getText(),prenom.getText(),adresse.getText(),Integer.parseInt(tel.getText()),mail.getText(),date_naissance.getValue().toString()));
         JOptionPane.showMessageDialog(null, "Patient added succefully");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPatient.fxml"));
        Parent root=loader.load();
        btnadd.getScene().setRoot(root);
    }



    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientmanagement.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
    
}
