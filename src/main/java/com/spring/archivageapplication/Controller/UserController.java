package com.spring.archivageapplication.Controller;



import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.userRepository;
import com.spring.archivageapplication.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
public class UserController {

    userService us;

    public UserController(userService us) {
        this.us = us;
    }

    @Autowired
    userRepository repo;

    @PostMapping("/ajouter")
    public ResponseEntity<User> create(@RequestBody  User user) {
        try {
            User _User  = repo.save(new User(user.getUsername(),user.getEmail(),user.getPassword(), user.getFirstname(),user.getLastname(),
                    user.getPhoneNumber(),false));
            return new ResponseEntity<>(_User, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"/update/{id}"})
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        us.updateUser(id, user);
        return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        us.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/get/{id}"})
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
    }

}
