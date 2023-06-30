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
public class Vente {
    private int id_vente;
    private String date_vente;
    private int id_user;
    private int id_produit;
    private int qte;
    
    private Vente (){
    
    }
    
    public Vente(int id_vente , String date_vente,int id_user,int id_produit,int qte){
    
        this.id_vente=id_vente;
        this.date_vente=date_vente;
        this.id_user=id_user;
        this.id_produit=id_produit;
        this.qte=qte;
    }

    public int getId_vente() {
        return id_vente;
    }

    public void setId_vente(int id_vente) {
        this.id_vente = id_vente;
    }

    public String getDate_vente() {
        return date_vente;
    }

    public void setDate_vente(String date_vente) {
        this.date_vente = date_vente;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Vente{" + "id_vente=" + id_vente + ", date_vente=" + date_vente + ", id_user=" + id_user + ", id_produit=" + id_produit + ", qte=" + qte + '}';
    }
    
}
