/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entité.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;


/**
 *
 * @author hzaat
 */
public class PersonneService implements IsService<Personne> {
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
    public PersonneService(){
    
        connexion = DataSource.getInstance().getCnx();
    }
    
    public void insert(Personne p){
    String requete="insert into personne(nom,prenom) values('"+p.getNom()+"','"+p.getPrenom()+"')";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public List<Personne> readAll(){
        String requet="select * from personne";
        List<Personne> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Personne p = new Personne(rs.getInt("id"),rs.getString(2),rs.getString("prenom"));
               list.add(p);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    
         }

    @Override
    public Personne readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
       
            String requet = "DELETE FROM personne WHERE id=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public void update(Personne p) {
         String requet = "UPDATE personne SET nom = ? , " + "prenom = ? " + "WHERE id = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,p.getNom());
            pst.setString(2,p.getPrenom() );
            pst.setInt(3,p.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}