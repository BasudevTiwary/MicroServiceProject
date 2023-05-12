package com.userService2.UserService2.controller;

import com.userService2.UserService2.entities.User;
import com.userService2.UserService2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addUser(user);
        ResponseEntity<String> recordHasBeenAdded = new ResponseEntity<>("record has been added", HttpStatus.OK);
        return recordHasBeenAdded;
    }
    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        ResponseEntity<List<User>> listResponseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        return listResponseEntity;
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<User> getOneUser(@PathVariable("userId") int userId){
//        User user=userService.getOneUser(userId);
//        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
//        return userResponseEntity;
//    }
    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getOneUserByUserName(@PathVariable("userName") String userName){
        User user=userService.getOneUserByUserName(userName);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        return userResponseEntity;
    }
}
