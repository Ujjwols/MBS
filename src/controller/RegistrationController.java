package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Model.RegistrationModel;
import View.RegistrationView;

public class RegistrationController {
    private RegistrationModel model;
    private RegistrationView view;

    public RegistrationController(RegistrationView view) {
        this.view = view;
        view.addRegisterListner(new RegisterListener());
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model = view.getUser();
                if (checkUser(model)) {
                    view.setMessage("Registered Successfully");
                } else {
                    view.setMessage("Invalid registration");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public boolean checkUser(RegistrationModel user) throws Exception {
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root",
                        "21013002zevils");

                String checkUsername = "SELECT * FROM users WHERE username=?";
                pst = conn.prepareStatement(checkUsername);
                pst.setString(1, user.getUsername());
                rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username has already been used");
                    return false;
                }

                if (user.getFirstname().isEmpty() || user.getLastname().isEmpty() || user.getUsername().isEmpty()
                        || user.getContactno().isEmpty() || user.getPassword().isEmpty()
                        || user.getConfrimpassword().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return false;
                }

                if (!user.getPassword().equals(user.getConfrimpassword())) {
                    JOptionPane.showMessageDialog(null, "Password and confirm password must match");
                    return false;
                }

                try {
                    long contactno = Long.parseLong(user.getContactno());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Contact must be a long integer");
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
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }
}
