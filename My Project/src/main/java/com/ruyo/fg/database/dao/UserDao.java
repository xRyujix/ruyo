package com.ruyo.fg.database.dao;

import com.ruyo.fg.model.User;

import java.util.List;

public interface UserDao {

    boolean insertUser(User User);

    boolean updateUser(User User);

    boolean deleteUser(User User);

    User getUserById(int id);

    List<User> getAllUsers();

    User validateUser(String username, String password);
}
