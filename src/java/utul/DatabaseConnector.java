package utul;

import java.sql.*;

/**
 * Creates a connection to a database for your project.
 * NOTE: DO NOT CHANGE ANYTHING ELSE IN THIS FILE OTHER THAN
 * THE DATABASE NAME in the url argument of the getConnection method
 * @author jijones
 */
public class DatabaseConnector {
	/** The connection object. */
    private static Connection conn;
    
	/**
	 * Creates the connection between your application and the specified database.
	 */
    //gets the database connection
    public static Connection getConnection(){
        try {
            /* The following method is what actually creates the connection to
               the database. The parameters are passed in the following order:
               url - the database to connect to. For our purposes, must be
                     "jdbc:derby://localhost:1527/YOURDATABASENAMEHERE"        
               username - the username you set when you created your database
               password - the password you set when you created your database
            */
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Housing", "Ten", "123");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }
    
    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args){
        try{
           Connection connection = getConnection();
           System.out.println("Connection established!\n");
           String sql = "SELECT TABLENAME FROM sys.systables where tabletype='T'";
           
           PreparedStatement stmt = connection.prepareStatement(sql);
           
           ResultSet result = stmt.executeQuery();
           System.out.println("Current tables in database: " );
           while(result.next()){
               System.out.println(result.getString("TABLENAME"));
           }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            closeConnection();
        }
    }
}
