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
public class Vaccination {
    
    private int id;
    private int nuemro_dose;
    private String date_dose;
    private int id_patient;
    
    
    public Vaccination(){
    
    }
    
    public Vaccination ( int id ,int numero_dose,String date_dose,int id_patient){
    
    this.id=id;
    this.nuemro_dose=numero_dose;
    this.date_dose=date_dose;
    this.id_patient=id_patient;
    
    }
    
    public Vaccination ( int numero_dose,String date_dose,int id_patient){
    
    
    this.nuemro_dose=numero_dose;
    this.date_dose=date_dose;
    this.id_patient=id_patient;
    
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNuemro_dose() {
        return nuemro_dose;
    }

    public void setNuemro_dose(int nuemro_dose) {
        this.nuemro_dose = nuemro_dose;
    }

    public String getDate_dose() {
        return date_dose;
    }

    public void setDate_dose(String date_dose) {
        this.date_dose = date_dose;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "Vaccination{" + "id=" + id + ", nuemro_dose=" + nuemro_dose + ", date_dose=" + date_dose + ", id_patient=" + id_patient + '}';
    }
    
    
}
