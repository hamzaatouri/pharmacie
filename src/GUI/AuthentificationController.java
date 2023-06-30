/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField password;
    
    
    
    
    Connection con;
    PreparedStatement pst,pst2;
    ResultSet rs,rs2;
    @FXML
    private Button log;
    @FXML
    private Label title;
    @FXML
    private Button forgottenpassword;
    @FXML
    private TextField email;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        
        String Email = email.getText();
        String Password = password.getText();
       
        
        
        if(email.equals("") || Password.equals("")){
        
        JOptionPane.showMessageDialog(null,"Username or Password Blank");
        
        }
        else{
        
            String requete = "Select * from user where email=? and password=? and is_admin like 'true'";
            String requete2 = "Select * from user where email=? and password=? ";
             con = DataSource.getInstance().getCnx();
            try {
                pst = con.prepareStatement(requete);
                pst.setString(1, Email);
                pst.setString(2, Password);
                rs=pst.executeQuery();
                pst2 = con.prepareStatement(requete2);
                pst2.setString(1, Email);
                pst2.setString(2, Password);
                rs2=pst2.executeQuery();
                
                if(rs.next()){
                
                JOptionPane.showMessageDialog(null, "Session du Gérant");
               
                Parent root=FXMLLoader.load(getClass().getResource("MenuGérant.fxml"));
                Scene scene = new Scene(root);
                
                Stage s = new Stage();
                s.setScene(scene);
                s.show();
                
                Stage stage = (Stage) title.getScene().getWindow();
                stage.close();
                
                            
                
                }
                else if(rs2.next()){
                JOptionPane.showMessageDialog(null, "Session du Pharmacien");
                Parent root=FXMLLoader.load(getClass().getResource("MenuPharmacien.fxml"));
                Scene scene = new Scene(root);
                
                
                Stage s = new Stage();
                s.setScene(scene);
                s.show();
                
                Stage stage = (Stage) title.getScene().getWindow();
                stage.close();
                }
                
                else {
                
                JOptionPane.showMessageDialog(null, "login failed");
                
                
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        
        
    }

    @FXML
    private void forgottenpassword(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordChange.fxml"));
        Parent root=loader.load();
        forgottenpassword.getScene().setRoot(root);
        
    }

    
  
    }
    

