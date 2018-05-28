package Create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Log_id {
   
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
            String sql = "CREATE TABLE \"Game\".user " +
                         "(LOG_ID INT PRIMARY KEY     NOT NULL," +
                         " CHAT_TYPE           TEXT    NOT NULL, " +
                         " ALIAS          TEXT    NOT NULL, " +
                         " PWD            CHAR(8))";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
          }
          System.out.println("Database table ok");
        }
   }