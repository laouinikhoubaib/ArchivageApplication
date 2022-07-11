package com.spring.archivageapplication.Service;

import com.spring.archivageapplication.Models.User;

import java.util.List;

public interface userService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}
