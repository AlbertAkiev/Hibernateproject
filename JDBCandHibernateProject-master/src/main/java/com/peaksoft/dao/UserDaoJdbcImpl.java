package com.peaksoft.dao;



import com.peaksoft.model.User;
import com.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS  users(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50)," +
                "last_name VARCHAR(30)," +
                "age INT );";
        try (Connection con = Util.connection();
             Statement statement = con.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users ";
        try(Connection con= Util.connection();
            Statement statement=con.createStatement()){
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users(name,last_name,age) VALUES(?,?,?);";
        try(Connection con= Util.connection();
            PreparedStatement statement=con.prepareStatement(SQL)){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?";
        try(Connection con= Util.connection();
            PreparedStatement statement = con.prepareStatement(SQL)){
            statement.setLong(1, id);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try(Connection con= Util.connection();
            Statement statement=con.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()){
                User user =  new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getByte("age"));
                user.setLastName(resultSet.getString("last_name"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE users";
                try(Connection con=Util.connection();
                Statement statement = con.createStatement()){
                    statement.executeUpdate(SQL);
                }catch (SQLException e){
                    e.printStackTrace();
                }

    }
}