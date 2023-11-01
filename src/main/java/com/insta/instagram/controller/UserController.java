package com.insta.instagram.controller;

import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.User;
import com.insta.instagram.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody User user) throws UserException {
        User signUser =  userService.registerUser(user);
        return new ResponseEntity<>(signUser, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id) throws UserException {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserByUsernameHandler(@PathVariable String username) throws UserException {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds) throws UserException {
        List<User> users = userService.findUserByIds(userIds);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }



}
