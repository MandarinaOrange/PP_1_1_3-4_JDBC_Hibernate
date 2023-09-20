package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL =
            "jdbc:mysql://localhost:3306/test1" ;
                    //+ "INIT = RUNSCRIPT FROM 'classpath: init.sql'";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    private static SessionFactory sessionFactory;

    public Util() {
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


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                configuration.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test1");
                configuration.setProperty("hibernate.connection.username", "root");
                configuration.setProperty("hibernate.connection.password", "root");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.setProperty(" hibernate.connection.pool_size", "10");


                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("ERROR " + e);
            }
        }
        return sessionFactory;
    }




}
