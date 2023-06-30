/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entité.Commande;
import entité.Fournisseur;
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
public class FournissuerService implements IsService<Fournisseur> {
    
      private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public FournissuerService(){
    
        connexion = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Fournisseur f) {
        
         String requete="insert into fournisseur(fournisseur_name,fournisseur_surname,fournisseur_adresse,fournisseur_tel,fournisseur_email) values('"+f.getNom()+"','"+f.getPrenom()+"','"+f.getAdresse()+"','"+f.getTel()+"','"+f.getMail()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(FournissuerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List readAll() {
String requet="select * from fournisseur";
        List<Fournisseur> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Fournisseur f = new Fournisseur(rs.getInt("id_fournisseur"),rs.getString(2),rs.getString("fournisseur_surname"),rs.getString("fournisseur_adresse"),rs.getInt("fournisseur_tel"),rs.getString("fournisseur_email"));
               list.add(f);
               
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;    }

    @Override
    public Fournisseur readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
             String requet = "DELETE FROM fournisseur WHERE id_fournisseur=?";
            String requet1="SET  FOREIGN_KEY_CHECKS=0";
            String requet2="SET  FOREIGN_KEY_CHECKS=1";
            Connection connection = DataSource.getInstance().getCnx();
        try {
             Statement stmt1 = connexion.createStatement();
            stmt1.execute(requet1);
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
            Statement stmt2 = connexion.createStatement();
            stmt2.execute(requet2);
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Fournisseur f) {
        
                 String requet = "UPDATE fournisseur SET fournisseur_name = ? , " + "fournisseur_surname = ? , " + "fournisseur_adresse = ? , " + "fournisseur_tel = ? , " + "fournisseur_email = ?  " + "WHERE id_fournisseur = ?";
         Connection connection = DataSource.getInstance().getCnx();
         String requet1="SET  FOREIGN_KEY_CHECKS=0";
          String requet2="SET  FOREIGN_KEY_CHECKS=1";
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
           Statement stmt1 = connexion.createStatement();
           stmt1.execute(requet1);
            ste = connexion.createStatement();
            pst.setString(1,f.getNom());
            pst.setString(2,f.getPrenom() );
            pst.setString(3,f.getAdresse());
            pst.setInt(4,f.getTel());
            pst.setString(5,f.getMail());
            pst.setInt(6,f.getId());
            pst.executeUpdate();
            Statement stmt2 = connexion.createStatement();
             stmt2.execute(requet2);
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
