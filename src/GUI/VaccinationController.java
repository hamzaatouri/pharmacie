/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Patient;
import entité.Produit;
import entité.Vaccination;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.PatientService;
import service.VaccinationService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class VaccinationController implements Initializable {

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
    private TextField num_dose;
    @FXML
    private DatePicker date_dose;
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}


    @FXML
    private void add(ActionEvent event) {
        
        if(nom.getText().equals("")||prenom.getText().equals("")||adresse.getText().equals("")||tel.getText().equals("")||mail.getText().equals("")||num_dose.getText().equals("")){
            
            JOptionPane.showMessageDialog(null,"Erreur!!!!!Champ vide");
        }
        
        else if(!estUnEntier(tel.getText())){
            JOptionPane.showMessageDialog(null,"Erreur!!!!!champ telephone doit etre un entier");
        } 
         else if(!estUnEntier(num_dose.getText())){
            JOptionPane.showMessageDialog(null,"Erreur!!!!!champ num_dose doit etre un entier");
        } 
        else{
        int qte=0;
        String requet="select id_patient from patient";
        String requet2 ="select qte_stock from produit where id_produit=1";
        String requet3 ="UPDATE produit SET qte_stock = ?  WHERE id_produit = 1";
        Patient p =new Patient();
        int generatedKey = 0;
        connexion = DataSource.getInstance().getCnx();
         PatientService ps=new PatientService();
         ps.insert(new Patient(nom.getText(),prenom.getText(),adresse.getText(),Integer.parseInt(tel.getText()),mail.getText(),date_naissance.getValue().toString()));
        try {
            ste=connexion.createStatement();
             rs= ste.executeQuery(requet);
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
          
         VaccinationService vs = new VaccinationService();
         if(Integer.parseInt(num_dose.getText())>10){
         
         }
         vs.insert(new Vaccination(Integer.parseInt(num_dose.getText()),date_dose.getValue().toString(),generatedKey));
          try {
            PreparedStatement pst = connexion.prepareStatement(requet3);
            Produit pr = new Produit();
            Statement  ste3= connexion.createStatement();
            pst.setInt(1,(qte-1));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EpreuveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("typetraitement.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
    
}
