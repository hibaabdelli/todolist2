package com.example.todolist;

import com.example.todolist.Task.TaskImpl;
import com.example.todolist.Task.Complet;
import com.example.todolist.Task.Taskk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseControler {

    public static ArrayList<Taskk> findAll() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        ArrayList<Taskk> tasks = new ArrayList<Taskk>();
        String query = "SELECT * FROM Tasks;";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Taskk task = new TaskImpl(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDate("date"),
                        Complet.valueOf(resultSet.getString("state"))
                );
                tasks.add(task);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    public static Taskk findById(int id) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM Tasks WHERE id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new TaskImpl(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDate("date"),
                        Complet.valueOf(resultSet.getString("state"))
                );
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    public static void create(Taskk task) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            System.out.println("error3");
            return;
        }

        String query = "INSERT INTO Tasks(name, description, date, state) VALUES(?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Utils.getSqlDate(task.getDate()));
            preparedStatement.setString(4, task.getState().name());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void update(Taskk task) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "UPDATE Tasks SET name = ?, description = ?, date = ?, state = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Utils.getSqlDate(task.getDate()));
            preparedStatement.setString(4, task.getState().name());
            preparedStatement.setInt(5, task.getId_T());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void deleteAll() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM Tasks;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void deleteById(int id) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM Tasks WHERE id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
