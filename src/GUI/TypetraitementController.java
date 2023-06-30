/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class TypetraitementController implements Initializable {

    @FXML
    private ImageView vaccination;
    @FXML
    private ImageView test;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void vaccin(MouseEvent event) throws IOException {
        String requet2 ="select qte_stock from produit where id_produit=1";
        Connection connexion = DataSource.getInstance().getCnx();
        int qte = 100;
        try {
            Statement ste2;
            ste2 = connexion.createStatement();
            ResultSet rs2 = ste2.executeQuery(requet2);
            
             while(rs2.next()){
           qte = rs2.getInt(1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(TypetraitementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(qte==0){
         JOptionPane.showMessageDialog(null, "Vaccin hors stock");
        }else
        {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vaccination.fxml"));
        Parent root=loader.load();
        vaccination.getScene().setRoot(root);
    }
    }

    @FXML
    private void test(MouseEvent event) throws IOException {
        String requet2 ="select qte_stock from produit where id_produit=2";
        Connection connexion = DataSource.getInstance().getCnx();
        int qte = 100;
        try {
            Statement ste2;
            ste2 = connexion.createStatement();
            ResultSet rs2 = ste2.executeQuery(requet2);
            
             while(rs2.next()){
           qte = rs2.getInt(1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(TypetraitementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(qte==0){
         JOptionPane.showMessageDialog(null, "Test_Covid hors stock");
        }else
        {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Epreuve.fxml"));
        Parent root=loader.load();
        test.getScene().setRoot(root);
    }
    }

    @FXML
    
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPharmacien.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
    
}
