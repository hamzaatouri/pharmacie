/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


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
public class UserService implements IsService<User>  {

    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public UserService(){
    
        connexion = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(User u) {
        
    String requete="insert into user(user_name,password,user_surname,user_adresse,user_tel,is_admin) values('"+u.getNom()+"','"+u.getPassword()+"','"+u.getPrenom()+"','"+u.getAdresse()+"','"+u.getTel()+"','"+u.getIsadmin()+"')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<User> readAll() {
         String requet="select * from user";
        List<User> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
           rs= ste.executeQuery(requet);
           while(rs.next()){
               User u = new User(rs.getInt("id_user"),rs.getString(2),rs.getString("user_surname"),rs.getString("user_adresse"),rs.getInt("user_tel"),rs.getBoolean("is_admin"),rs.getString("password"));
               list.add(u);
           }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list;
    }

    

    @Override
    public void delete(int id) {
                    String requet = "DELETE FROM user WHERE id_user=?";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User u) {
        
                 String requet = "UPDATE user SET user_name = ? , " + "user_surname = ? , " +"user_adresse = ? ," + "user_tel = ? ," +"is_admin=? "+ "WHERE id_user = ?";
         Connection connection = DataSource.getInstance().getCnx();
        try {
           PreparedStatement pst = connection.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom() );
            pst.setString(3,u.getAdresse() );
            pst.setInt(4,u.getTel() );
            pst.setBoolean(5,u.getIsadmin() );
            pst.setInt(6,u.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
