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
    String firstname,lastname,username,contactno,password,confrimpassword;
    public RegistrationModel(String firstname,String lastname,String username,String contactno,String password,String confrimpassword)
    {
       
        this.firstname=firstname;
        this.lastname=lastname; 
        this.username=username;
        this.contactno=contactno;
        this.password=password;
        this.confrimpassword=confrimpassword;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfrimpassword() {
        return confrimpassword;
    }

    public void setConfrimpassword(String confrimpassword) {
        this.confrimpassword = confrimpassword;
    }

   
  
}
