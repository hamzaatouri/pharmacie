/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entité.Produit;
import entité.User;
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
public class ProduitService implements IsService<Produit> {
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public ProduitService(){
    
        connexion = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Produit p) {
        String requete="insert into produit(produit_name,catégorie,date_fabrication,date_expiration,prix,qte_stock) values('"+p.getNom_prod()+"','"+p.getCategorie()+"','"+p.getDate_fabrication()+"','"+p.getDate_expiration()+"','"+p.getPrix()+"','"+p.getQte()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produit> readAll() {
String requet="select * from produit";
        List<Produit> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Produit p = new Produit(rs.getInt("id_produit"),rs.getString(2),rs.getString("catégorie"),rs.getString("date_fabrication"),rs.getString("date_expiration"),rs.getFloat("prix"),rs.getInt("qte_stock"));
               list.add(p);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;    }

    @Override
    public Produit readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        String requet = "DELETE FROM user WHERE id_produit=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Produit p) {
         String requet = "UPDATE produit SET produit_name = ? , " + "catégorie = ? , "+ "date_fabrication = ? , "+ "date_expiration = ? , "+ "prix = ? , "+ "qte_stock = ?  " + "WHERE id_produit = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,p.getNom_prod());
            pst.setString(2,p.getCategorie());
            pst.setString(3,p.getDate_fabrication());
            pst.setString(4,p.getDate_expiration());
            pst.setFloat(5,p.getPrix());
            pst.setInt(6,p.getQte());
            pst.setInt(7,p.getId());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
