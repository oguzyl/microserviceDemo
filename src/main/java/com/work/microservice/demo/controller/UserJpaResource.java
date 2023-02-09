package com.work.microservice.demo.controller;

import com.work.microservice.demo.exception.UserNotFoundException;
import com.work.microservice.demo.jpa.PostRepository;
import com.work.microservice.demo.jpa.UserRepository;
import com.work.microservice.demo.model.Post;
import com.work.microservice.demo.model.User;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserJpaResource {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
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

    @GetMapping(path = "/jpa/users/{id}/posts")
    @ResponseBody
    public List<Post> retrievePostsForUser(@PathVariable("id") int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        return user.get().getPosts();
    }


    @PostMapping(path = "/jpa/users/{id}/posts")
    @ResponseBody
    public ResponseEntity<Object> createPostForUser(@PathVariable("id") int id, @Valid @RequestBody Post post) {
        Optional<User> _user = userRepository.findById(id);
        if (_user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        post.setUser(_user.get());
        Post _post = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/jpa/users/{id}/posts/{postId}")
    @ResponseBody
    public EntityModel<Post> retrieveUser(@PathVariable("id") int id, @PathVariable("postId") int postId) throws UserNotFoundException {
        Optional<User> _user = userRepository.findById(id);
        Optional<Post> _post = postRepository.findById(postId);
        if(_user.isEmpty() || _post.isEmpty())
            throw new UserNotFoundException("User Id : " + id);

        EntityModel<Post> entityModel = EntityModel.of(_post.get());
        return entityModel;
    }


}
