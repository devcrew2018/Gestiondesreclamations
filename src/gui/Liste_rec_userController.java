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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class Liste_rec_userController implements Initializable {
 
    @FXML
    private AnchorPane liste;
    @FXML
    private TableView<Reclamation> tab_sujets;
    @FXML
    private TextArea rec_texte;
    @FXML
    private JFXButton supp_rec;
    @FXML
    private JFXButton retour_inter3;
    @FXML
    private TableColumn<Reclamation, String> sujet;
    @FXML
    private JFXButton afficher_rec;
       private  Reclamation v =new Reclamation();
  public static int i;
    @FXML
    private JFXButton refresh;
    @FXML
    private JFXButton modif_rec_user;
    @FXML
    private JFXTextArea rep_user;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private JFXButton valid_modif_user;
    @FXML
    private Label lab3;
    @FXML
    private Label remarque;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rec_texte.setVisible(false);
        rec_texte.setEditable(false);
        lab1.setVisible(false);
        lab2.setVisible(false);
        lab3.setVisible(false);
        rep_user.setVisible(false);
        rep_user.setEditable(false);
        valid_modif_user.setVisible(false);
        valid_modif_user.setDisable(true);
        remarque.setVisible(false);
                
        Reclamationservice ps=new Reclamationservice();
        try {
            ArrayList<Reclamation> reclamations = (ArrayList <Reclamation>) ps.getMyReclamations();
            ObservableList obs= FXCollections.observableArrayList(reclamations);
            tab_sujets.setItems(obs);
            sujet.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
            
        } catch (SQLException ex) {
            Logger.getLogger(Liste_rec_userController.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
    }    

    @FXML
    private void supp_rec_signal(ActionEvent event) {
            supp_rec.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                       Reclamation r=  tab_sujets.getSelectionModel().getSelectedItem();
       if (r==null){
           remarque.setVisible(true);
           remarque.setText("selectionner une réclamation!");
       }
       else{
         Reclamationservice m=new Reclamationservice();
         int b=r.getRec_id();
               
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Etes vous sure de vouloir supprimer cet equipement  ?");
                         Optional<ButtonType> result = alert.showAndWait();
                         ButtonType button = result.orElse(ButtonType.CANCEL);

                       if (button == ButtonType.OK) {
                             alert.setTitle("Notification");
                             alert.setContentText("Equipement supprimé avec succés");
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
                             alerta.setContentText("ECHEC de la suppression");
                             alerta.showAndWait();
                         }           
             }
            }
    });          
       
    }

    @FXML
    private void retour_inter3_signal(ActionEvent event) {
        
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

    @FXML
     private void afficher_rec_signal(ActionEvent event) {
               Reclamation r=  tab_sujets.getSelectionModel().getSelectedItem();
       if (r==null){
           remarque.setVisible(true);
           remarque.setText("selectionner une réclamation!");
       }
       else{
        rec_texte.setVisible(true);
        rec_texte.setEditable(false);
        lab1.setVisible(true);
        lab2.setVisible(true);
        lab3.setVisible(false);
        rep_user.setVisible(true);
        rep_user.setEditable(false);
        valid_modif_user.setVisible(false);
        valid_modif_user.setDisable(true);
        remarque.setVisible(false);
//        Reclamation r=  tab_sujets.getSelectionModel().getSelectedItem();
        rec_texte.setText(r.getRec_text());
        String b=r.getRec_reponse();
     rep_user.setText(r.getRec_reponse());
    }
     }
    @FXML
    private void refresh_signal(ActionEvent event) {
         rec_texte.setVisible(false);
         rec_texte.setEditable(false);
         lab1.setVisible(false);
         lab2.setVisible(false);
         lab3.setVisible(false);
         rep_user.setVisible(false);
         rep_user.setEditable(false);
          valid_modif_user.setVisible(false);
        valid_modif_user.setDisable(true);
        remarque.setVisible(false);
           Reclamationservice ps=new Reclamationservice();
        try {
            ArrayList<Reclamation> reclamations = (ArrayList <Reclamation>) ps.getMyReclamations();
            ObservableList obs= FXCollections.observableArrayList(reclamations);
            tab_sujets.setItems(obs);
            sujet.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
            rec_texte.setText("");
            rep_user.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Liste_rec_userController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void modif_rec_user_signal(ActionEvent event) throws SQLException {
       Reclamation r=  tab_sujets.getSelectionModel().getSelectedItem();
       if (r==null){
           remarque.setVisible(true);
           remarque.setText("selectionner une réclamation!");
       }
       else{
            remarque.setVisible(false);
            remarque.setText("Vouz ne pouvez pas modifier un réclamation déjà traité");
       rec_texte.setText(r.getRec_text());
       rep_user.setText(r.getRec_reponse());
        if(r.getEtat()==1)
        {
             remarque.setVisible(false);
         rec_texte.setVisible(true);
         rec_texte.setEditable(false);
         lab1.setVisible(true);
          lab2.setVisible(true);
         lab3.setVisible(false);
         rep_user.setVisible(true);
         rep_user.setEditable(false);
          valid_modif_user.setVisible(false);
        valid_modif_user.setDisable(true);
        remarque.setVisible(true);
        }
        else{
         remarque.setVisible(false);
         rec_texte.setVisible(true);
         rec_texte.setEditable(true);
         lab1.setVisible(false);
          lab2.setVisible(false);
         lab3.setVisible(true);
         rep_user.setVisible(false);
         rep_user.setEditable(false);
        valid_modif_user.setVisible(true);
        valid_modif_user.setDisable(false);
        }
       }
    }

    @FXML
    private void valid_modif_user_signal(ActionEvent event) throws SQLException{
        if (rec_texte.getText().equals("")) {remarque.setVisible(true);
        remarque.setText("Veuillez remplir Votre réclamation"); }
        else{
        Reclamation r=  tab_sujets.getSelectionModel().getSelectedItem();
        Reclamationservice rs =new Reclamationservice() ;
        int a=r.getRec_id();
        remarque.setVisible(true);
        remarque.setText("modification terminé");
        String b=rec_texte.getText();
        try {
         rs.modifierReclamationU(a,b);
        }catch (SQLException ex) {
            Logger.getLogger(Liste_rec_userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
