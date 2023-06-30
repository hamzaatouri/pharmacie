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
public class MenuGérantController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button users;
    @FXML
    private Button commande;
    @FXML
    private Button fournisseurs;
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;

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
    private void users(MouseEvent event) {
         loadpage("UserCrud");
    }

    @FXML
    private void comaande(MouseEvent event) {
         loadpage("Commande");
    }

    @FXML
    private void fournisseurs(MouseEvent event) {
         loadpage("Fournisseur");
    }
    
    private void loadpage(String page){
   
       Parent root = null;
        try {
            root=FXMLLoader.load(getClass().getResource(page +".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuGérantController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   bp.setCenter(root);
   }
    
}
