/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author balouti.melek
 */
public class GestionProduitController implements Initializable {

    @FXML
    private TableView<Produit> TVProduit;
    @FXML
    private TableColumn<Produit, String> colnom;
    @FXML
    private TableColumn<Produit, String> colcategorie;
    @FXML
    private TableColumn<Produit, String> colfabrication;
    @FXML
    private TableColumn<Produit, String> colexpiration;
    @FXML
    private TableColumn<Produit, Float> colprix;
    @FXML
    private TableColumn<Produit, Integer> colqte;
    private TextField somme;
    private TextField mp;
   float a = 0;
    float c=0;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField cat;
    @FXML
    private TextField dateFab;
    @FXML
    private TextField dateExp;
    @FXML
    private TextField prix;
    @FXML
    private TextField qte;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button SUPPRIMER;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show_produit();
        select_produit();
    }


    @FXML
    private void SUPPRIMER(ActionEvent event) {
    
     ProduitService ps = new ProduitService();
     ps.delete(Integer.parseInt(id.getText()));
      show_produit();
     
    }



    
    private void show_produit(){
    
        ProduitService ps = new ProduitService();
    ps.readAll();
    ObservableList<Produit> list = FXCollections.observableArrayList(ps.readAll());
    
  colnom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_prod"));
    colcategorie.setCellValueFactory(new PropertyValueFactory<Produit,String>("categorie"));
    colfabrication.setCellValueFactory(new PropertyValueFactory<Produit,String>("date_fabrication"));
    colexpiration.setCellValueFactory(new PropertyValueFactory<Produit,String>("date_expiration"));
    colprix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix"));
    colqte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
    TVProduit.setItems(list);
    
    
    }

    @FXML
    private void ajouter(ActionEvent event) {
   ProduitService ps = new ProduitService();
        ps.insert(new Produit(nom.getText(),cat.getText(),dateFab.getText(),dateExp.getText(),Float.parseFloat(prix.getText()),Integer.parseInt(qte.getText())));
   show_produit();
    }

    @FXML
    private void modifier(ActionEvent event) {
     ProduitService ps = new ProduitService();
        ps.update(new Produit(Integer.parseInt(id.getText()),nom.getText(),cat.getText(),dateFab.getText(),dateExp.getText(),Float.parseFloat(prix.getText()),Integer.parseInt(qte.getText())));
   show_produit();
    }
    
    private void select_produit(){
        
        TVProduit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
        public void handle(MouseEvent event){
        Produit u= TVProduit.getItems().get(TVProduit.getSelectionModel().getSelectedIndex());
        
        id.setText(String.valueOf(u.getId()));
        nom.setText(u.getNom_prod());
        cat.setText(u.getCategorie());
        dateFab.setText(u.getDate_fabrication());
        dateExp.setText(u.getDate_expiration());
        prix.setText(String.valueOf(u.getPrix()));
        qte.setText(String.valueOf(u.getQte()));
    
    
    }
    
                });
                }


    
     

    

      
    
}
