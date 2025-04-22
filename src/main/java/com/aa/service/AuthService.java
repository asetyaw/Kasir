package service;

import model.Pengguna;
import util.Database;
import util.HashUtil;

import java.sql.*;

public class AuthService {

    public boolean register(String username, String password) {
        try (Connection conn = Database.getConnection()) {
            String query = "INSERT INTO pengguna (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, HashUtil.hashPassword(password));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registrasi gagal: " + e.getMessage());
            return false;
        }
    }

    public Pengguna login(String username, String password) {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM pengguna WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (HashUtil.verifyPassword(password, hashedPassword)) {
                    return new Pengguna(
                            rs.getInt("id"),
                            rs.getString("username"),
                            hashedPassword
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Login gagal: " + e.getMessage());
        }
        return null;
    }
}
