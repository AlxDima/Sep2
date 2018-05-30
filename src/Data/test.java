package Data;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class test {

    public static Connection connectToDatabase(String user, String port,
            String database) {
        System.out.println("- PostgreSQL JDBC Connection Testing -");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                  "postgres", "0774");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet executeSelect(Connection connection, String query) {
        Statement st = null;
        try {
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
            //st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return rs;
    }

    public static void dropTable(Connection connection, String table) {
        Statement st = null;
        try {
            st = connection.createStatement();
            st.execute("DROP TABLE " + table);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(Connection connection,
            String tableDescription) {
        Statement st = null;
        try {
            st = connection.createStatement();
            st.execute("CREATE TABLE " + tableDescription);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insertIntoTableFromFile(Connection connection,
            String table, String file) {

        BufferedReader br = null;
        int numRows = 0;
        try {
            Statement st = connection.createStatement();
            String sCurrentLine, brokenLine[], composedLine = "";
            br = new BufferedReader(new FileReader("src/TopURLs"));

            while ((sCurrentLine = br.readLine()) != null) {
                // Insert each line to the DB
                brokenLine = sCurrentLine.split("\t");
                composedLine = "INSERT INTO dotcom VALUES (";
                int i;
                for (i = 0; i < brokenLine.length - 1; i++) {
                    composedLine += "'" + brokenLine[i] + "',";
                }
                composedLine += "'" + brokenLine[i] + "')";
                numRows += st.executeUpdate(composedLine);
                //System.out.println(composedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return numRows;
    }

    public static void main(String[] argv) throws SQLException {
        /*
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your Username:");
        String user = input.next();
        System.out.println("Please enter your Port ID:");
        String port = input.next();
        */

        String user = "zbva777";
        String port = "28046";
        String database = "test";

        Connection connection = connectToDatabase(user, port, database);

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
            return;
        }
        // Now we're ready to work on the DB

        String query = "SELECT * FROM \"Game\".gamestat;";
        ResultSet rs = executeSelect(connection, query);
        try {
            while (rs.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs.close();

        dropTable(connection, "dotcom");
        createTable(connection, "dotcom (rank int primary key, name varchar(5000), type varchar(5000), subtype varchar(5000), subsubtype varchar(5000));");
        int rows = insertIntoTableFromFile(connection, "dotcom", "src/TopURLs");
        System.out.println(rows + " rows inserted.");
    }
}
