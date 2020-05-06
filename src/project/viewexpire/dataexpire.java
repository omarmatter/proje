/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.viewexpire;

/**
 *
 * @author Omar
 */
public class dataexpire {
int memberid;
int bookid;
String date;
String mobile;
String email;

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

//      @Override
//    public String toString() {
//        return String.format("%-5s %-10s %-10s %-5s %-5s", bookid, memberid, date, email,mobile);
//    }

    @Override
    public String toString() {
        return "dataexpire{" + "memberid=" + memberid + ", bookid=" + bookid + ", date=" + date + ", mobile=" + mobile + ", email=" + email + '}';
    }
     

}
