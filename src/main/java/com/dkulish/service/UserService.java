package com.dkulish.service;

import com.dkulish.data.User;

public interface UserService {

    void saveUser(User user);

    User findUserById(String id);

    void deleteUserById(String id);

    void updateUser(User user);
}
