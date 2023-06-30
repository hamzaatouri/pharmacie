/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.Fournisseur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javax.swing.JOptionPane;
import service.FournissuerService;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class FournisseurController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfTel;
    @FXML
    private TableView<Fournisseur> tvfournissuer;
    @FXML
    private TableColumn<Fournisseur, Integer> colid;
    @FXML
    private TableColumn<Fournisseur, String> colnom;
    @FXML
    private TableColumn<Fournisseur, String> colprenom;
    @FXML
    private TableColumn<Fournisseur, String> coladresse;
    @FXML
    private TableColumn<Fournisseur, Integer> coltel;
    @FXML
    private TableColumn<Fournisseur, String> colemail;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button ajouter;
    @FXML
    private TextField tfemail;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show_fournisseurs();
        select_fournisseur();
    }    

    @FXML
    private void update(ActionEvent event) {
         FournissuerService fs = new FournissuerService();
         fs.update(new Fournisseur(Integer.parseInt(tfID.getText()),tfNom.getText(),tfPrenom.getText(),tfAdresse.getText(),Integer.parseInt(tfTel.getText()),tfemail.getText()));
        show_fournisseurs();
    }

    @FXML
    private void delete(ActionEvent event) {
        
        FournissuerService fs = new FournissuerService();
        fs.delete(Integer.parseInt(tfID.getText()));
        show_fournisseurs();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
         if((tfNom.getText().equals(""))||(tfPrenom.getText().equals(""))||(tfAdresse.getText().equals(""))||(tfTel.getText().equals(""))||(tfemail.getText().equals(""))){
             JOptionPane.showMessageDialog(null,"Erreur!!!!!Champ vide");
        }
        else if(!estUnEntier(tfTel.getText())){
            JOptionPane.showMessageDialog(null,"Erreur!!!!!champ Telephone doit etre un entier");
        }
        else
        {
        FournissuerService fs = new FournissuerService();
        fs.insert(new Fournisseur(tfNom.getText(),tfPrenom.getText(),tfAdresse.getText(),Integer.parseInt(tfTel.getText()),tfemail.getText()));
    }}
         
         public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
    
    private void show_fournisseurs(){
     FournissuerService fs = new FournissuerService();
    fs.readAll();
    ObservableList<Fournisseur> list = FXCollections.observableArrayList(fs.readAll());
    
    colid.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("id"));
    colnom.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("nom"));
    colprenom.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("prenom"));
    coladresse.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("adresse"));
    coltel.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("tel"));
    colemail.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("mail"));
    
    tvfournissuer.setItems(list);
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuGérant.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
    
         private void select_fournisseur(){
        
        tvfournissuer.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
        public void handle(MouseEvent event){
        Fournisseur f= tvfournissuer.getItems().get(tvfournissuer.getSelectionModel().getSelectedIndex());
        
        tfID.setText(String.valueOf(f.getId()));
        tfNom.setText(f.getNom());
        tfPrenom.setText(f.getPrenom());
        tfAdresse.setText(f.getAdresse());
        tfTel.setText(String.valueOf(f.getTel()));
        tfemail.setText(f.getMail());
        String s ;
        s = f.getNom();
        
        
    
    
    }
    
                });
                }
         
         
}
