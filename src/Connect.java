import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Connect {
 
 static Connection conn = null;
 static String databaseName = "agency";
 static String url = "jdbc:mysql://127.0.0.1:3306/agency?serverTimezone=UTC" ; 
 
 static String username = "root";
 static String password = "123456";   //password
 
 
 public static Connection main(String args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
  
Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
  
conn = DriverManager.getConnection(url, username,password);
return conn;}
 }
  
