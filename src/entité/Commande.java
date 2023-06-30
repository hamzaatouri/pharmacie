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
public class Commande {
    
    private int id;
    private String date_comm;
    private int qte;
    private int id_fournisseur;
    
    public Commande(){
    
    }
    public Commande (int id , String date_comm , int qte , int id_fournisseur){
    
        this.id=id;
        this.date_comm=date_comm;
        this.qte=qte;
        this.id_fournisseur=id_fournisseur;
    }
    
        public Commande ( String date_comm , int qte , int id_fournisseur){
    
       
        this.date_comm=date_comm;
        this.qte=qte;
        this.id_fournisseur=id_fournisseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_comm() {
        return date_comm;
    }

    public void setDate_comm(String date_comm) {
        this.date_comm = date_comm;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date_comm=" + date_comm + ", qte=" + qte + ", id_fournisseur=" + id_fournisseur + '}';
    }
    
    
}
