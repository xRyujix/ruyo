package com.ruyo.fg.database.dao;

import com.ruyo.fg.model.User_Player;

import java.util.List;

public interface User_PlayerDao {

    boolean addUser_Player(int idUser, int idPlayer);

    boolean updateUser_Player(User_Player user_player);

    boolean deleteUser_Player(int id);

    List<User_Player> getAllUser_Player();
}
