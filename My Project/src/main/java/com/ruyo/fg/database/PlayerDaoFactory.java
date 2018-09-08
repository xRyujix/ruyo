package com.ruyo.fg.database;

import com.ruyo.fg.database.dao.PlayerDao;

import com.ruyo.fg.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerDaoFactory implements PlayerDao {

    @Autowired
    private Connection con;
    /*
        private int id;
        private String firstname;
        private String lastname;
        private String age;
        private String email;
        private String nationality;
     */
    @Override
    public boolean insertPlayer(Player player) {
        String sql = "INSERT INTO players VALUES(NULL,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, player.getFirstname());
            ps.setString(2, player.getLastname());
            ps.setString(3, player.getAge());
            ps.setString(4, player.getEmail());
            ps.setString(5, player.getNationality());
            ps.setBoolean(6,true);
            ps.setBoolean(7,false);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error insertPlayer: "+ex.toString());
        }
        return false;
    }

    @Override
    public boolean updatePlayer(Player player) {
        String sql = "UPDATE players SET name = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, player.getFirstname());
            ps.setString(2, player.getLastname());
            ps.setInt(3,player.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return false;
    }

    @Override
    public boolean deletePlayer(int id) {
        String sql = "UPDATE players set isDelete =? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return false;
    }

    @Override
    public Player getPlayerById(int id) {
        return null;
    }

    @Override
    public boolean existPlayerByEmail(String email) {
        String sql = "SELECT email FROM players WHERE email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               return true;
            }
        } catch (SQLException e) {
            System.err.println("error: "+e.toString());
        }
        return false;
    }

    @Override
    public int getIdPlayerByEmail(String email) {
        String sql = "SELECT id FROM players WHERE email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            /*e.printStackTrace();*/
            System.err.println("error: "+e.toString());
        }
        return 0;
    }


    @Override
    public Player getPlayerByBtg(String battletag) {
        return null;
    }

    @Override
    public List<Player> getAllPlayers(Player.Status playerStatus) {
        String sql;

        switch (playerStatus) { //fixme: arreglar esto tmb
            case ENABLED_AND_NOTDELETED:
                sql = "SELECT * FROM players WHERE isEnable AND !isDelete";
                break;
            case NOTENABLED_AND_NOTDELETED:
                sql = "SELECT * FROM players WHERE !isEnable AND !isDelete";
                break;
            case NOTENABLED_AND_DELETED:
                sql = "SELECT * FROM players WHERE !isEnable AND isDelete";
                break;
            default:
                sql = "SELECT * FROM players";
                break;
        }

        try {
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            List<Player> listUsers = new ArrayList<>();
            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setFirstname(rs.getString("firstname"));
                player.setLastname(rs.getString("lastname"));
                player.setAge(rs.getString("age"));
                player.setEmail((rs.getString("email")));
                player.setNationality(rs.getString("nationality"));
                player.setEnable(rs.getBoolean("isEnable"));
                player.setDelete(rs.getBoolean("isDelete"));
                listUsers.add(player);
            }
            return listUsers;

        } catch (SQLException e) {
            /*e.printStackTrace();*/
            System.err.println("error: "+e.toString());
        }
        return null;
    }

}
