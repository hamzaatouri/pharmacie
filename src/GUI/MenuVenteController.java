/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entité.Panier;
import entité.Vente;
import entité.Association_contre_indiquet;
import entité.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.ProduitService;
import service.PanierService;

import service.VenteService ; 
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import service.AsciService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author balouti.melek
 */
public class MenuVenteController implements Initializable {
private Connection connexion;
private Statement ste ; 
private Statement st ; 

private int qte ; 
    @FXML
    private Button SUPPRIMER;
    @FXML
    private ImageView IDEspèce;
    @FXML
    private ImageView IDCARTE;
    @FXML
    private ImageView IDCH;
    @FXML
    private Button Annuler;
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
     @FXML
    private TableColumn<Produit, Integer> ID;
    private TableColumn<Vente, String> COLNAME;

    private TableColumn<Vente, Integer> COLPU;
    @FXML
    private TextField somme;
    @FXML
    private TextField mp;
    float a = 0;
    float C = 0;
    @FXML
    private TextField RechercheP;

    /**
     * Initializes the controller class.
     */
    //public List<Produit> produit;
    ProduitService ps = new ProduitService();
    private final ObservableList<Produit> list = FXCollections.observableArrayList(ps.readAll());
    @FXML
    private TextField Nomp;
    @FXML
    private Button AJOUTER;
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
ID.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_produit"));
        colnom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom_prod"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
        colfabrication.setCellValueFactory(new PropertyValueFactory<Produit, String>("date_fabrication"));
        colexpiration.setCellValueFactory(new PropertyValueFactory<Produit, String>("date_expiration"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        colqte.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("qte"));

        FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);

        RechercheP.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Produit.getNom_prod().toLowerCase()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Produit.getCategorie()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Produit.getDate_expiration()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Produit> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(TVProduit.comparatorProperty());
        

        TVProduit.setItems(sortedData);
       
