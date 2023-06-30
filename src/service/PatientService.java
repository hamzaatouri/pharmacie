/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entité.Patient;

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
public class PatientService implements IsService<Patient>{
    
     private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public PatientService(){
    
        connexion = DataSource.getInstance().getCnx();
    }
     
 @Override
    public void insert(Patient p) {
        
        String requete="insert into patient(patient_name,patient_surname,patient_adresse,patient_tel,patient_email,patient_birthdate) values('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getAdresse()+"','"+p.getTel()+"','"+p.getMail()+"','"+p.getDate_de_naissance()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public List readAll() {

         String requet="select * from patient";
        List<Patient> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Patient p = new Patient(rs.getInt("id_patient"),rs.getString(2),rs.getString("patient_surname"),rs.getString("patient_adresse"),rs.getInt("patient_tel"),rs.getString("patient_email"),rs.getString("patient_birthdate"));
               list.add(p);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }

    @Override
    public Patient readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        String requet = "DELETE FROM patient WHERE id_patient=?";
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
    public void update(Patient p) {
        
         String requet = "UPDATE patient SET patient_name = ? , " + "patient_surname = ?, "+"patient_adresse =?,"+"patient_tel = ?,"+"patient_email=?," +"patient_birthdate=?" + "WHERE id_patient = ?";
          String requet1="SET  FOREIGN_KEY_CHECKS=0";
          String requet2="SET  FOREIGN_KEY_CHECKS=1";
         Connection connection = DataSource.getInstance().getCnx();
        try {
            Statement stmt1 = connexion.createStatement();
            stmt1.execute(requet1);
           
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,p.getNom());
            pst.setString(2,p.getPrenom() );
            pst.setString(3,p.getAdresse() );
            pst.setInt(4,p.getTel() );
            pst.setString(5,p.getMail() );
            pst.setString(6,p.getDate_de_naissance() );
            pst.setInt(7,p.getId());
            pst.executeUpdate();
             Statement stmt2 = connexion.createStatement();
             stmt2.execute(requet2);
          
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   




    

    
    

   
    
}
