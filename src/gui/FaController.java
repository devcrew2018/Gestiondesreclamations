/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import workshopdb.entities.Reclamation;
import workshopdb.services.Reclamationservice;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class FaController implements Initializable {

    @FXML
    private TableView<Reclamation> tab;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation, String> lib;
    @FXML
    private TableColumn<Reclamation, Integer> exp;
    @FXML
    private TableColumn<Reclamation, String> date;
    @FXML
    private TableColumn<Reclamation, String> text;
    @FXML
    private Button afficher;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         afficher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                Reclamationservice ps=new Reclamationservice();
        try {
            ArrayList<Reclamation> reclamations = (ArrayList <Reclamation>) ps.getAllReclamations();
            ObservableList obs= FXCollections.observableArrayList(reclamations);
            tab.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("rec_id"));
            lib.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
            exp.setCellValueFactory(new PropertyValueFactory<>("rec_expediteur"));
            date.setCellValueFactory(new PropertyValueFactory<>("rec_date"));
            text.setCellValueFactory(new PropertyValueFactory<>("rec_text"));
        } catch (SQLException ex) {
            Logger.getLogger(FaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    
    

                 }  );   
}
}

