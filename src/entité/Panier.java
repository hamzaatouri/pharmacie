/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

/**
 *
 * @author balouti.melek
 */
public class Panier {
    private String nomp_prd ; 
    private Float prix ; 
    
    public Panier(){
    
    } 

    public Panier(String nomp_prd, Float prix) {
        this.nomp_prd = nomp_prd;
        this.prix = prix;
    }

    public String getNomp_prd() {
        return nomp_prd;
    }

    public Float getPrix() {
        return prix;
    }

    public void setNomp_prd(String nomp_prd) {
        this.nomp_prd = nomp_prd;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
    
        return "Panier{" + "nomp_prd=" + nomp_prd + ", prix=" + prix + '}';
        
    }

    public Panier(String nomp_prd) {
        this.nomp_prd = nomp_prd;
    }

    public Panier(Float prix) {
        this.prix = prix;
    }
    
    
    
    
    
}
