package com.ruyo.fg.database;

import com.ruyo.fg.database.dao.User_PlayerDao;
import com.ruyo.fg.model.User_Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class User_PlayerDaoFactory implements User_PlayerDao {

    @Autowired
    private Connection con;

    @Override
    public boolean addUser_Player(int idUser, int idPlayer) {
        String sql = "INSERT INTO users_players VALUES(NULL,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.setInt(2, idPlayer);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error addUser_Player: "+ex.toString());
        }
        return false;
    }

    @Override
    public boolean updateUser_Player(User_Player user_player) {
        return false;
    }

    @Override
    public boolean deleteUser_Player(int id) {
        return false;
    }

    @Override
    public List<User_Player> getAllUser_Player() {
        return null;
    }
}
