package com.spring.archivageapplication.Controller;



import com.spring.archivageapplication.Models.Complaint;
import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.userRepository;
import com.spring.archivageapplication.Service.Complaint.ComplaintService;
import com.spring.archivageapplication.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    userService us;

    @Autowired
    userRepository repo;

    public AdminController(userService us) {

        this.us = us;
    }
    @Autowired
    ComplaintService ComplaintService;

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
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        try {
            List<User> users = new ArrayList<User>();
            if (username == null)
                repo.findAll().forEach(users::add);
            else
                repo.findByUsername(username).forEach(users::add);
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
            List<User> users = new ArrayList<User>();
            if (username == null)
                repo.findAll().forEach(users::add);
            else
                repo.findByUsername(username).forEach(users::add);


            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/retrieveAllComplaints")
    public List<Complaint> retrieveAllComplaints() {

        return ComplaintService.retrieveAllComplaints();
    }


    @PutMapping("/updateUntreatedComplaint/{Compalaint-id}")
    @Transactional
    public Complaint updateComplaint2(@PathVariable(value ="Compalaint-id")Integer id) throws MessagingException {
        return ComplaintService.updateComplaint2(id);
    }

    @PostMapping("/affectatComplaintToUser/{Complaint_id}/{id}")
    @ResponseBody
    public void affectatComplaintToUser(@PathVariable("Complaint_id") int Complaint_id,@PathVariable("id")int id) throws IOException {

        ComplaintService.affectatComplaintToUser(Complaint_id, id);
    }

}
