/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionbd1cinfo2;

import entité.Patient;
import entité.Personne;
import entité.User;
import static java.lang.Boolean.TRUE;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.PatientService;
import service.PersonneService;
import service.UserService;
import utils.DataSource;


/**
 *
 * @author hzaat
 */
public class Test  {


        public static void main(String[] args) {
        // TODO code application logic here
       // DataSource ds1 = DataSource.getInstance();

       
   //User u=new User("hamza","zaatouri","ariana",71558946,"aze",true);
   //User u2=new User("ha","zaa","ariana",71558946);
   //User u3 = new User ("wassim","zaa","ariana",71558946);
   UserService us=new UserService();
   //us.insert(u2);
   us.readAll().forEach(System.out::println);
  // us.delete(2);
   //us.update(u2,2);
   
   Patient p = new Patient("melek","ahmed","ariana",17889663,"hzaatouri@gmail.com","1990-10-24");
   Patient p2 = new Patient("me","ah","ariana",17889663,"hzaatouri@gmail.com","1990-10-24");

   PatientService pt=new PatientService();
  // pt.insert(p);
   //pt.insert(p2);
   //pt.insert(p);
   //pt.readAll().forEach(System.out::println);
   //pt.delete(1);
   //pt.update(p2,2);
   
   
   
      
    }
    
}
