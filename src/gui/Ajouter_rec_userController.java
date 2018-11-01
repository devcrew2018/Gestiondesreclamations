/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import workshopdb.entities.CurrentUser;
import workshopdb.entities.Reclamation;
import workshopdb.services.Reclamationservice;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class Ajouter_rec_userController implements Initializable {

    @FXML
    private AnchorPane a_r_user;
    @FXML
    private JFXTextArea recl;
    @FXML
    private JFXComboBox<String> sujet;
    @FXML
    private JFXButton confirm;
    @FXML
    private JFXButton retour_inter2;
    @FXML
    private JFXTextField resultat_ajout;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultat_ajout.setVisible(false);
        resultat_ajout.setEditable(false);
        sujet.getItems().addAll("Objet Perdue","Problème d'équipement ","Problème de service","Problème dans le magasin","Problème dans la vestiaire","Autres sujet");
        sujet.getSelectionModel().select(5);
    }    

    @FXML
    private void confirmer_r(ActionEvent event) {
      // CurrentUser.id=18;
      if (recl.getText().equals("")) {resultat_ajout.setVisible(true);
        resultat_ajout.setText("Veuillez remplir Votre réclamation"); }
      else{
        resultat_ajout.setVisible(true);
        resultat_ajout.setEditable(false);
        resultat_ajout.setText("Votre réclamation est envoyée avec succés!"); 
        Reclamationservice rs =new Reclamationservice() ;
        Reclamation r =new Reclamation(sujet.getSelectionModel().getSelectedItem(), CurrentUser.id,recl.getText()) ;
        try {
            rs.ajouterReclamationPs(r);
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_rec_userController.class.getName()).log(Level.SEVERE, null, ex);     
              }       
    }
    }

    @FXML
    private void retour_inter2_signal(ActionEvent event) {
       try {
     FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("recl_user.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("New Window");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
       
    }
    
}
