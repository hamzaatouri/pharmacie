/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hzaat
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<?, ?> Statistics_Chart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    
    
    
     Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private ImageView back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series set = new  XYChart.Series();
        set.getData().add(new XYChart.Data("positif",getpositif()));
        set.getData().add(new XYChart.Data("negatif",getnegatif()));
        Statistics_Chart.getData().addAll(set);
        
    }   
    
    public int getpositif(){
        int resultatpos=0;
        String requete = "select count(*) from epeuve where resultat like 'true'";
        try {
            
            con = DataSource.getInstance().getCnx();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(requete);
            rs.next();
            resultatpos = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultatpos;
    }
    
        public int getnegatif(){
            int resultatneg=0;
            String requete = "select count(*) from epeuve where resultat like 'false'";
        try {
            
            con = DataSource.getInstance().getCnx();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(requete);
            rs.next();
            resultatneg = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultatneg;
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPharmacien.fxml"));
        Parent root=loader.load();
        back.getScene().setRoot(root);
    }
    
}
