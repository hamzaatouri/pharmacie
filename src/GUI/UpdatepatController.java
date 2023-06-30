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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.PatientService;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class UpdatepatController implements Initializable {

    @FXML
    private TableView<Patient> tvpatient;
    @FXML
    private TableColumn<Patient, Integer> id;
    @FXML
    private TableColumn<Patient, String> nom;
    @FXML
    private TableColumn<Patient, String> prenom;
    @FXML
    private TableColumn<Patient, String> adresse;
    @FXML
    private TableColumn<Patient, Integer> telephone;
    @FXML
    private TableColumn<Patient, String> mail;
    @FXML
    private TableColumn<Patient, String> birthday;
    @FXML
    private TextField tfID;
    @FXML
    private Button modifier;
    @FXML
    private TextField tfmail;
    @FXML
    private DatePicker tfbirthday;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnom;
    
    @FXML
    private TextField tftelephone;
    @FXML
    private Button back;
   
   
   
   
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showPatients();
       select_patient();
    }    
    
        @FXML
    

    private void modifier(ActionEvent event) {
         PatientService ps = new PatientService();
         ps.update(new Patient(Integer.parseInt(tfID.getText()),tfnom.getText(),tfprenom.getText(),tfadresse.getText(),Integer.parseInt(tftelephone.getText()),tfmail.getText(),tfbirthday.getValue().toString()));
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
    telephone.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("tel"));
    mail.setCellValueFactory(new PropertyValueFactory<Patient,String>("mail"));
    birthday.setCellValueFactory(new PropertyValueFactory<Patient,String>("date_de_naissance"));
    
    tvpatient.setItems(list);
    }
    
     private void select_patient(){
        
        tvpatient.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
        public void handle(MouseEvent event){
        Patient p= tvpatient.getItems().get(tvpatient.getSelectionModel().getSelectedIndex());
        
        tfID.setText(String.valueOf(p.getId()));
        tfnom.setText(p.getNom());
        tfprenom.setText(p.getPrenom());
        tfadresse.setText(p.getAdresse());
        tftelephone.setText(String.valueOf(p.getTel()));
        tfmail.setText(p.getMail());
       
        
    
    
    }
    
                });
                }

   
    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPharmacien.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }



   


}
