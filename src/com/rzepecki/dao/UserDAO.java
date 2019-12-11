package com.rzepecki.dao;

import com.rzepecki.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDAO {


    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ?, user_group_id = ? where id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String FIND_ALL_GROUP_QUERY = "SELECT * FROM users where user_group_id =?";

    public User create(User user) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            //statement.setInt(4, user.getUser_group_id());
            statement.setInt(4, user.getUser_group_id());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_group_id(resultSet.getInt("user_group_id"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user, int userId) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setObject(4, user.getUser_group_id());
            statement.setInt(5, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try  {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    public ArrayList<User> findAll() {
        try {
            //User[] users = new User[0];
            ArrayList<User> usersList = new ArrayList<User>();
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_group_id(resultSet.getInt("user_group_id"));
                //users = addToArray(user, users);
                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public ArrayList<User> findAllByGroupId(int groupId){
        try{
            ArrayList<User> usersList = new ArrayList<>();
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(FIND_ALL_GROUP_QUERY);
            statement.setInt(1,groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_group_id(resultSet.getInt("user_group_id"));
                usersList.add(user);
            }
            return usersList;
        }catch (SQLException e){
            e.printStackTrace(); return null;
        }
    }




}
