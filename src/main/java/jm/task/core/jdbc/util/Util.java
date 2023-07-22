package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL =
            "jdbc:mysql://localhost:3306/test1" ;
                    //+ "INIT = RUNSCRIPT FROM 'classpath: init.sql'";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "Hohner03";

    Util() {
        try /*(Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD));*/{
             //Statement statement = connection1.createStatement()){
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection1.isClosed()) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println("No connection");
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
