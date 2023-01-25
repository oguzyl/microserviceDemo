package com.work.microservice.demo.controller;


import com.work.microservice.demo.exception.UserNotFoundException;
import com.work.microservice.demo.model.User;
import com.work.microservice.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/users/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") int id) throws UserNotFoundException {
        User _user = userService.findOne(id);
//        throw new NullPointerException("sada");
        if(_user == null)
            throw new UserNotFoundException("User Id : " + id);
        return _user;
    }


    @PostMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userService.save(user);
        return ResponseEntity.created(null).body(_user);
    }



    @DeleteMapping(path = "/users/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") int id) throws UserNotFoundException {
        userService.deleteById(id);
    }

}
