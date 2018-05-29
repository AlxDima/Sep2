package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ChatId {
   
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
            String sql = "CREATE TABLE \"Game\".chatid " +
                         "(ChatID              INT PRIMARY KEY     NOT NULL," +
                         " CHAT_TYPE           TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
          }
          System.out.println("Database created");
        }
   }