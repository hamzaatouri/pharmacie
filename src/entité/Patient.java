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
public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private int tel;
    private String mail;
    private String date_de_naissance;
    
    public Patient(){
    
    }
    
    public Patient (int id , String nom,String prenom,String adresse,int tel,String mail,String date_de_naissance){
    
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.tel=tel;
        this.mail=mail;
        this.date_de_naissance=date_de_naissance;
    }
    
    public Patient ( String nom,String prenom,String adresse,int tel,String mail,String date_de_naissance){
    
        
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.tel=tel;
        this.mail=mail;
        this.date_de_naissance=date_de_naissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", mail=" + mail + ", date_de_naissance=" + date_de_naissance + '}';
    }
    
    
}
