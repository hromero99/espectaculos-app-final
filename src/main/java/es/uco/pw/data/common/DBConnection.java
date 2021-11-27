package es.uco.pw.data.common;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection{
    private Connection connection = null;
    private String url;
    private String username;
    private String password;

    public DBConnection() {
        try {
            FileInputStream fis=new FileInputStream("resources/config.properties");
            Properties pro=new Properties ();
            pro.load(fis);
            username = (String)pro.get("dbuser");
            password = (String)pro.get("dbpassword");
            url = String.format("jdbc:mysql://%s/%s", pro.get("dbhost"), pro.get("dbname"));
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public DBConnection(String url, String user, String password) {
    	this.username = user;
        this.password = password;
        this.url = url;
    }
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = (Connection) DriverManager.getConnection(this.url, this.username, this.password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
        return this.connection;
    }

    public void closeConnection() {
        try {
            if(this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error while trying to close the connection.");
            e.printStackTrace();
        }
    }
}
