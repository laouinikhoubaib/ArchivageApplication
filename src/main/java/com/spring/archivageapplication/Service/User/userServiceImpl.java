package com.spring.archivageapplication.Service;

import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.userRepository;
import com.spring.archivageapplication.Service.User.userService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class userServiceImpl implements userService {

    userRepository ur;

    public userServiceImpl(userRepository ur) {
        this.ur = ur;
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();
        ur.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return ur.findById(id).get();
    }

    @Override
    public User addUser(User user) {
        return ur.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {

        User fromDb = ur.findById(id).get();
        System.out.println(fromDb.toString());

        fromDb.setFirstname(user.getFirstname());
        fromDb.setLastname(user.getLastname());
        fromDb.setEmail(user.getEmail());
        fromDb.setPassword(user.getPassword());
        fromDb.setPhoneNumber(user.getPhoneNumber());

        ur.save(fromDb);
    }

    @Override
    public void deleteUser(Long id) {
        ur.deleteById(id);
    }
}
