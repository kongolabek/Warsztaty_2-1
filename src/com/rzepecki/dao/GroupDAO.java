package com.rzepecki.dao;

import com.rzepecki.model.Group;
import com.rzepecki.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class GroupDAO {

    private static final String CREATE_GROUP_QUERY = "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_GROUP_QUERY = "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_GROUP_QUERY = "UPDATE user_group SET name = ? where id = ?";
    private static final String DELETE_GROUP_QUERY = "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY = "SELECT * FROM user_group";

    public Group create(Group group) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group read(int groupId) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(READ_GROUP_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("username"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Group group, int id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(UPDATE_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try  {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Group> findAll() {
        try {
            ArrayList<Group> groupsList = new ArrayList<>();
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groupsList.add(group);
            }
            return groupsList;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }


}

