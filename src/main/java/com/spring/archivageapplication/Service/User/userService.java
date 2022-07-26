package com.spring.archivageapplication.Service.User;

import com.spring.archivageapplication.Models.User;

import java.util.List;

public interface userService {

    List<User> getUsers();

    User getUserById(Long id);

    User insert(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);


}
