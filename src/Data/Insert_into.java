package Data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class Insert_into {
   
   public static void main( String args[] )
        {
          Connection c = null;
          Statement stmt = null;
          try {
          Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection("jdbc:postgresql://localhost:5432/postgres",
                     "postgres", "0774");
        
            System.out.println("Database open ok");

            stmt = c.createStatement();
            String sql1 = "INSERT INTO \"Game\".user " + "VALUES (1, 'Alex D', 'Popeye')";
            String sql2 = "INSERT INTO \"Game\".user " + "VALUES (2, 'Alex z', 'Pluto')" ;
            String sql3 = "INSERT INTO \"Game\".user " + "VALUES (3, 'Mihai B', 'MickeyMouse')" ;
            stmt.executeUpdate(sql3);

        
      
            stmt.close();
            c.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
          }
          System.out.println("Database update ok");
        }
   }
   