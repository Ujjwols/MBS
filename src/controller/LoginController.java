/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Connection;
import Model.*;
import View.*;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class LoginController {
    LoginView logview;
    LoginModel logmod;
    ResultSet rs;
    Statement stmt;
    
    public LoginController(LoginView logview){
        this.logview=logview;
        logview.addLoginListener(new LoginListener());
    }
    class LoginListener implements ActionListener{
        
       
        @Override
        public void actionPerformed(ActionEvent e){
            
            try{
                logmod=logview.getuser();   
                if(checkUser(logmod)){ 
                    logview.setMessage("login successfully");
                    DashboardView d=  new DashboardView();
                    d.show();
                    logview.dispose();
                }
                else{
                    logview.setMessage("invalid credentials");
                    
                }
                           
        
            }
            catch(Exception e1){
                System.out.println(e1.getMessage());
                
            }
        }
         public boolean checkUser(LoginModel user) throws Exception{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info","root","aryan999@#");
            String query="select * from users where username='"+user.getUsername()+"' AND password='"+user.getPassword()+"'";
            try{
                stmt=conn.createStatement();
                rs=stmt.executeQuery(query);
                if (rs.next()){
                    return true;
                }
                conn.close();
                
            }
            catch(Exception e2){
                System.out.println(e2.getMessage());
                
            }
           return false;
         }




    }
    


    
}
