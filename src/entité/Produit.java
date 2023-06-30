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
public class Produit {
    
    private int id;
    private String nom_prod;
    private String categorie;
    private String date_fabrication;
    private String date_expiration;
    private float prix;
    private int qte;
    
    public Produit(){
    
    }
    
    public Produit (int id ,String nom_prod,String categorie,String date_fabrication,String date_expiration,float prix,int qte){

        this.id=id;
        this.nom_prod=nom_prod;
        this.categorie=categorie;
        this.date_fabrication=date_fabrication;
        this.date_expiration=date_expiration;
        this.prix=prix;
        this.qte=qte;
        
    }

    public Produit(String nom_prod, String categorie, String date_fabrication, String date_expiration, float prix, int qte) {
        this.nom_prod = nom_prod;
        this.categorie = categorie;
        this.date_fabrication = date_fabrication;
        this.date_expiration = date_expiration;
        this.prix = prix;
        this.qte = qte;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate_fabrication() {
        return date_fabrication;
    }

    public void setDate_fabrication(String date_fabrication) {
        this.date_fabrication = date_fabrication;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom_prod=" + nom_prod + ", categorie=" + categorie + ", date_fabrication=" + date_fabrication + ", date_expiration=" + date_expiration + ", prix=" + prix + ", qte=" + qte + '}';
    }
    
}
