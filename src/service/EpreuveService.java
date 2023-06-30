/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entité.Epreuve;

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
public class EpreuveService implements IsService<Epreuve>  {

    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public EpreuveService(){
    
        connexion = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Epreuve e) {
        
    String requete="insert into epeuve(resultat,date_epreuve,id_patient) values('"+e.getResultat()+"','"+e.getDate_epreuve()+"','"+e.getId_patient()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(EpreuveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<Epreuve> readAll() {
         String requet="select * from epeuve";
        List<Epreuve> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Epreuve e = new Epreuve(rs.getInt("id_epreuve"),rs.getBoolean(2),rs.getString("date_epreuve"),rs.getInt("id_patient"));
               list.add(e);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(EpreuveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }

    

    @Override
    public void delete(int id) {
                    String requet = "DELETE FROM user WHERE id_epreuve=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EpreuveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Epreuve e) {
        
                 String requet = "UPDATE epreuve SET resultat = ?  " + "WHERE id_epreuve = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setBoolean(1,e.getResultat());
            pst.setInt(2,e.getId_epreuve());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EpreuveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public  Epreuve readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
