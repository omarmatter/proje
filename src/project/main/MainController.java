/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.main;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LapCity
 */
public class MainController implements Initializable {

    @FXML
    private HBox bookinfo;
    @FXML
    private HBox memberinfo;
    @FXML
    private Text bookname;
    @FXML
    private Text author;
    @FXML
    private Text membername;
    @FXML
    private Text contact;
    @FXML
    private Text stutas;

    Statement statement;
    @FXML
    private TextField id;
    @FXML
    private TextField memberid;
    @FXML
    private JFXTextField idinfo;
    @FXML
    private ListView<String> view;
    @FXML
   
    private JFXHamburger humb;
    @FXML
    private JFXDrawer drawer;
    @FXML
    public AnchorPane pane1;
    VBox vbox;
    @FXML
    private AnchorPane hide;
    @FXML
    private AnchorPane omar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              ini();
           
        try {
            vbox = FXMLLoader.load(getClass().getResource("side.fxml"));
        
            drawer.setSidePane(vbox);
            for (Node node : vbox.getChildren()) {
                
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

                        switch (node.getAccessibleText()) {
                              case "home":
                           
                                 first();
                         
                                break;
                            case "addbook":
                            
                                loadAddBook();
                            
                                break;
                            case "addmember":
                                 loadAddMember();
                  
                                    break;
                            case "viwewbook":
                                
                               
                                loadDisplayBook();
                          
                                    break;
                            case "viwemember":
                                
                                loadDisplayMember();
                          
                                    break;
                            case "expir":
                                
                                expirehandel();
                         
                                     break;
                            case "all":
                                
                              
                                showborrow();
                           

                        break;
                            case "exit":
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Log out");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want  exit?" );

            Optional<ButtonType> x = alert.showAndWait();
            if (x.get() == ButtonType.OK) {
               
                                System.exit(0);
                        }
                        }
                    });
               

            }
            }
         } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

            HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(humb);
            transition.setRate(-1);
          
            humb.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                if (drawer.isOpened()) {
                    
                    drawer.close();
              
                     
                } else {

                    drawer.open();
                }
             
            });
              
            // TODO
     
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");

            this.statement = connection.createStatement();
            // TODO
        } catch (Exception ex) {
            System.out.println(ex);
        }
     

        // TODO
            
    }
    private  void ini(){
         pane1.getChildren().clear();
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/project/firsr/first.fxml"));
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          private void loadAddMember()   {
        pane1.getChildren().clear();
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/project/add_member/add_member.fxml"));
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAddBook()   {
        pane1.getChildren().clear();
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/project/add/add.fxml"));
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDisplayMember()  {
        pane1.getChildren().clear();
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/project/display_member/display_member.fxml"));
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDisplayBook()  {
        pane1.getChildren().clear();
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/project/disblaybook/disblaybook.fxml"));
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void expirehandel(){

        pane1.getChildren().clear();
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/project/viewexpire/viewexpire.fxml"));
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     private void first(){
         pane1.getChildren().clear();
          Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/project/firsr/first.fxml"));
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }

    private void showborrow()  {
        pane1.getChildren().clear();
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/viewallborrow/viewallborrow.fxml"));
            
            pane1.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     


}
