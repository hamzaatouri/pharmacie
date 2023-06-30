/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entité.Panier;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.PanierService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author balouti.melek
 */
public class MenuVenteCarteController implements Initializable {
 private Connection connexion;
    private PreparedStatement ste;
     private PreparedStatement st;
    private ResultSet rs , r;
    @FXML
    private TableColumn<Panier, String > P_name;
    @FXML
    private TableColumn<Panier, Float> prix2;
    @FXML
    private Button IDBACK;
    @FXML
    private TextField somme;
    @FXML
    private ImageView imp;
    private TableView<Panier> TVSOMME;
    @FXML
    private TableView<Panier> TVSOMMEE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showvente();
        // TODO
    }    

    @FXML
    private void BACK2(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuVente.fxml"));
        Parent root=loader.load();
        IDBACK.getScene().setRoot(root);
    }

    @FXML
    private void imp(MouseEvent event) throws IOException, DocumentException {
               Document doc = new Document();
          String requete = "select * from panier ";
    
                  connexion = DataSource.getInstance().getCnx();

          
          PdfWriter.getInstance(doc, new FileOutputStream("E:\\PROJET\\vente.pdf"));
          doc.open();
          doc.add(new Paragraph("WELCOME TO                                   BEST PHARMACI "));
          doc.add(new Paragraph("*****************************************************************************************************                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   "));
          doc.add(new Paragraph(" Paiement Par Carte Bancaire                                                                                                              Num=71899000"));
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          
          doc.add(new Paragraph(""));
          
          PdfPTable table= new PdfPTable(2);
          table.setWidthPercentage(100);
          PdfPCell cell ;
          
          
          cell = new PdfPCell(new Phrase("nom_produit",FontFactory.getFont("Comic sans MS", 12))) ;
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.GREEN);
          table.addCell(cell) ;
          cell = new PdfPCell(new Phrase("prix",FontFactory.getFont("Comic sans MS", 12))) ;
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.GREEN);
          table.addCell(cell) ;
          /////////////////////////////////////////
          try {
          ste=connexion.prepareStatement(requete);
          rs= ste.executeQuery();
          
          while(rs.next()){
              cell = new PdfPCell(new Phrase(rs.getString("name_prd").toString(),FontFactory.getFont("Arial", 10))) ;
              cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell.setBackgroundColor(BaseColor.DARK_GRAY);
              table.addCell(cell) ;

              cell = new PdfPCell(new Phrase(rs.getString("prix").toString(),FontFactory.getFont("Arial", 12))) ;
              cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell.setBackgroundColor(BaseColor.DARK_GRAY);
              table.addCell(cell) ;

        }
          
         doc.add(table)  ; 
          
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          doc.add(new Paragraph(""));
          
          doc.add(new Paragraph("MERCI POUR VOTRE VESIT"));
          
          
//Image img  = new Image.getInstance("E:\\Utilisateur\\melek.allouti\\bureau\\PROJET\\v.png");
//doc.add(img) ;

//FileInputStream in =new FileInputStream("E:\\Utilisateur\\melek.allouti\\bureau\\PROJET\\v.png");

doc.close();
Desktop.getDesktop().open(new File("E:\\PROJET\\vente.pdf") );
      } catch (SQLException ex) {
          Logger.getLogger(MenuVchequeController.class.getName()).log(Level.SEVERE, null, ex);
      }
//vider la table
//string sql = delete all from panier ;
   String requet = "TRUNCATE TABLE panier";
            
            Connection connection = DataSource.getInstance().getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement(requet);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
       public void showvente(){
    
   PanierService ps = new PanierService();
    ps.readAll();
    ObservableList<Panier> list = FXCollections.observableArrayList(ps.readAll());
    P_name.setCellValueFactory(new PropertyValueFactory<Panier, String>("nomp_prd"));
    prix2.setCellValueFactory(new PropertyValueFactory<Panier, Float>("prix"));
  TVSOMMEE.setItems(list);
    }
}


