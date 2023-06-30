/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entité.Produit;
import entité.Panier;
import entité.Vente;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import service.VenteService;
import service.PanierService;

import GUI.MenuVenteController ; 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 * FXML Controller class
 *
 * @author balouti.melek
 */
public class MenuVchequeController implements Initializable {
  private Connection connexion;
    private PreparedStatement ste;
     private PreparedStatement st;
    private ResultSet rs , r;
    @FXML
    private Button BACK;
    @FXML
    private AnchorPane IDcheque;
    @FXML
    private Line line;
    @FXML
    private TableColumn<Panier, String> P_NAME;
    @FXML
    private TableColumn<Panier, Float> PRIX;
    @FXML
    private TextField somme;
    @FXML
    private ImageView imp;
    @FXML
    private TableView<Panier> TVSOMME;

    /**
     * Initializes the controller class.
     */
    PanierService ps = new PanierService();
    private final ObservableList<Panier> list = FXCollections.observableArrayList(ps.readAll());
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        showvente() ; 
    }    

    @FXML
    private void BACK(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuVente.fxml"));
        Parent root=loader.load();
        BACK.getScene().setRoot(root);
    }

    @FXML
    private void imp(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {
     
          Document doc = new Document();
          String requete = "select * from panier ";
    
                  connexion = DataSource.getInstance().getCnx();

          PdfWriter.getInstance(doc, new FileOutputStream("E:\\PROJET\\vente.pdf"));
          doc.open();
          doc.add(new Paragraph("WELCOME TO                                   BEST PHARMACI "));
          doc.add(new Paragraph("*****************************************************************************************************                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   "));
          doc.add(new Paragraph("Paiement Par Chèque"));
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
          
          doc.add(new Paragraph("                                                                                                                                                                                MERCI POUR VOTRE VESIT"));
          doc.add(new Paragraph("                                                                                                               Num=71899000"));

doc.close();
Desktop.getDesktop().open(new File("E:\\PROJET\\vente.pdf") );
showvente() ;
      } catch (SQLException ex) {
          Logger.getLogger(MenuVchequeController.class.getName()).log(Level.SEVERE, null, ex);
      }
//vider la table
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
    P_NAME.setCellValueFactory(new PropertyValueFactory<Panier, String>("nomp_prd"));
    PRIX.setCellValueFactory(new PropertyValueFactory<Panier, Float>("prix"));
    TVSOMME.setItems(list);
    }
    }
    
    

