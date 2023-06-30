/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Association_contre_indiquet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.AsciService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AsciController implements Initializable {

    @FXML
    private TableView<Association_contre_indiquet> TVProduit;
    @FXML
    private TableColumn<Association_contre_indiquet, String> name;
    @FXML
    private TableColumn<Association_contre_indiquet, String> med1;
    @FXML
    private TableColumn<Association_contre_indiquet, String> med2;
    @FXML
    private Button ajouter;
    @FXML
    private Button SUPPRIMER;
    @FXML
    private Button modifier;
    @FXML
    private TextField id;
    @FXML
    private TextField combname;
    @FXML
    private TextField medname1;
    @FXML
    private TextField medname2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         show_combo();
         select_combo();
     
   }    

    
    private void show_combo(){
    
        AsciService ps = new AsciService();
    ps.readAll();
    ObservableList<Association_contre_indiquet> list = FXCollections.observableArrayList(ps.readAll());
    
    name.setCellValueFactory(new PropertyValueFactory<Association_contre_indiquet,String>("nom_comb"));
    med1.setCellValueFactory(new PropertyValueFactory<Association_contre_indiquet,String>("med1"));
    med2.setCellValueFactory(new PropertyValueFactory<Association_contre_indiquet,String>("med2"));
    TVProduit.setItems(list);
    }
   
    
    @FXML
    private void ajouter(ActionEvent event) {
        AsciService ps = new AsciService();
        ps.insert(new Association_contre_indiquet(combname.getText(),medname1.getText(),medname2.getText()));
   show_combo();
    }

    @FXML
    private void SUPPRIMER(ActionEvent event) {
        AsciService ps = new AsciService();
     ps.delete(Integer.parseInt(id.getText()));
      show_combo();
    }

    @FXML
    private void modifier(ActionEvent event) {
            AsciService ps = new AsciService();
        ps.update(new Association_contre_indiquet (Integer.parseInt(id.getText()),combname.getText(),medname1.getText(),medname2.getText()));
   show_combo();
    }
    
   



private void select_combo(){
        
        TVProduit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
        public void handle(MouseEvent event){
        Association_contre_indiquet u= TVProduit.getItems().get(TVProduit.getSelectionModel().getSelectedIndex());
        
        id.setText(String.valueOf(u.getId_combinaison()));
       combname.setText(u.getNom_comb());
        medname1.setText(u.getMed1());
        medname2.setText(u.getMed2());
    
    
    }
    
                });
}
}

