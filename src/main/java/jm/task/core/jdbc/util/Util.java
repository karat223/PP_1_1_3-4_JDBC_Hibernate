package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    public Util() {
    }

    public static Connection open() throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException,
            SQLException {
        Connection connection = null;

        String password = "45401";
        String username = "root";
        String url = "jdbc:mysql://localhost:3306";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }

}





