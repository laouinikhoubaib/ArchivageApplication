package com.spring.archivageapplication.Controller;



import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.userRepository;
import com.spring.archivageapplication.Service.User.userService;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    userService us;

    @Autowired
    userRepository repo;

    public UserController(userService us) {

        this.us = us;
    }

    @PostMapping("/add")
    public ResponseEntity<User> create(@RequestBody  User user) {
        try {
            User User  = repo.save(new User(user.getUsername(),user.getEmail(),user.getPassword(), user.getFirstname(),user.getLastname(),
                    user.getPhoneNumber(),false));
            return new ResponseEntity<>(User, HttpStatus.CREATED);
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

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBy/username")
    public ResponseEntity<List<User>> getUserByUsername(@RequestParam(required = false) String username) {
        try {
            List<User> tutorials = new ArrayList<User>();
            if (username == null)
                repo.findAll().forEach(tutorials::add);
            else
                repo.findByUsername(username).forEach(tutorials::add);


            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
