package com.work.microservice.demo.controller;

import com.work.microservice.demo.exception.UserNotFoundException;
import com.work.microservice.demo.jpa.UserRepository;
import com.work.microservice.demo.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserJpaResource {

    private UserRepository userRepository;

    public UserJpaResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/jpa/users")
    @ResponseBody
    public List<User> retrieveAllUsers() {

        return userRepository.findAll();

    }

    @GetMapping(path = "/jpa/users/{id}")
    @ResponseBody
    public EntityModel<User> retrieveUser(@PathVariable("id") int id) throws UserNotFoundException {
        Optional<User> _user = userRepository.findById(id);
        if(_user.isEmpty())
            throw new UserNotFoundException("User Id : " + id);

        EntityModel<User> entityModel = EntityModel.of(_user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping(path = "/jpa/users")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userRepository.save(user);
        return ResponseEntity.created(null).body(_user);
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") int id) throws UserNotFoundException {
        userRepository.deleteById(id);
    }

}
