package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
//  private Connection connection;
//
//
//    public UserDaoJDBCImpl() {
//
//        connection=Util.open();
//
//
//    }
//    public Connection getConnection(){
//        return connection;
//    }

    public void createUsersTable() {
        try(Statement statement =  Util.open().createStatement()) {
            String sqlCommand = "CREATE TABLE IF NOT EXISTS  Users  (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(30),age TINYINT)";
            statement.executeQuery("USE preproect_1_1_3");
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {


        String sqlCommand ="DROP TABLE IF EXISTS Users";
        try(Statement statement =  Util.open().createStatement())
         {
             statement.executeQuery("USE preproect_1_1_3");
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sqlCommand ="INSERT INTO users(Name, LastName, age) VALUES (?,?,?)";
        try(PreparedStatement pstmt=Util.open().prepareStatement(sqlCommand)) {

            pstmt.executeQuery("USE preproect_1_1_3");
            pstmt.setString(1,name);
            pstmt.setString(2,lastName);
            pstmt.setByte(3,age);
            System.out.println("User с именем — "+name+" added в базу данных");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(Statement statement =  Util.open().createStatement()) {
                String sqlCommand = "DELETE FROM Users WHERE Id = id";
                statement.executeUpdate(sqlCommand);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> resList=new ArrayList<>();
        try(Statement statement =  Util.open().createStatement()) {
            statement.executeQuery("USE preproect_1_1_3");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                Long id=resultSet.getLong("Id");
                String name=resultSet.getString("Name");
                String lastname=resultSet.getString("LastName");
                byte age=resultSet.getByte("age");
                User user=new User(name,lastname,age);
                user.setId(id);
                resList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resList;



    }

    public void cleanUsersTable() {

        String sqlCommand ="TRUNCATE TABLE Users";
        try(Statement statement =  Util.open().createStatement())
        {
            statement.executeQuery("USE preproect_1_1_3");
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
