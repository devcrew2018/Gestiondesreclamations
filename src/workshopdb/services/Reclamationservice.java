/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopdb.services;

import workshopdb.entities.Reclamation;
import workshopdb.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amine
 */
public class Reclamationservice {
     Connection connexion;
    public Reclamationservice(){
        connexion=MyDB.getInstance().getConnection();
    }
   public void ajouterReclamation(Reclamation r)throws SQLException{
        String req="INSERT INTO `reclamation`(`id`,`lib`, `exp`,`dat`,`tex` ) VALUES ('"+r.getRec_id()+"','"+r.getRec_lib()+"','"+r.getRec_expediteur()+"','"+r.getRec_date()+"','"+r.getRec_text()+"')";
                Statement stm= connexion.createStatement();
                stm.executeUpdate(req);
    }
    
    public void ajouterReclamationPs(Reclamation r) throws SQLException{
        String req="INSERT INTO reclamation (`lib`,`exp`,`dat`,`tex`) values(?,?,?,?,?);";
        PreparedStatement ps=connexion.prepareStatement(req);
        //ps.setInt(1,p.getId());
        ps.setString(1,r.getRec_lib());
        ps.setInt(2,r.getRec_expediteur());
//        ps.setInt(3,r.getRec_destinataire());
        ps.setString(3,r.getRec_date());
        ps.setString(4,r.getRec_text());
        ps.executeUpdate();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Reclamation> getAllReclamations() throws SQLException
    {   
        List <Reclamation> reclamations= new ArrayList<>();
        String req="SELECT * FROM reclamation";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
            Reclamation r=new Reclamation(rst.getInt("id"),rst.getString("lib"),rst.getInt("exp"),rst.getString("dat"),rst.getString("tex"));
            reclamations.add(r);
        }
        return reclamations;
    } 
 
    public void supprimerReclamation(int a) throws SQLException
    {int nb;
            String req="delete from reclamation WHERE id="+a;
            Statement s=connexion.createStatement();
            nb=s.executeUpdate(req);
            if (nb==1)
                System.out.println(" lines are deleted"); 
            else 
                System.out.println("produit inexistant !");
               
            
    }
    
    public void modifierReclamation(int a,String b,int c,String e,String f) throws SQLException
    {
        String req = "UPDATE reclamation SET lib= '"+b+"' , exp= '"+c+"' , dat= '"+e+"' ,tex= '"+f+"' WHERE id= "+a;
        Statement s=connexion.createStatement();
        s.executeUpdate(req);
    }
           
}
