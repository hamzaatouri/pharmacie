/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import entité.Vente;
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
public class VenteService implements IsService<Vente>  {

    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public VenteService(){
    
        connexion = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Vente v) {
        
    String requete="insert into vente(date_vente,id_produit,qte_produit) values('"+v.getDate_vente()+"','"+v.getId_produit()+"','"+v.getQte()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<Vente> readAll() {
         String requet="select * from vente";
        List<Vente> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               Vente v = new Vente (rs.getInt("id_vente"),rs.getString(2),rs.getInt("id_user"),rs.getInt("id_produit"),rs.getInt("qte_produit"));
               list.add(v);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }

    

    @Override
    public void delete(int id) {
                    String requet = "DELETE FROM user WHERE id_vente=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Vente v) {
        
                 String requet = "UPDATE vente SET id_produit = ? , " + "qte_produit = ? " + "WHERE id_vente = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setInt(1,v.getId_produit());
            pst.setInt(2,v.getQte() );
            pst.setInt(3,v.getId_produit());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Vente readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
