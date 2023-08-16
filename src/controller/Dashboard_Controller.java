package controller;

import DAO.DAO_Dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.Dashboard_Modal;
import View.DashboardView;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Dashboard_Controller implements ActionListener {
    private final Dashboard_Modal mod;
    private final DashboardView stupage;
    private DAO_Dashboard modDAO;
    private JTable jtable1;

    public Dashboard_Controller(Dashboard_Modal mod, DAO_Dashboard modDAO, DashboardView stupage) {
        this.mod = mod;
        this.jtable1 = stupage.jtable1;
        this.stupage = stupage;
        this.modDAO = modDAO;

        this.stupage.add_btn.addActionListener(this);
        this.stupage.btnupdate.addActionListener(this);
        this.stupage.btnSearch.addActionListener(this);
        this.stupage.btn_refresh.addActionListener(this);
    }

    public void start() {
        stupage.setTitle("Package");
        stupage.setLocationRelativeTo(null);
        refreshTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stupage.add_btn) {
            if (validateFields()) {
                mod.setPackage_name(stupage.packagename_txt.getText());
                mod.setdestination(stupage.destination_txt.getText());
                mod.setguide(stupage.guide_txt.getText());
                mod.setpackage_details(stupage.packagedetails_txt.getText());
                mod.setpackage_no(Integer.parseInt(stupage.packageno_txt.getText()));

                if (modDAO.add(mod)) {
                    JOptionPane.showMessageDialog(null, "Data added successfully");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Data could not be added");
                }
            }
        }


        if (e.getSource() == stupage.btnupdate) {
            if (validateFields()) {
                mod.setpackage_no(Integer.parseInt(stupage.packageno_txt.getText()));
                mod.setPackage_name(stupage.packagename_txt.getText());
                mod.setdestination(stupage.destination_txt.getText());
                mod.setguide(stupage.guide_txt.getText());
                mod.setpackage_details(stupage.packagedetails_txt.getText());

                if (modDAO.update(mod)) {
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Error in Updating");
                }
            }
        }
       
        // ... Existing code ...


   

        if (e.getSource() == stupage.btnSearch) {
            if (!stupage.txtID.getText().isEmpty()) {
                mod.setPackage_name(stupage.txtID.getText());
                if (modDAO.search(mod)) {
                    stupage.packagename_txt.setText(mod.getPackage_name());
                    stupage.destination_txt.setText(mod.getdestination());
                    stupage.guide_txt.setText(mod.getguide());
                    stupage.packagedetails_txt.setText(mod.getpackage_details());
                    stupage.packageno_txt.setText(Integer.toString(mod.getpackage_no()));
                } else {
                    JOptionPane.showMessageDialog(null, "No Record Found");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a package name for search");
            }
        }

        if (e.getSource() == stupage.btn_refresh) {
            refreshTable();
        }
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
        model.setRowCount(0); // Clear existing table data

        List<Dashboard_Modal> students = modDAO.getAllStudents();

        for (Dashboard_Modal student : students) {
            Object[] row = {
                    student.getPackage_name(),
                    student.getdestination(),
                    student.getguide(),
                    student.getpackage_details(),
                    student.getpackage_no()
            };
            model.addRow(row);
        }
    }

    private boolean validateFields() {
        if (stupage.packagename_txt.getText().isEmpty() || stupage.destination_txt.getText().isEmpty()
                || stupage.guide_txt.getText().isEmpty() || stupage.packagedetails_txt.getText().isEmpty()
                || stupage.packageno_txt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields.");
            return false;
        }

        try {
            Integer.parseInt(stupage.packageno_txt.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Package Number. Please enter a valid number.");
            return false;
        }

        return true;
    }
}
