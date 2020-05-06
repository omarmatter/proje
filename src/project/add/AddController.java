/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LapCity
 */
public class AddController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
  
    @FXML
    private Button save;
        Statement statement;
    @FXML
    private JFXDatePicker data;
   
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project","root","");
            
            this.statement = connection.createStatement();
            // TODO
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }    
    @FXML
    private void saveHandel(ActionEvent event){
        System.out.println(data.getValue());
        try {
        if(id.getText().equals("")||title.getText().equals("")|| author.getText().equals("")||data.getValue()==null){
                Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("full data");
            alert.showAndWait();
            
        }else{
            int ID = Integer.parseInt(id.getText());
            String Title = title.getText();
            String Author = author.getText();
            String  Publisher =  data.getValue()+"";
            String sql = "Insert Into databook(id,title,author,publisher,isavail) values(" + ID + ",'" +Title + "','"
                    + Author + "','" + Publisher +"','" + 1 + "')";
            this.statement.executeUpdate(sql);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("ADD sucsess");
           
                    ImageView imageView =new ImageView("/project/icon/k.gif");
                    alert.setGraphic(imageView);
                    alert.getGraphic().setScaleX(1);
                     alert.getGraphic().setScaleY(1);
            alert.showAndWait();
            reset();
                 }
        } catch (SQLException ex) {
             Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Erorr");
            alert.showAndWait();
           
        }
      
    }

    
    private  void reset(){
        id.setText("");
          title.setText("");
        author.setText("");
      

    }
}
