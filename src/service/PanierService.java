/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entité.Panier;
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
 * @author balouti.melek
 */
public class PanierService implements IsService<Panier> {
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
     public PanierService(){
    
        connexion = DataSource.getInstance().getCnx();
    }


    @Override
    public void insert(Panier t) {
      
    String requete="insert into panier(name_prd,prix) values('"+t.getNomp_prd()+"','"+t.getPrix()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
        
        
            

    @Override
    public List<Panier> readAll() {
    String requet="select * from panier";
        List<Panier> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Panier p = new Panier(rs.getString(1),rs.getFloat(2));
               list.add(p);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;    }

   
    public void delete(String nomp_prd) {
          String requet = "DELETE FROM user WHERE nomp_prd=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setString(1,nomp_prd);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    

    @Override
    public void update(Panier t) {

                 String requet = "UPDATE user SET nomp_prd = ? , " + "orix = ? " + "WHERE nomp_prd = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,t.getNomp_prd());
           // pst.setInt(2,T.getprix() );
            
           // pst.setInt(3,t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Panier readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }
    

