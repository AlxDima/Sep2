package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Select_from {


public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "0774");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Game\".user;" );
         while ( rs.next() ) {
            String id = rs.getString("ID");
            String  name = rs.getString("NAME");
            String  alias = rs.getString("ALIAS");
            
            System.out.println();
            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + name );
            System.out.println( "ALIAS = " + alias );
            System.out.println( "=======================" );
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Database query ok ");
     }
}
