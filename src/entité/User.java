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
public class User {
    
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private int tel;
    private boolean Isadmin;
    private String password;
    private String email;
    
    public User(){
    
    }
    
    public User(int id , String nom , String prenom , String adresse ,int tel ,boolean Isadmin , String password){
    
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.tel=tel;
        this.Isadmin=Isadmin;
        this.password=password;
    
    }
    
        public User( String nom , String prenom , String adresse ,int tel , String password , boolean Isadmin){
    
        
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.tel=tel;
        this.password=password;
        this.Isadmin=Isadmin;
        
    
    }
        
                public User(  int id, String nom , String prenom , String adresse ,int tel ){
    
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.tel=tel;
        
    
    }

    public User(int id, String nom, String prenom, String adresse, int tel, boolean Isadmin, String password, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.Isadmin = Isadmin;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = password;
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

    public boolean getIsadmin() {
        return Isadmin;
    }

    public void setIsadmin(boolean Isadmin) {
        this.Isadmin = Isadmin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", Isadmin=" + Isadmin + '}';
    }
    
    
}

