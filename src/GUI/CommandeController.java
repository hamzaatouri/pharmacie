/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Commande;
import entité.Fournisseur;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.CommandeService;
import service.FournissuerService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tfQte;
    @FXML
    private ComboBox<String> combo_fournisseur;
    @FXML
    private TableColumn<Commande, Integer> colid;
    @FXML
    private TableColumn<Commande, String> coldate;
    @FXML
    private TableColumn<Commande, Integer> colqte;
    @FXML
    private TableColumn<Commande, Integer> colfournisseur;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Commande> tvcommande;

    /**
     * Initializes the controller class.
     */
     ObservableList<String> list = FXCollections.observableArrayList();
     private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    @FXML
    private ImageView back;
   
    
    
    
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       FillComboBox();
        combo_fournisseur.setItems(list);
        showCommandeList();
        select_commande();
    }   
    
    public void FillComboBox(){
    
    String requet="select fournisseur_name from fournisseur";
       connexion = DataSource.getInstance().getCnx();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
                              list.add(rs.getString("fournisseur_name"));

              
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        CommandeService cs = new CommandeService ();
        cs.update(new Commande(Integer.parseInt(tfID.getText()),tfDate.getValue().toString(),Integer.parseInt(tfQte.getText()),select_fournisseur()));
         showCommandeList();
    }

    @FXML
    private void delete(ActionEvent event) {
        CommandeService cs = new CommandeService ();
         cs.delete(Integer.parseInt(tfID.getText()));
        showCommandeList();
    }

    @FXML
    private void ajouter(ActionEvent event) {
         if(tfQte.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Erreur!!!!!Champ vide");
        }
        else if(!estUnEntier(tfQte.getText())){
            JOptionPane.showMessageDialog(null,"Erreur!!!!!champ Qte doit etre un entier");
        }
        else
        {
        CommandeService cs = new CommandeService();
       cs.insert(new Commande(tfDate.getValue().toString(),Integer.parseInt(tfQte.getText()),select_fournisseur()));
       showCommandeList();
    }}
    
    public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
    
    public int select_fournisseur() {
         int id = 0;
        try {
           
            String s = combo_fournisseur.getSelectionModel().getSelectedItem().toString();
            String query ="select id_fournisseur from fournisseur where fournisseur_name=?";
            Connection connection = DataSource.getInstance().getCnx();
            PreparedStatement pst = connection.prepareStatement(query);
            ste = connexion.createStatement();
            pst.setString(1,s);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
     public void showCommandeList(){
    
    CommandeService cs = new CommandeService();
    FournissuerService fs = new FournissuerService();
    fs.readAll();
    cs.readAll();
    ObservableList<Fournisseur> list2 = FXCollections.observableArrayList(fs.readAll());
    ObservableList<Commande> list = FXCollections.observableArrayList(cs.readAll());
    
    colid.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
    coldate.setCellValueFactory(new PropertyValueFactory<Commande,String>("date_comm"));
    colqte.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("qte"));
    colfournisseur.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_fournisseur"));
    
    
    
    tvcommande.setItems(list);
    
    }
     
    
     
     private void select_commande(){
        
        tvcommande.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
        public void handle(MouseEvent event){
        Commande c= tvcommande.getItems().get(tvcommande.getSelectionModel().getSelectedIndex());
        
        tfID.setText(String.valueOf(c.getId()));
        
        tfQte.setText(String.valueOf(c.getQte()));
        
    
    
    }
    
                });
                }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuGérant.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
    
}
