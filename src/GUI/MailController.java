/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import GUI.MailSetup;
import entité.Epreuve;
import entité.Patient;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.PatientService;
import utils.DataSource;
/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class MailController implements Initializable {

    @FXML
    private TextField to;
    @FXML
    private  TextField from;
    @FXML
    private TextField subject;
    @FXML
    private  TextArea message;
    @FXML
    private Button send;
    @FXML
    private TableView<Patient> patienttable;
   @FXML
    private TableColumn<Patient, Integer> id;
    @FXML
    private TableColumn<Patient, String> nom;
    @FXML
    private TableColumn<Patient, String> prenom;
    @FXML
    private TableColumn<Patient, String> email;
    @FXML
    private TableColumn<Epreuve, Boolean> resultat;
    @FXML
    private TableView<Epreuve> tvresultat;

    /**
     * Initializes the controller class.
     */
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    @FXML
    private ImageView back;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPatients();
        select_patient();
    }    

    @FXML
    private void send(ActionEvent event) throws MessagingException {
    sendmail(to.getText());
    
    }
    
    public List readAll() {
         String requet="select * from " + "patient INNER JOIN epeuve ON patient.id_patient = epeuve.id_patient ";
        List<Patient> list=new ArrayList<>();
        connexion = DataSource.getInstance().getCnx();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
                Patient p = new Patient(rs.getInt("id_patient"),rs.getString(2),rs.getString("patient_surname"),rs.getString("patient_adresse"),rs.getInt("patient_tel"),rs.getString("patient_email"),rs.getString("patient_birthdate"));
               list.add(p);
           }
        
        
       } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }
        public List readAllFromEpreuve() {
         String requet="select * from " + "epeuve INNER JOIN patient ON patient.id_patient = epeuve.id_patient ";
        List<Epreuve> list=new ArrayList<>();
        connexion = DataSource.getInstance().getCnx();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Epreuve e = new Epreuve(rs.getInt("id_epreuve"),rs.getBoolean(2),rs.getString("date_epreuve"),rs.getInt("id_patient"));
               
               list.add(e);
           }
        
        
       } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }
    
    public void showPatients(){
    
       
        ObservableList<Patient> list = FXCollections.observableArrayList(readAll());
        ObservableList<Epreuve> listresultat = FXCollections.observableArrayList(readAllFromEpreuve());
        
    id.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<Patient,String>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<Patient,String>("prenom"));
    email.setCellValueFactory(new PropertyValueFactory<Patient,String>("mail"));
    resultat.setCellValueFactory(new PropertyValueFactory<Epreuve,Boolean>("resultat"));
    patienttable.setItems(list);
    tvresultat.setItems(listresultat);
    
    }
    
        private void select_patient(){
        
        patienttable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
        public void handle(MouseEvent event){
        Patient p= patienttable.getItems().get(patienttable.getSelectionModel().getSelectedIndex());
        Epreuve e= tvresultat.getItems().get(patienttable.getSelectionModel().getSelectedIndex());
        
       to.setText(p.getMail());
       from.setText("PHARMA BEST");
       subject.setText("COVID TEST RESULT");
      String s = (String.valueOf( e.getResultat())) ;
      String res;
      if(s.equals("true")){
      res = "POSITIF";
      }
      else
          res = "NEGATIF";
       message.setText("la resultat de votre test est " + res);
    message.setEditable(false);
    from.setEditable(false);
    to.setEditable(false);
    subject.setEditable(false);
    
    }
    
                });
        
        
        tvresultat.setOnMouseClicked(new EventHandler<MouseEvent>(){
        
         @Override
        public void handle(MouseEvent event){
         Epreuve e= tvresultat.getItems().get(patienttable.getSelectionModel().getSelectedIndex());
          
        }
        
        });
        
                }

    @FXML
     private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPharmacien.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
     
      public  void sendmail(String recepient) throws MessagingException {
    
        Properties properties = new Properties();
                
   properties.put("mail.smtp.auth","true");
   properties.put("mail.smtp.starttls.enable","true");
   properties.put("mail.smtp.host","smtp.gmail.com");
   properties.put("mail.smtp.port","587");
   
   String MyAccountEmail = "hzaatouri@gmail.com";
   String Password = "ucantseeme";
    
   
    Session session = Session.getInstance(properties , new Authenticator() {
            
     @Override
     protected PasswordAuthentication getPasswordAuthentication(){
     
     return new PasswordAuthentication(MyAccountEmail, Password);
     }       
            
});
    
    Message message = prepareMessage(session,MyAccountEmail,recepient);
            
          Transport.send(message);
    }
    
    private  Message prepareMessage(Session session ,String MyAccountEmail,String recepient){
    
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(MyAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            msg.setSubject(subject.getText());
            msg.setText(message.getText());
            return msg;
            
           
        } catch (Exception ex) {
            Logger.getLogger(MailSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return null;
    }
    
}
