package kata55;

//Autor: José Vicente Cabañas

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Kata5 {

    public static void main( String args[] ) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
    
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/josevicentecabanas/Documents/Proyectos Netbeans/Kata5VJ/lib/Mail");
      System.out.println("Opened database successfully");
      
      //Class.forName("oracle.jdbc.driver.OracleDriver");
      //Connection c = DriverManager.getConnection("jdbc:oracle:thin:@10.22.143.90:1521:orcl","system","orcl");
      
      Statement stmt = c.createStatement();
      String fileName="/Users/josevicentecabanas/Desktop/Mail.txt";
      BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
      
      String mail;
      
      ResultSet rs = stmt.executeQuery("SELECT * FROM PEOPLE");
      
      while ((mail=reader.readLine()) != null) {
          System.out.println(mail);
          String query = "INSERT INTO MAIL (MAIL) VALUES ('" + mail + "')";
          stmt.executeUpdate(query);
      }
            
      while (rs.next()){
          System.out.println("ID = " + rs.getInt("ID"));
          System.out.println("NAME = " + rs.getString("NAME"));
      }
      
      rs.close();
      stmt.close();
      c.close();
      
  }
}