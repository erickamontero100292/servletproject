package com.library.dao;

import com.library.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DbConnection conn;

    public UserDao(DbConnection conn) {
        this.conn = conn;
    }

    public User login(String user, String pass) {

        try {
            String sql = "select * from user where username=? and password = md5(?) and state='active' limit 1";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            ResultSet rs = preparedStatement.executeQuery();
            User usuario = new User(0);
            while (rs.next()) {
                // Create an object for the user
                usuario.setId(rs.getInt("iduser"));
                usuario.setName(rs.getString("name"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setProfile(rs.getString("profile"));
                usuario.setState(rs.getString("state"));
            }
            return usuario;
        } catch (SQLException e) {
            System.out.println("Error UsuarioDao.login: " + e.getMessage());
            return null;
        }
    }
}
