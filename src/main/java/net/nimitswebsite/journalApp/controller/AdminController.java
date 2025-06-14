package net.nimitswebsite.journalApp.controller;

import net.nimitswebsite.journalApp.Service.UserService;
import net.nimitswebsite.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAll();
        if(allUsers!=null && !allUsers.isEmpty() ){
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveadmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
