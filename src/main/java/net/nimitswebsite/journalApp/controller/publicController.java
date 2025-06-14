package net.nimitswebsite.journalApp.controller;

import net.nimitswebsite.journalApp.Service.UserService;
import net.nimitswebsite.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String health_check(){
        return "OK";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User myuser) {
        try{
            userService.saveNewEntry(myuser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
