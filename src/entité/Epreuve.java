/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

/**
 *
 * @author hzaat
 */
public class Epreuve {
    private int id_epreuve;
    private boolean resultat;
    private String date_epreuve;
    private int id_patient;
    
    public Epreuve(){
    
    }
    
    public Epreuve(int id_epreuve,boolean resultat,String date_epreuve,int id_patient){
    
        this.id_epreuve=id_epreuve;
        this.resultat=resultat;
        this.date_epreuve=date_epreuve;
        this.id_patient=id_patient;
    }
    
     public Epreuve(boolean resultat,String date_epreuve,int id_patient){
    
        this.id_epreuve=id_epreuve;
        this.resultat=resultat;
        this.date_epreuve=date_epreuve;
        this.id_patient=id_patient;
    }

    public int getId_epreuve() {
        return id_epreuve;
    }

    public void setId_epreuve(int id_epreuve) {
        this.id_epreuve = id_epreuve;
    }

    public boolean getResultat() {
        return resultat;
    }

    public void setResultat(boolean resultat) {
        this.resultat = resultat;
    }

    public String getDate_epreuve() {
        return date_epreuve;
    }

    public void setDate_epreuve(String date_epreuve) {
        this.date_epreuve = date_epreuve;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "Epreuve{" + "id_epreuve=" + id_epreuve + ", resultat=" + resultat + ", date_epreuve=" + date_epreuve + ", id_patient=" + id_patient + '}';
    }
    
}
