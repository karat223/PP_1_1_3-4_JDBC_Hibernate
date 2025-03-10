package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Util {

    public Util() {
    }

    public static Connection open() {
       Connection connection=null;
        try{
            String PUSSWORD = "45401";
            String USERNAME = "root";
            String URL = "jdbc:mysql://localhost:3306";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection=  DriverManager.getConnection(URL, USERNAME, PUSSWORD);

        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return connection;
    }
}





