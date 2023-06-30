/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.User;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Connection;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.UserService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class UserCrudController implements Initializable {

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
    private TableView<User> tvuser;
    @FXML
    private TableColumn<User, Integer> colid;
    @FXML
    private TableColumn<User, String> colnom;
    @FXML
    private TableColumn<User, String> colprenom;
    @FXML
    private TableColumn<User, String> coladresse;
    @FXML
    private TableColumn<User, Integer> coltel;
    @FXML
    private Button btninsert;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TableColumn<User, Boolean> colrole;
    @FXML
    private TextField tfpassword;
    @FXML
    private TableColumn<?, ?> colpassword;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("GERANT" , "PHARAMACIEN");
        combo.setItems(list);
       showUsers();
       select_user();
       
    }    

    @FXML
    private void insert(ActionEvent event) {
        
        String s = combo.getSelectionModel().getSelectedItem().toString();
        boolean role;
        if(s.equals("GERANT")){
        role=TRUE;
        }
        else{
            role=FALSE;
        }
        UserService us = new UserService ();
        
       us.insert(new User (tfNom.getText() , tfPrenom.getText() , tfAdresse.getText() , Integer.parseInt(tfTel.getText()) , tfpassword.getText(), role  ) );
        showUsers();
        
        
    }
    private void select_user(){
        
        tvuser.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
        public void handle(MouseEvent event){
        User u= tvuser.getItems().get(tvuser.getSelectionModel().getSelectedIndex());
        
        tfID.setText(String.valueOf(u.getId()));
        tfNom.setText(u.getNom());
        tfPrenom.setText(u.getPrenom());
        tfAdresse.setText(u.getAdresse());
        tfTel.setText(String.valueOf(u.getTel()));
       
    
    }
    
                });
                }

    @FXML
    private void delete(ActionEvent event) {
        
        UserService us = new UserService ();
        
         
        us.delete(Integer.parseInt(tfID.getText()));
        showUsers();
        
    }

    @FXML
    private void update(ActionEvent event) {
        String s = combo.getSelectionModel().getSelectedItem().toString();
        boolean role;
        if(s.equals("GERANT")){
        role=TRUE;
        }
        else{
            role=FALSE;
        }
        UserService us = new UserService ();
        
       us.update(new User (Integer.parseInt(tfID.getText()),tfNom.getText() , tfPrenom.getText() , tfAdresse.getText() , Integer.parseInt(tfTel.getText()) , role, tfpassword.getText()  ) );
        showUsers();
    }
    
   
    
    public void showUsers(){
    
    UserService us = new UserService();
    us.readAll();
    ObservableList<User> list = FXCollections.observableArrayList(us.readAll());
    
    colid.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
    colnom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
    colprenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
    coladresse.setCellValueFactory(new PropertyValueFactory<User,String>("adresse"));
    coltel.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
    colrole.setCellValueFactory(new PropertyValueFactory<User,Boolean>("Isadmin"));
    
    tvuser.setItems(list);
    }

    @FXML
    private void select(ActionEvent event) {
        
        
}}
