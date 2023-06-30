/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Epreuve;
import entité.Patient;
import entité.Produit;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import service.EpreuveService;
import service.PatientService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class EpreuveController implements Initializable {

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
    private Button btnadd;
    @FXML
    private ImageView back;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private DatePicker date_test;
    @FXML
    private ComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("POSITIF" , "NEGATIF");
        combo.setItems(list);
    }    

    @FXML
    private void add(ActionEvent event) {
         String s = combo.getSelectionModel().getSelectedItem().toString();
        boolean resultat;
        if(s.equals("POSITIF")){
        resultat=TRUE;
        }
        else{
            resultat=FALSE;
        }
        int qte=0;
        String requet="select id_patient from patient";
        String requet2 ="select qte_stock from produit where id_produit=2";
        String requet3 ="UPDATE produit SET qte_stock = ?  WHERE id_produit = 2";
        Patient p =new Patient();
        int generatedKey = 0;
        
        
        Connection connexion = DataSource.getInstance().getCnx();
         PatientService ps=new PatientService();
         ps.insert(new Patient(nom.getText(),prenom.getText(),adresse.getText(),Integer.parseInt(tel.getText()),mail.getText(),date_naissance.getValue().toString()));
        try {
            Statement ste = connexion.createStatement();
            ResultSet rs = ste.executeQuery(requet);
            Statement ste2 = connexion.createStatement();
            ResultSet rs2 = ste2.executeQuery(requet2);
            
           
           while(rs2.next()){
           qte = rs2.getInt(1);
           }
             
              while(rs.next()){
                  generatedKey = rs.getInt(1);
              }
        } catch (SQLException ex) {
            Logger.getLogger(VaccinationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
         EpreuveService vs = new EpreuveService();
         
         vs.insert(new Epreuve(resultat,date_test.getValue().toString(),generatedKey));
        try {
            PreparedStatement pst = connexion.prepareStatement(requet3);
            Produit pr = new Produit();
            Statement  ste3= connexion.createStatement();
            pst.setInt(1,(qte-1));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EpreuveController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("typetraitement.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
    
}
