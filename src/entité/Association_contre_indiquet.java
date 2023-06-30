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
public class Association_contre_indiquet {
    private int id_combinaison;
    private String nom_comb;
    private String med1;
    private String med2;
    
    
    public Association_contre_indiquet (){
    
    }
    
    public Association_contre_indiquet (int id_combinaison ,String nom_comb,String med1 ,String med2){
    
        this.id_combinaison=id_combinaison;
        this.nom_comb=nom_comb;
        this.med1=med1;
        this.med2=med2;
    }

    public Association_contre_indiquet(String nom_comb, String med1, String med2) {
        this.nom_comb = nom_comb;
        this.med1 = med1;
        this.med2 = med2;
    }

    public int getId_combinaison() {
        return id_combinaison;
    }

    public void setId_combinaison(int id_combinaison) {
        this.id_combinaison = id_combinaison;
    }

    public String getNom_comb() {
        return nom_comb;
    }

    public void setNom_comb(String nom_comb) {
        this.nom_comb = nom_comb;
    }

    public String getMed1() {
        return med1;
    }

    public void setMed1(String med1) {
        this.med1 = med1;
    }

    public String getMed2() {
        return med2;
    }

    public void setMed2(String med2) {
        this.med2 = med2;
    }
    
    

   

    @Override
    public String toString() {
        return "Association_contre_indiquet{" + "id_combinaison=" + id_combinaison + ", nom_comb=" + nom_comb + ", med1=" + med1 + ", med2=" + med2 + '}';
    }
    
    
}
