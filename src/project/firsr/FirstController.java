/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.firsr;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class FirstController implements Initializable {

    @FXML
 //   private AnchorPane pane1;
 //   @FXML
    private AnchorPane omar;
    @FXML
    private HBox bookinfo;
    @FXML
    private TextField id;
    @FXML
    private Text bookname;
    @FXML
    private Text author;
    @FXML
    private Text stutas;
    @FXML
    private HBox memberinfo;
    @FXML
    private TextField memberid;
    @FXML
    private Text membername;
    @FXML
    private Text contact;
    @FXML
    private JFXTextField idinfo;
  Statement statement;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Label title;
    @FXML
    private Label author2;
    @FXML
    private Label dataeborrow;
    @FXML
    private Label datrreturne;
    @FXML
    private Label id2;
    @FXML
    private Label name2;
    @FXML
    private Label mobile2;
    @FXML
    private Label id1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");

            this.statement = connection.createStatement();
            // TODO
        } catch (Exception ex) {
            System.out.println(ex);
        }
        JFXDepthManager.setDepth(bookinfo, 20);
        JFXDepthManager.setDepth(memberinfo, 20);
        
        
        // TODO
    }    

    @FXML
    private void showinfobook(ActionEvent event) throws SQLException {
             clearbook();
        boolean found = false;
          String Id = this.id.getText();

        ResultSet rs = this.statement.executeQuery("Select * From databook where   id=" + Id);

        while (rs.next()) {
            String name = rs.getString("title");
            String Author = rs.getString("author");
            boolean av = rs.getBoolean("isavail");

            bookname.setText(name);
            author.setText(Author);
            String stu = (av) ? "availabel" : "No available";
            stutas.setText(stu);
            found = true;
        }
        if (!found) {
            bookname.setText("No Such Book Avilable");
        }

    }

    


    @FXML
    private void showinfomember(ActionEvent event) throws SQLException {
            clearmember();
        boolean found = false;
        String Id = this.memberid.getText();
        ResultSet rs = this.statement.executeQuery("Select * From datamember where id =" + Id);

        while (rs.next()) {
            String name = rs.getString("name");
            String con = rs.getString("mobile");

            membername.setText(name);

            contact.setText(con);
            found = true;
        }
        if (!found) {
            membername.setText("No Such Member Avilable");
        }
    }

     public void clearbook() {
        bookname.setText("");
        author.setText("");
        stutas.setText("");
    }

    public void clearmember() {
        membername.setText("");
        contact.setText("");

    }

    @FXML
    private void borrowbook(ActionEvent event) {
         if (memberid.getText().equals("") || id.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Borrow");
            alert.setContentText("full data");
            alert.showAndWait();
        } else {

            int Memberid = Integer.parseInt(this.memberid.getText());
            int bookid = Integer.parseInt(id.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Borrow Operation ");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to Borrow the book " + bookname.getText() + " to " + membername.getText());

            Optional<ButtonType> x = alert.showAndWait();
            if (x.get() == ButtonType.OK) {
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime newtime = today.plusDays(0);
                String sql = "Insert Into borrow(bookid,memberid ,returntime) values(" + bookid + ",'" + Memberid + "','" + newtime + "')";
                String sgl = "UPDATE databook SET isavail =false WHERE id =" + bookid;
                try {
                    this.statement.executeUpdate(sql);
                    this.statement.executeUpdate(sgl);
                    Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
                    aler.setHeaderText(null);
                    aler.setContentText("borrow sucsess");
                   
                    ImageView imageView =new ImageView("/project/icon/k.gif");
                    aler.setGraphic(imageView);
                    aler.getGraphic().setScaleX(1);
                     aler.getGraphic().setScaleY(1);
                     aler.setHeight(.5);
                 
                    
                    aler.showAndWait();
                } catch (SQLException ex) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setHeaderText(null);
                    alert2.setContentText(" Borrow");
                    alert2.showAndWait();

                }

            }
        }
    
    }
     boolean sumb;
    @FXML
       
    private void loadinfobook(ActionEvent event) throws SQLException {
       sumb=false;
        rest();
          if(!idinfo.getText().equals("")){
              
        int ID = Integer.parseInt(idinfo.getText());
        String qu = "select * from borrow where bookid=" + ID;
        ResultSet rs;
        rs = this.statement.executeQuery(qu);
        while (rs.next()) {
            System.out.println("rrrr");
            int BOOKID = ID;
            Timestamp time = rs.getTimestamp("da");
            int MemberID = rs.getInt("memberid");
            System.out.println(time);
            Date ret = rs.getDate("returntime");
              
           dataeborrow.setText( time.toString());
            datrreturne.setText(ret.toString());
            System.out.println(year(ret.toString()));

            qu = "select * from databook where id =" + BOOKID;
            rs = this.statement.executeQuery(qu);

            while (rs.next()) {

               title.setText( rs.getString("title"));
               author2.setText(rs.getString("author"));
             id1.setText(BOOKID+"");
            }
            qu = "select * from datamember where id =" + MemberID;
            rs = this.statement.executeQuery(qu);
            while (rs.next()) {
              name2.setText(rs.getString("name"));
               id2.setText( rs.getString("id"));

               mobile2.setText( rs.getString("mobile"));
                sumb = true;

            }

        }
        }else{
            
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Submission");
            alert.setHeaderText(null);
            alert.setContentText("No found ID");
            alert.showAndWait();
        }

   
  
           
    }

    @FXML
    private void submissionHandel(ActionEvent event) {
          if (!sumb) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Submission");
            alert.setHeaderText(null);
            alert.setContentText("Enter ID");
             
                
            alert.showAndWait();
          
        }else{
        String ID = idinfo.getText();
        String qu = "Delete from borrow where Bookid=" + ID;
        String qu2 = "UPDATE databook SET isavail=true WHERE id=" + ID;
        try {
            this.statement.executeUpdate(qu);
            this.statement.executeUpdate(qu2);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Submission");
            alert.setHeaderText(null);
            alert.setContentText("Sucsess");
             
                    ImageView imageView =new ImageView("/project/icon/k.gif");
                    alert.setGraphic(imageView);
                    alert.getGraphic().setScaleX(1);
                     alert.getGraphic().setScaleY(1);
            alert.showAndWait();
              rest();
             
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Submission");
            alert.setHeaderText(null);
            alert.setContentText("Faild Submission");
            alert.showAndWait();
          

        }

    }}
    public  void rest(){
        dataeborrow.setText("Date borrow");
            datrreturne.setText("Date retune");
            
               title.setText("Title");
               author2.setText("Author");
             id1.setText("ID");
              name2.setText("Name");
               id2.setText("ID");

               mobile2.setText("Mobile");
    }
  
    public static String year( String Exp) {
        long calendar = Calendar.getInstance().getTimeInMillis();
         SimpleDateFormat sf = new SimpleDateFormat("yyy-mm-dd");
        Date sDt3 = null;
        try {
            sDt3 =  (Date) sf.parse(Exp);
        } catch (ParseException e) {
         
        }
        long ld3 = sDt3.getTime();
        System.out.println(calendar);
        return ((ld3 - calendar) / (1000 * 60 * 60 * 24)) + "";
    }
}
