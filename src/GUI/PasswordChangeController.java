/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entité.User;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Random;
import javax.swing.JOptionPane;
import utils.DataSource;
/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class PasswordChangeController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button sendpassword;

    Random random = new Random();   
    int y = random.nextInt(1000); 
    private Connection connexion;
    private Statement ste;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendpassword(ActionEvent event) throws MessagingException {
        
            sendmail(email.getText());
           
            String requet = "UPDATE user SET password=?" + "WHERE email = ?";
             connexion = DataSource.getInstance().getCnx();
            try{
            PreparedStatement pst = connexion.prepareStatement(requet);
            ste = connexion.createStatement();
            pst.setString(1,String.valueOf(y));
            pst.setString(2,email.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PasswordChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
             JOptionPane.showMessageDialog(null, "Nouvelle mot de passe envoyé , verifiez votre email");
    }
    
    public  void sendmail(String recepient) throws MessagingException {
    
        Properties properties = new Properties();
                
   properties.put("mail.smtp.auth","true");
   properties.put("mail.smtp.starttls.enable","true");
   properties.put("mail.smtp.host","smtp.gmail.com");
   properties.put("mail.smtp.port","587");
   
   String MyAccountEmail = "hzaatouri@gmail.com";
   String Password = "ucantseeme";
    
   
    Session session = Session.getInstance(properties , new Authenticator() {
            
     @Override
     protected PasswordAuthentication getPasswordAuthentication(){
     
     return new PasswordAuthentication(MyAccountEmail, Password);
     }       
            
});
    
    Message message = prepareMessage(session,MyAccountEmail,recepient);
            
          Transport.send(message);
    }
    
    private  Message prepareMessage(Session session ,String MyAccountEmail,String recepient){
    
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(MyAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            msg.setSubject("NEW PASSWORD");
            msg.setText(String.valueOf(y));
            return msg;
            
           
        } catch (Exception ex) {
            Logger.getLogger(MailSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return null;
    }
    
}
