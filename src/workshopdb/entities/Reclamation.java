/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopdb.entities;

/**
 *
 * @author Amine
 */
public class Reclamation {
    private int rec_id;
    private String rec_lib;
    private int rec_expediteur;
//    private int rec_destinataire;
    private String rec_date;
    private String rec_text;

    @Override
    public String toString() {
        return "Reclamation{" + "rec_id=" + rec_id + ", rec_lib=" + rec_lib + ", rec_expediteur=" + rec_expediteur + ", rec_date=" + rec_date + ", rec_text=" + rec_text + '}';
    }
    
    
     
   
     
    public Reclamation(int rec_id , String rec_lib , int rec_expediteur  , String rec_date , String rec_text)
    {
            this.rec_id=rec_id;
            this.rec_lib=rec_lib;
            this.rec_expediteur=rec_expediteur;
            this.rec_date=rec_date;
            this.rec_text=rec_text;
    }

    public int getRec_id() {
        return rec_id;
    }

    public String getRec_lib() {
        return rec_lib;
    }

    public int getRec_expediteur() {
        return rec_expediteur;
    }


    public String getRec_date() {
        return rec_date;
    }

    public String getRec_text() {
        return rec_text;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public void setRec_lib(String rec_lib) {
        this.rec_lib = rec_lib;
    }

    public void setRec_expediteur(int rec_expediteur) {
        this.rec_expediteur = rec_expediteur;
    }


    public void setRec_date(String rec_date) {
        this.rec_date = rec_date;
    }

    public void setRec_text(String rec_text) {
        this.rec_text = rec_text;
    }
    
     
}
