package com.ruyo.fg.database;


import com.ruyo.fg.database.dao.UserDao;
import com.ruyo.fg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoFactory implements UserDao {

    @Autowired
    private Connection con;

    @Override
    public boolean insertUser(User User) {
        String sql = "INSERT INTO users VALUES(NULL,?,SHA2(?,0),?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, User.getUsername());
            ps.setString(2, User.getPassword());
            ps.setString(3, User.getEmail());
            ps.setBoolean(4,true);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error insertUser: "+ex.toString());
        }
        return false;
    }


    @Override
    public boolean updateUser(User User) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, User.getUsername());
            ps.setString(2, User.getPassword());
            ps.setInt(3,User.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return false;
    }

    @Override
    public boolean deleteUser(User User) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users WHERE isEnable AND !isDelete";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            List<User> listUsers = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                listUsers.add(user);
            }
            return listUsers;

        } catch (SQLException e) {
            /*e.printStackTrace();*/
            System.err.println("error: "+e.toString());
        }
        return null;

    }

    @Override
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = SHA2(?,0) AND isEnable AND !isDelete";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEnable(rs.getBoolean("isEnable"));
                user.setDelete(rs.getBoolean("isDelete"));
                return user;
            }

        } catch (SQLException | NullPointerException e) {
            System.err.println("error validateUser: "+e.toString());
        }
        return null;
    }
}
