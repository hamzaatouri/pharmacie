/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entité.Commande;
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
public class CommandeService implements IsService<Commande> {
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public CommandeService(){
    
        connexion = DataSource.getInstance().getCnx();
    }
    

    @Override
    public void insert(Commande c) {
        String requete="insert into commande(date_commande,qte_commande,id_fournisseur) values('"+c.getDate_comm()+"','"+c.getQte()+"','"+c.getId_fournisseur()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List readAll() {
         String requet="select * from commande";
        List<Commande> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Commande c = new Commande(rs.getInt("id_commande"),rs.getString(2),rs.getInt("qte_commande"),rs.getInt("id_fournisseur"));
               list.add(c);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }

    @Override
    public Commande readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
               String requet = "DELETE FROM commande WHERE id_commande=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Commande c) {
         String requet = "UPDATE commande SET date_commande = ? , " + "qte_commande = ? , " + "id_fournisseur = ?  "  + "WHERE id_commande = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,c.getDate_comm());
            pst.setInt(2,c.getQte() );
            pst.setInt(3,c.getId_fournisseur());
            pst.setInt(4,c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
