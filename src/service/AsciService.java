/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entité.Association_contre_indiquet;
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
public class AsciService implements IsService<Association_contre_indiquet> {
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public AsciService(){
    
        connexion = DataSource.getInstance().getCnx();
    }

   
   @Override
    public void insert(Association_contre_indiquet p) {
        String requete="insert into asci(comb_name,med_name1,med_name2) values('"+p.getNom_comb()+"','"+p.getMed1()+"','"+p.getMed2()+"')" ;

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public List <Association_contre_indiquet> readAll() {
String requet="select * from asci";
        List<Association_contre_indiquet> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Association_contre_indiquet p = new Association_contre_indiquet(rs.getInt("id_combinaison"),rs.getString("comb_name"),rs.getString("med_name1"),rs.getString("med_name2"));
               list.add(p);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;    
    
    }
     @Override
    public Association_contre_indiquet readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        String requet = "DELETE FROM asci WHERE id_combinaison=?";
            
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
    public void update(Association_contre_indiquet p) {
         String requet = "UPDATE asci SET comb_name = ? , " + "med_name1 = ? , "+ "med_name2 = ? "+"WHERE id_combinaison  = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,p.getNom_comb());
            pst.setString(2,p.getMed1());
            pst.setString(3,p.getMed2());
            pst.setInt(4,p.getId_combinaison());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
}
