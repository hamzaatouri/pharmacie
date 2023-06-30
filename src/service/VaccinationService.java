/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author hzaat
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import entité.Vaccination;

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
public class VaccinationService implements IsService<Vaccination>{
    
     private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public VaccinationService(){
    
        connexion = DataSource.getInstance().getCnx();
    }
     
 @Override
    public void insert(Vaccination v) {
        
        String requete="insert into vaccination(numero_dose,date_dose,id_patient) values('"+v.getNuemro_dose()+"','"+v.getDate_dose()+"','"+v.getId_patient()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(VaccinationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public List readAll() {

         String requet="select * from vaccination";
        List<Vaccination> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Vaccination v = new Vaccination(rs.getInt("id_vaccin"),rs.getInt(2),rs.getString("date_dose"),rs.getInt("id_patient"));
               list.add(v);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(VaccinationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }

    @Override
    public Vaccination readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        String requet = "DELETE FROM patient WHERE id_vaccin=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VaccinationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Vaccination v) {
        
         String requet = "UPDATE patient SET numero_dose = ? , " + "date_dose = ? , " +"id_patient = ? " + "WHERE id_vaccin = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setInt(1,v.getNuemro_dose());
            pst.setString(2,v.getDate_dose() );
            pst.setInt(3,v.getId_patient());
            pst.setInt(4, v.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VaccinationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   




    

    
    

   
    
}

