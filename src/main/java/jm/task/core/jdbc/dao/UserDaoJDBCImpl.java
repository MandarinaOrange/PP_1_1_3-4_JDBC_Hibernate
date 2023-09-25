package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE SCHEMA if NOT EXISTS test1;";
        String query1 = "USE test1;";
        String query2 = "DROP TABLE if EXISTS users " ;
        String query3 = "CREATE TABLE users(id integer not null primary key auto_increment, name varchar(40), lastName varchar(40), age smallint)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
             PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
             PreparedStatement preparedStatement3 = connection.prepareStatement(query3);

        ) {
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        String query2 = "DROP TABLE if EXISTS users " ;
        try (
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);

        ) {
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into users (name, lastName, age) values ('" + name + "', '" +
                lastName + "'," + Byte.toString(age) +")" ;
        try (
                Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = " + Long.toString(id) ;
        try (
                Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = "select * from users";
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                long id = res.getLong("id");
                String name = res.getString("name");
                String lastName = res.getString("lastName");
                Byte age = res.getByte("age");
                users.add(new User(id, name, lastName, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users" ;
        try (
                Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
