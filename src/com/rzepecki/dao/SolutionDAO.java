package com.rzepecki.dao;

import com.rzepecki.model.Exercise;
import com.rzepecki.model.Solution;
import com.rzepecki.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class SolutionDAO {
    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution(created, description , exercise_id, users_id) VALUES (?, ?, ?, ?)"; //na poczatku ustawia tylko created date
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET updated = ?, description = ?, exercise_id = ?, users_id = ? where id = ?"; //brak mozliwosci zmiany daty utworzenia
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY = "SELECT * FROM solution";
    private static final String FIND_ALLBYUSER_SOLUTION_QUERY = "SELECT * FROM solution where users_id= ?";
    private static final String FIND_ALLBYEXERCISE_SOLUTION_QUERY = "SELECT * FROM solution where exercise_id= ?";

    public Solution create(Solution solution) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, solution.getCreated());
            statement.setString(2, solution.getDescription());
            statement.setObject(3, solution.getExercise_id());
            statement.setObject(4, solution.getUsers_id());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solutionId) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution =new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                if (solution.getUpdated()!=null){
                    solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                }
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(UPDATE_SOLUTION_QUERY);
            solution.setUpdated(LocalDateTime.now());
            statement.setDate(1, solution.getUpdated());
            statement.setString(2, solution.getDescription());
            statement.setObject(3, solution.getExercise_id());
            statement.setObject(4, solution.getUsers_id());
            statement.setInt(5, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try  {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Solution> findAll() {
        try {
            ArrayList<Solution> solutionsList = new ArrayList<>();
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutionsList.add(solution);
            }
            return solutionsList;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public ArrayList<Solution> findAllByUserId(int usersId){
        try {
            ArrayList<Solution> solutionsList = new ArrayList<>();
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(FIND_ALLBYUSER_SOLUTION_QUERY);
            statement.setInt(1, usersId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                if (solution.getUpdated()!=null){
                    solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                }
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutionsList.add(solution);
            }
            return solutionsList;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }

    }

    public ArrayList<Solution> findAllByExerciseId(int exerciseId){
        try {
            ArrayList<Solution> solutionsList = new ArrayList<>();
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(FIND_ALLBYEXERCISE_SOLUTION_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                if (solution.getUpdated()!=null){
                    solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                }
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutionsList.add(solution);
            }
            return solutionsList;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }

    }


}
