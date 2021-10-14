package peaksoft.dao;

import peaksoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connect;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users" +
                " ( id SERIAL PRIMARY KEY ," +
                "name VARCHAR NOT NULL , " +
                "lastname VARCHAR NOT NULL ," +
                "age INT NOT NULL )";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table User Create...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table User removed...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setString(1, name);
            pStmt.setString(2, lastName);
            pStmt.setInt(3, age);
            pStmt.executeUpdate();
            System.out.println("User add...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setInt(1, (int) id);
            pStmt.executeUpdate();
            System.out.println("Remove User by id...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(
                        rs.getString("name"),
                        rs.getString("lastname"),
                        (byte) rs.getInt("age")));
            }
            System.out.println("Get all User...1");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Clean all Users...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}