/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import workshopdb.services.SendingMail;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class Envoyer_mailController implements Initializable {

    @FXML
    private AnchorPane mail_g;
    @FXML
    private JFXButton retour_m;
    @FXML
    private JFXButton envoyer_m;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField mail_text;
    @FXML
    private Label lab1;
    @FXML
    private Label rem;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lab1.setVisible(false);
        rem.setVisible(false); 
    }    

    @FXML
    private void envoyer_m_mail(ActionEvent event) {
        if ((mail_text.getText().isEmpty())|| (titre.getText().isEmpty()))
        {
            rem.setVisible(true);   
        }
        else
        {
        rem.setVisible(false); 
        SendingMail ma=new SendingMail(mail_text.getText(),"mohamedamine.khemiri@esprit.tn",titre.getText());
        SendingMail.envoyer();
        lab1.setVisible(true);
    }
    }

    @FXML
    private void retour_m_signal(ActionEvent event) {
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
    
}
