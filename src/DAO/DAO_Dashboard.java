package DAO;

import Database.DbConnection;
import Model.Dashboard_Modal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO_Dashboard extends DbConnection {

    public boolean add(Dashboard_Modal mod) {
        PreparedStatement ps = null;
        Connection conn = dbConnect();

        String sql = "INSERT INTO customer(package_name, destination, guide, packagedetails, package_no) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mod.getPackage_name());
            ps.setString(2, mod.getdestination());
            ps.setString(3, mod.getguide());
            ps.setString(4, mod.getpackage_details());
            ps.setInt(5, mod.getpackage_no());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            closeResources(conn, ps);
        }
    }

    public boolean update(Dashboard_Modal mod) {
        PreparedStatement ps = null;
        Connection conn = dbConnect();

        String sql = "UPDATE customer SET package_name = ?, destination = ?, guide = ?, packagedetails = ? WHERE package_no = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mod.getPackage_name());
            ps.setString(2, mod.getdestination());
            ps.setString(3, mod.getguide());
            ps.setString(4, mod.getpackage_details());
            ps.setInt(5, mod.getpackage_no());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            closeResources(conn, ps);
        }
    }








    public boolean search(Dashboard_Modal mod) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = dbConnect();

        String sql = "SELECT * FROM customer WHERE package_name = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mod.getPackage_name());
            rs = ps.executeQuery();

            if (rs.next()) {
                mod.setPackage_name(rs.getString("package_name"));
                mod.setdestination(rs.getString("destination"));
                mod.setguide(rs.getString("guide"));
                mod.setpackage_details(rs.getString("packagedetails"));
                mod.setpackage_no(rs.getInt("package_no"));

                return true;
            }

            return false;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public List<Dashboard_Modal> getAllStudents() {
        List<Dashboard_Modal> students = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = dbConnect();

        String sql = "SELECT * FROM customer";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Dashboard_Modal student = new Dashboard_Modal();
                student.setPackage_name(rs.getString("package_name"));
                student.setdestination(rs.getString("destination"));
                student.setguide(rs.getString("guide"));
                student.setpackage_details(rs.getString("packagedetails"));
                student.setpackage_no(rs.getInt("package_no"));
                students.add(student);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            closeResources(conn, ps, rs);
        }

        return students;
    }

    private void closeResources(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        closeResources(conn, ps);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
