package com.work.microservice.demo.controller;


import com.work.microservice.demo.model.User;
import com.work.microservice.demo.service.UserService;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@Controller
@Validated
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
        public User getUser(@PathVariable("id") int id) {
            List<User> users = userService.getUsers();

            Predicate<? super User> predicate = item -> (item.getId() == id);
        return  users.stream().filter(predicate).findFirst().orElse(null);
    }


    @PostMapping(path = "/users")
//    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userService.save(user);
        return ResponseEntity.badRequest().body(_user);
//        return ResponseEntity.created(null).build();


    }


}
