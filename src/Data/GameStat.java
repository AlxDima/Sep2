package Data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GameStat {
   
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
         //  String sql = "UPDATE \"dreamhome\".staff " +
           // "(UserId       INT PRIMARY KEY     NOT NULL," +
           // " WinGamesNo       INT    NOT NULL," +
           // " TotalPGamesNo    INT   NOT NULL," +
          //  " Winrate          CHAR(15)     NOT NULL," +
           // " TotalPTime       CHAR(15)    NOT NULL)";

            String sql = "CREATE TABLE \"Game\".gamestat " +
                         "(UserId       INT PRIMARY KEY     NOT NULL," +
                         " WinGamesNo       INT    NOT NULL," +
                         " TotalPGamesNo      INT   NOT NULL," +
                         " Winrate       CHAR(15)     NOT NULL," +
                         " TotalPTime       CHAR(15)    NOT NULL)";
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