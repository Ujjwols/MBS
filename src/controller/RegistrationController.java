/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ASUS
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import Model.*;
import View.*;
public class RegistrationController {
    RegistrationModel model;
    RegistrationView regview;
    PreparedStatement pst=null;
        public RegistrationController(RegistrationView regview)
        {
            this.regview=regview;
            
            regview.addLoginListner(new RegisetrListener());
        }
        
        class RegisetrListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                model=regview.getUser();
                if(checkUser(model))
                {
                    regview.setMessage("Registered Successfully");
                }
                else
                {
                    regview.setMessage("Invalid registration");
                    
                }
            }
            catch(Exception e1)
            {
                
            }

        }
       
        public boolean checkUser(RegistrationModel user) throws Exception {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvcprac", "root", "iphone53g@");

        
        String checkUsernameQuery = "SELECT * FROM users WHERE username = ?";
        pst = conn.prepareStatement(checkUsernameQuery);
        pst.setString(1, user.getUsername());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            
            JOptionPane.showMessageDialog(null, "Username is already taken");
            return false;
        }
        
            if (user.getFirstname().isEmpty() || user.getLastname().isEmpty() ||
            user.getUsername().isEmpty() || user.getContactno().isEmpty() ||
            user.getPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return false;
        }
        try {
            int Contactno= Integer.parseInt(user.getContactno());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Contactno must be an integer");
            return false;
        }


        
        String insertQuery = "INSERT INTO users(first_name, last_name, username, contact_no, password) VALUES (?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(insertQuery);
        pst.setString(1, user.getFirstname());
        pst.setString(2, user.getLastname());
        pst.setString(3, user.getUsername());
        pst.setString(4, user.getContactno());
        pst.setString(5, user.getPassword());

        pst.executeUpdate();
        System.out.println("Data inserted");
        JOptionPane.showMessageDialog(null, "Data Registered Successfully");
        return true;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        throw e; // Rethrow the exception to be caught by the calling code
    } finally {
        if (pst != null) {
            pst.close();
        }
    }
}        
    }
}