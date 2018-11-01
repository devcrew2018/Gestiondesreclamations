/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import workshopdb.entities.CurrentUser;
import workshopdb.entities.Reclamation;
import workshopdb.services.Reclamationservice;
import java.sql.Connection;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class Liste_recl_gController implements Initializable {

    @FXML
    private AnchorPane liste_rec_g;
    @FXML
    private TableView<Reclamation> tab_g;
    @FXML
    private TableColumn<Reclamation, Integer> exp;
    @FXML
    private TableColumn<Reclamation, String> lib;
    @FXML
    private TableColumn<Reclamation, String> date;
    @FXML
    private TableColumn<Reclamation, String> text;
    @FXML
    private TableColumn<Reclamation, Integer> etat;
    @FXML
    private JFXTextArea rep;
    @FXML
    private JFXButton refresh_g;
    @FXML
    private JFXButton traiter_rec;
    @FXML
    private JFXButton supprimer_rec_g;
    @FXML
    private JFXButton retour_g_inter2;
    @FXML
    private TableColumn<Reclamation, String> reponse;
   
    @FXML
    private JFXButton confirm_tr;
    @FXML
    private Label lab3;
    @FXML
    private Label remarque_remp;
    @FXML
    private Label select_red;
    @FXML
    private Label deja;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rep.setVisible(false);
        rep.setEditable(false);
        confirm_tr.setVisible(false);
        confirm_tr.setDisable(true);
        lab3.setVisible(false);
        remarque_remp.setVisible(false);
        select_red.setVisible(false);
        deja.setVisible(false);
        
              Reclamationservice ps=new Reclamationservice();
        try {
            ArrayList<Reclamation> reclamations = (ArrayList <Reclamation>) ps.getAllReclamations();
            ObservableList obs= FXCollections.observableArrayList(reclamations);
            tab_g.setItems(obs);
            exp.setCellValueFactory(new PropertyValueFactory<>("rec_expediteur"));
            lib.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
            date.setCellValueFactory(new PropertyValueFactory<>("rec_date"));
            text.setCellValueFactory(new PropertyValueFactory<>("rec_text"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
             reponse.setCellValueFactory(new PropertyValueFactory<>("rec_reponse"));
            
 
        } catch (SQLException ex) {
            Logger.getLogger(Liste_recl_gController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    

    @FXML
    private void refresh_g_signal(ActionEvent event) {
        rep.setVisible(false);
        rep.setEditable(false);
         confirm_tr.setVisible(false);
        confirm_tr.setDisable(true);
         lab3.setVisible(false);
         remarque_remp.setVisible(false);
         select_red.setVisible(false);
         deja.setVisible(false);
        
        
             Reclamationservice ps=new Reclamationservice();
        try {
            ArrayList<Reclamation> reclamations = (ArrayList <Reclamation>) ps.getAllReclamations();
            ObservableList obs= FXCollections.observableArrayList(reclamations);
            tab_g.setItems(obs);
            exp.setCellValueFactory(new PropertyValueFactory<>("rec_expediteur"));
            lib.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
            date.setCellValueFactory(new PropertyValueFactory<>("rec_date"));
            text.setCellValueFactory(new PropertyValueFactory<>("rec_text"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            reponse.setCellValueFactory(new PropertyValueFactory<>("rec_reponse"));
 
        } catch (SQLException ex) {
            Logger.getLogger(Liste_recl_gController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void traiter_rec_signal(ActionEvent event) throws SQLException {
       Connection connexion = null;
          
          Reclamation r= tab_g.getSelectionModel().getSelectedItem();  
          if (r==null){
              deja.setVisible(false);
           select_red.setVisible(true);
       }
       else
          {
              if(r.getEtat()==1)
              {
                  deja.setVisible(true);
                  select_red.setVisible(false);
                  rep.setVisible(false);
        rep.setEditable(false);
        confirm_tr.setVisible(false);
        confirm_tr.setDisable(true);
         lab3.setVisible(false);
         remarque_remp.setVisible(false);
              }
              
              else{
              deja.setVisible(false);
       rep.setVisible(true);
        rep.setEditable(true);
        confirm_tr.setVisible(true);
        confirm_tr.setDisable(false);
         lab3.setVisible(false);
         remarque_remp.setVisible(false);
         select_red.setVisible(false);
          }}
    }
    
    @FXML
    private void supprimer_rec_g_signal(ActionEvent event) {
          
            supprimer_rec_g.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {  
                     rep.setVisible(false);
        rep.setEditable(false);
        confirm_tr.setVisible(false);
        confirm_tr.setDisable(true);
         lab3.setVisible(false);
         remarque_remp.setVisible(false);
         select_red.setVisible(false);
         deja.setVisible(false);
        
                   Reclamation r= tab_g.getSelectionModel().getSelectedItem();  
          if (r==null){
           select_red.setVisible(true);
       }        
                   else{
               select_red.setVisible(false);
         Reclamationservice m=new Reclamationservice();
         int b=r.getRec_id();           
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);        
                         alert.setTitle("Notification");
                         alert.setContentText("Etes vous sure de vouloir supprimer cette réclamation  ?");
                         Optional<ButtonType> result = alert.showAndWait();
                         ButtonType button = result.orElse(ButtonType.CANCEL);
                         
                       if (button == ButtonType.OK) {
                             alert.setTitle("Notification");
                             alert.setContentText("Réclamation supprimée avec succés");
                             alert.showAndWait();
                      try {                 
                          ArrayList<Reclamation> aa =(ArrayList<Reclamation>) m.getAllReclamations();
                          for (int i=0;i<aa.size();i++){
                           
                          if (b==aa.get(i).getRec_id() ){
                         
                              aa.get(i).setRec_reponse(r.getRec_reponse());}
                              
                          m.ajouterReclamationH(aa.get(i));}
                          
                          
                      } catch (SQLException ex) {
                          Logger.getLogger(Liste_rec_userController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                                                     
                      try {
                          m.supprimerReclamation(b);
                      } catch (SQLException ex) {
                          Logger.getLogger(Liste_rec_userController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      } 

                          else {
                             Alert alerta = new Alert(Alert.AlertType.ERROR);
                             
                             alerta.setTitle("Notification");
                             alerta.setContentText("Suppression annulée");
                             alerta.showAndWait();
                         }                   

            }
             }
    });
        
        
    }

    @FXML
    private void retour_g_inter2_siganal(ActionEvent event) { 
              try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("recl_gerant.fxml"));
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

    @FXML
    private void confirm_tr_signal(ActionEvent event) {
        select_red.setVisible(false);
         Reclamation r= tab_g.getSelectionModel().getSelectedItem();  
         if (rep.getText().isEmpty()){
             remarque_remp.setVisible(true);
         }
         else
         {
             remarque_remp.setVisible(false);
             Reclamationservice rs =new Reclamationservice() ;
        try {
                rs.modifierReclamation(r.getRec_id(),rep.getText());
                 lab3.setVisible(true);       
        } catch (SQLException ex) {
            Logger.getLogger(Liste_recl_gController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    }   
}
