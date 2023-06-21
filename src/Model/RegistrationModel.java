/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */
public class RegistrationModel {
    String username,password,firstname,lastname,contactno,confrimpassword;
    public RegistrationModel(String username,String password,String firstname,String lastname,String contactno,String confrimpassword)
    {
        this.username=username;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname; 
        this.contactno=contactno;
        this.confrimpassword=confrimpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
    public String getConfrimpassword(){
        return confrimpassword;
    }
    public void setConfrimpassword(String confrimpassword){
        this.confrimpassword=confrimpassword;
    }
  
}
