/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.PatientService;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class PatientmanagementController implements Initializable {

    @FXML
    private TableView<Patient> patienttable;
    @FXML
    private TableColumn<Patient, Integer> id;
    @FXML
    private TableColumn<Patient, String> nom;
    @FXML
    private TableColumn<Patient, String> prenom;
    @FXML
    private TableColumn<Patient, String> adresse;
    @FXML
    private TableColumn<Patient, Integer> tel;
    @FXML
    private TableColumn<Patient, String> email;
    @FXML
    private TableColumn<Patient, String> date_de_naissance;
    @FXML
    private ImageView add;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView update;
    @FXML
    private Button stat;
    @FXML
    private Button mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPatients();
    }    




    public void showPatients(){
    
    PatientService ps = new PatientService();
    ps.readAll();
    ObservableList<Patient> list = FXCollections.observableArrayList(ps.readAll());
    
    id.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<Patient,String>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<Patient,String>("prenom"));
    adresse.setCellValueFactory(new PropertyValueFactory<Patient,String>("adresse"));
    tel.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("tel"));
    email.setCellValueFactory(new PropertyValueFactory<Patient,String>("mail"));
    date_de_naissance.setCellValueFactory(new PropertyValueFactory<Patient,String>("date_de_naissance"));
    
    patienttable.setItems(list);
    }

    @FXML
    private void add(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("typetraitement.fxml"));
        Parent root=loader.load();
        add.getScene().setRoot(root);
    }

    
        @FXML
    private void delete(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deletepat.fxml"));
        Parent root=loader.load();
        delete.getScene().setRoot(root);
    }
    
    
    @FXML
    private void update(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updatepat.fxml"));
        Parent root=loader.load();
        update.getScene().setRoot(root);
        
    }

    @FXML
    private void afficherstat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
        Parent root=loader.load();
        stat.getScene().setRoot(root);
    }

    @FXML
    private void mail(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("mail.fxml"));
        Parent root=loader.load();
        mail.getScene().setRoot(root);
    }
}
