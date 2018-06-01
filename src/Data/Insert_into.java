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
            String user1 = "INSERT INTO \"Game\".user " + "VALUES (1, 'Alex D', 'Popeye')";
            String user2 = "INSERT INTO \"Game\".user " + "VALUES (2, 'Alex z', 'Pluto')" ;
            String user3= "INSERT INTO \"Game\".user " + "VALUES (3, 'Mihai B', 'MickeyMouse')" ;
            String gamestat1 = "INSERT INTO \"Game\".gamestat " + "VALUES (1, 30, 30, '100%','10hours')";
            String gamestat2 = "INSERT INTO \"Game\".gamestat " + "VALUES (2, 27, 54, '50%','15hours')";
            String gamestat3 = "INSERT INTO \"Game\".gamestat " + "VALUES (3, 3, 30, '10%','8hours')";
            stmt.executeUpdate(gamestat3);

        
      
            stmt.close();
            c.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
          }
          System.out.println("Database update ok");
        }
   }
   