/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author Zevil
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
     public static Connection dbConnect(){
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tour","root","aryan999@#");
            System.out.println("connected");
            return conn;

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }

}
