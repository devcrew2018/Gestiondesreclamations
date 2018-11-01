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
public class CurrentUser {
    
    public static int id;
    public static String mail,nom;

//    public  int getId() {
//        return id;
//    }
//
//    public  String getMail() {
//        return mail;
//    }
//
//   String getNom() {
//        return nom;
//    }

    public  void setId(int id) {
        CurrentUser.id = id;
    }

    public  void setMail(String mail) {
        CurrentUser.mail = mail;
    }

    public  void setNom(String nom) {
        CurrentUser.nom = nom;
    }
    
    
    
    
    
}
