package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.org.apache.xml.internal.serializer.utils.AttList;

public class Insert_into
{
   Connection c = null;
   Statement stmt;

   public Insert_into()
   {

      try
      {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection(
               "jdbc:postgresql://localhost:5432/postgres", "postgres", "0774");

         System.out.println("Database open ok");

      }
      catch (Exception e)
      {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Database update ok");
   }

   public void addUser(String UserId, String UserName, String UserAlias)
   {
      stmt = null;
      try
      {
         stmt = c.createStatement();
         String user = "INSERT INTO \"Game\".user " + "VALUES (" + UserId
               + ", '" + UserName + "', '" + UserAlias + "')";
         stmt.executeUpdate(user);
         stmt.close();
         c.close();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
         System.out.println("bs");
      }

   }

   public ArrayList<String> showUser()
   {  c = null;
   stmt = null;
 
  
    try
   {
       Class.forName("org.postgresql.Driver");
      c = DriverManager
          .getConnection("jdbc:postgresql://localhost:5432/postgres",
          "postgres", "0774");
      stmt = c.createStatement();
       c.setAutoCommit(false);
   }
   catch (SQLException e1)
   {
      // TODO Auto-generated catch block
      e1.printStackTrace();
   }
   catch (ClassNotFoundException e)
   {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   
    System.out.println("Opened database successfully");

    
      
      ArrayList<String>result=new ArrayList<String>();
     
      ResultSet rs;
      String test ;
      try
      {
         rs = stmt.executeQuery("SELECT * FROM \"Game\".user;");
         while (rs.next())
         {
            String id = rs.getString("ID")+" ";
            String name = rs.getString("NAME")+" ";
            String alias = rs.getString("ALIAS");
            test= id+name+alias;
           result.add(test);
           System.out.println(test);
         }
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;

   }
}