        select_produit();

    }

    private void Valider(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuVenteEsp.fxml"));
//        Parent root = loader.load();
//        IDEspèce.getScene().setRoot(root);
qte=0 ; 
         
 String requet ="select qte_stock from produit where id_produit=?";
 

    
    }
    @FXML
    private void SUPPRIMER(ActionEvent event) {
    }

    @FXML
    private void Espèce(MouseEvent event) throws IOException {
        
        int i,j;
        PanierService ps = new PanierService();
        AsciService as = new AsciService();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Association contre indiquet");
        
        for(i=0;i<ps.readAll().size();i++){
        for(j=0;j<as.readAll().size();j++){
            
            if(((ps.readAll().get(i).getNomp_prd().equals(as.readAll().get(j).getMed1()))&&(ps.readAll().get(i+1).getNomp_prd().equals(as.readAll().get(j).getMed2())))
                ||
              ((ps.readAll().get(i).getNomp_prd().equals(as.readAll().get(j).getMed2()))&&(ps.readAll().get(i+1).getNomp_prd().equals(as.readAll().get(j).getMed1()))))
            {
              alert.setContentText(as.readAll().get(j).getNom_comb());
 
              alert.showAndWait();
              String requet = "TRUNCATE TABLE panier";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            
        }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuVenteEsp.fxml"));
        Parent root = loader.load();
        IDEspèce.getScene().setRoot(root);

    }

    @FXML
    private void Carte(MouseEvent event) throws IOException {
        
        int i,j;
        PanierService ps = new PanierService();
        AsciService as = new AsciService();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Association contre indiquet");
        
        for(i=0;i<ps.readAll().size();i++){
        for(j=0;j<as.readAll().size();j++){
            
            if(((ps.readAll().get(i).getNomp_prd().equals(as.readAll().get(j).getMed1()))&&(ps.readAll().get(i+1).getNomp_prd().equals(as.readAll().get(j).getMed2())))
                ||
              ((ps.readAll().get(i).getNomp_prd().equals(as.readAll().get(j).getMed2()))&&(ps.readAll().get(i+1).getNomp_prd().equals(as.readAll().get(j).getMed1()))))
            {
              alert.setContentText(as.readAll().get(j).getNom_comb());
 
              alert.showAndWait();
              String requet = "TRUNCATE TABLE panier";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            
        }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuVenteCarte.fxml"));
        Parent root = loader.load();
        IDCARTE.getScene().setRoot(root);

    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        
        int i = 0;
        int j = 0;
         
        PanierService ps = new PanierService();
        ProduitService p = new ProduitService();
         for(i=0;i<p.readAll().size();i++){
         for(j=0;j<ps.readAll().size();j++){
             if(p.readAll().get(i).getNom_prod().equals(ps.readAll().get(j).getNomp_prd())){
                 
                     int qte=p.readAll().get(i).getQte()+1;
                     int id =p.readAll().get(i).getId();
                     
                     
                     String requete = "UPDATE produit SET qte_stock = "+ qte +" WHERE id_produit = " +id;
                     Connection connection = DataSource.getInstance().getCnx();
                     try {
                     PreparedStatement pst = connection.prepareStatement(requete);
                     pst.executeUpdate();
                 } catch (SQLException ex) {
                     Logger.getLogger(MenuVenteController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         
         }
    }
        String requet = "TRUNCATE TABLE panier";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuVente.fxml"));
        Parent root = loader.load();
        Annuler.getScene().setRoot(root);
    }

    private void show_produit() {
     
        ProduitService ps = new ProduitService();
        ps.readAll();
        ObservableList<Produit> list = FXCollections.observableArrayList(ps.readAll());
        colnom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom_prod"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
        colfabrication.setCellValueFactory(new PropertyValueFactory<Produit, String>("date_fabrication"));
        colexpiration.setCellValueFactory(new PropertyValueFactory<Produit, String>("date_expiration"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        colqte.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("qte"));
        TVProduit.setItems(list);
       

    }
//public void showvente(){
    
    //VenteService vs = new VenteService();
    //vs.readAll();
    //ObservableList<Vente> list = FXCollections.observableArrayList(vs.readAll());
    
    //COLNAME.setCellValueFactory(new PropertyValueFactory<Vente, String>("prd_name"));
    //COLPU.setCellValueFactory(new PropertyValueFactory<Vente, Integer>("prix"));
    //TVSOMME.setItems(list);
    //}


    public float select_produit() {

        TVProduit.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Produit p = TVProduit.getItems().get(TVProduit.getSelectionModel().getSelectedIndex());
             Nomp.setText(String.valueOf(p.getNom_prod()));
                mp.setText(String.valueOf(p.getPrix()));
                a = Float.parseFloat(mp.getText());
                C = C + select_produit();
                somme.setText(String.valueOf(C));

            }

        });

        return a;

    }

    @FXML
    private void V_cheque(MouseEvent event) throws IOException {
        int i,j;
        PanierService ps = new PanierService();
        AsciService as = new AsciService();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Association contre indiquet");
        
        for(i=0;i<as.readAll().size();i++){
        for(j=0;j<ps.readAll().size();j++){
            
            if(((ps.readAll().get(i).getNomp_prd().equals(as.readAll().get(j).getMed1()))&&(ps.readAll().get(i+1).getNomp_prd().equals(as.readAll().get(j).getMed2())))
                ||
              ((ps.readAll().get(i).getNomp_prd().equals(as.readAll().get(j).getMed2()))&&(ps.readAll().get(i+1).getNomp_prd().equals(as.readAll().get(j).getMed1()))))
            {
              alert.setContentText(as.readAll().get(j).getNom_comb());
 
              alert.showAndWait();
              String requet = "TRUNCATE TABLE panier";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            
        }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuVcheque.fxml"));
        Parent root = loader.load();
        IDCH.getScene().setRoot(root);
    }

    @FXML
    private void afficher(MouseEvent event) {
        //Produit p = TVProduit.getItems().get(TVProduit.getSelectionModel().getSelectedIndex());
        //panier.add(new Panier(p.getNom_prod(),get
        //COLPU.setCellValueFactory(new PropertyValueFactory<List, String>("prix"));
        //COLNAME.setCellValueFactory(new PropertyValueFactory<List, String>("nom_prod"));
        //TVSOMME.setItems((ObservableList<Produit>) TVProduit);
    }

    @FXML
    private void handle(MouseEvent event) {
    }

    @FXML
    private void Recherche(ActionEvent event) {
        
    }

    @FXML
    private void AJOUTER(ActionEvent event) {
       
    
        PanierService ps = new PanierService();
        
        ps.insert(new Panier(Nomp.getText() , Float.parseFloat(mp.getText() ) ));
        Produit p = TVProduit.getItems().get(TVProduit.getSelectionModel().getSelectedIndex());
        int a=0;
        int b=0;
        a= p.getId();
        b=p.getQte();
        String requet2 ="Update produit SET   qte_stock =? where id_produit="+a;
        Connection connection = DataSource.getInstance().getCnx();
        try {
        PreparedStatement pst = connection.prepareStatement(requet2);
        pst.setInt(1,(b-1));
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(MenuVenteController.class.getName()).log(Level.SEVERE, null, ex);
    }
        show_produit();
        
    }

}