/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.viewexpire;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.disblaybook.databook;

/**
 * FXML Controller class
 *
 * @author Omar
 */







public class ViewexpireController implements Initializable {
 @FXML
    private TableColumn<dataexpire, Integer> tcmemberid;

    @FXML
    private TableColumn<dataexpire, Integer> tcbookid;

    @FXML
    private TableColumn<dataexpire, String> tcdateborrow;

    @FXML
    private TableColumn<dataexpire, String> tcemil;

    @FXML
    private TableColumn<dataexpire, String> tcmobile;
    @FXML
    private TableView<dataexpire> tableview;

       Statement statement;
   
   
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
             tcbookid.setCellValueFactory(new PropertyValueFactory("bookid"));
             tcmemberid.setCellValueFactory(new PropertyValueFactory("memberid"));
                tcdateborrow.setCellValueFactory(new PropertyValueFactory("date"));                
                   tcemil.setCellValueFactory(new PropertyValueFactory("email"));
                      tcmobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        try {
              Display();
        } catch (Exception ex) {
            System.out.println(ex);
        }
     
    }   
    
     private void Display() throws Exception{
     
      ResultSet rs = this.statement.executeQuery("Select *  FROM borrow,datamember where borrow.memberid=datamember.id");
        tableview.getItems().clear();
        while(rs.next()){
             String today =LocalDate.now().toString();
             String da=rs.getDate("returntime").toString();
             System.out.println();
             if(today.equals(da)){
            dataexpire  expire = new dataexpire();
            expire.setBookid(rs.getInt("bookid"));
            expire.setMemberid(rs.getInt("memberid"));
            expire.setDate(rs.getString("da"));
          expire.setEmail(rs.getString("email"));
           expire.setMobile(rs.getString("mobile"));
            tableview.getItems().add(expire);
             }
        
     }
      
        // TODO
    }    

}
