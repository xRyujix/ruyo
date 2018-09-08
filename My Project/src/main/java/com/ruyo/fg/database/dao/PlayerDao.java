package com.ruyo.fg.database.dao;

import com.ruyo.fg.model.Player;


import java.util.List;

public interface PlayerDao {

    boolean insertPlayer(Player player);

    boolean updatePlayer(Player player);

    boolean deletePlayer(int id);

    Player getPlayerById(int id);

    boolean existPlayerByEmail(String email);

    int  getIdPlayerByEmail(String email);

    Player getPlayerByBtg(String battletag);

    List<Player> getAllPlayers(Player.Status playerStatus);

}
