package Data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GameStatistic {
   
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
            String sql = "CREATE TABLE \"Game\".gamestat " +
                         "(GameSessionId       INT PRIMARY KEY     NOT NULL," +
                         " Player1       CHAR(15)    NOT NULL," +
                         " Player2       CHAR(15)   NOT NULL," +
                         " Result       CHAR(15)     NOT NULL," +
                         " GameTime       DATE    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
          }
          System.out.println("Table created");
        }
   }