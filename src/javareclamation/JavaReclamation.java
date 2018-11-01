/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javareclamation;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import workshopdb.entities.Reclamation;
import workshopdb.services.Reclamationservice;
import workshopdb.utils.MyDB;


/**
 *
 * @author Amine
 */
public class JavaReclamation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyDB database = MyDB.getInstance();
        Connection connexion = MyDB.getInstance().getConnection();
        Reclamation r=new Reclamation(23,"voiture  tres mal garée",1,"lbera7","ba3ed carhebtefffffffffffffffk");
        Reclamation r1=new Reclamation(8,"voiture  garée",9,"lbgchjera7","ba3ed ");
        System.out.println(r);
        Reclamationservice reclamationservice = new Reclamationservice();


       try {
            //productservice.ajouterProduitPs(p);
            reclamationservice.ajouterReclamation(r);
//            reclamationservice.ajouterReclamationPs(r1);
            //reclamationservice.supprimerReclamation(3);
           reclamationservice.modifierReclamation(2,"sabri",5,"e","fa");
            //System.out.println("insert completed");
        } catch (SQLException ex) {
            Logger.getLogger(JavaReclamation.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        try {
            for (Reclamation r2 : reclamationservice.getAllReclamations()) {
                System.out.println(r2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JavaReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
