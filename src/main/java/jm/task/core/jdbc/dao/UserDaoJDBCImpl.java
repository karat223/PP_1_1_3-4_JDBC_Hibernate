package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {

        try (Connection connection = Util.open()) {
            try (Statement statement = connection.createStatement()) {
                String sqlCommand = "CREATE TABLE IF NOT EXISTS  preproect_1_1_3.users  (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(30),age TINYINT)";
                statement.executeUpdate(sqlCommand);
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {


        try (Connection connection = Util.open()) {
            String sqlCommand = "DROP TABLE IF EXISTS preproect_1_1_3.users";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sqlCommand);
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.open()) {
            String sqlCommand = "INSERT INTO preproect_1_1_3.users(Name, LastName, age) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                System.out.println("User с именем — " + name + " added в базу данных");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.open()) {
            try (Statement statement = connection.createStatement()) {
                String sqlCommand = "DELETE FROM preproect_1_1_3.users WHERE Id = " + id;
                statement.executeUpdate(sqlCommand);
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> resList = new ArrayList<>();

        try (Connection connection = Util.open()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM preproect_1_1_3.users");
                while (resultSet.next()) {
                    Long id = resultSet.getLong("Id");
                    String name = resultSet.getString("Name");
                    String lastname = resultSet.getString("LastName");
                    byte age = resultSet.getByte("age");
                    User user = new User(name, lastname, age);
                    user.setId(id);
                    resList.add(user);
                }
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return resList;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.open()) {
            try (Statement statement = connection.createStatement()) {
                String sqlCommand = "TRUNCATE TABLE preproect_1_1_3.users";
                statement.executeUpdate(sqlCommand);
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
