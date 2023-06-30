/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class MenuPharmacienController implements Initializable {

    @FXML
    private Button patientbtn;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button hide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void patientmanagement(MouseEvent event) {
        loadpage("patientmanagement");
    }

    @FXML
    private void salesmanagement(MouseEvent event) {
        loadpage("MenuVente");
    }

    @FXML
    private void medecinemanagement(MouseEvent event) {
        loadpage("GestionProduit");
    }
    @FXML
    private void ASCI(MouseEvent event) {
        loadpage("asci");
    }

   private void loadpage(String page){
   
       Parent root = null;
        try {
            root=FXMLLoader.load(getClass().getResource(page +".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuPharmacienController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   bp.setCenter(root);
   }

    
    
}
