package com.work.microservice.demo.dao;

import com.work.microservice.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    private static List<User> users = new ArrayList<User>();
    private static int seq = 0;

    static {
        users.add(new User(++seq, "alex", "de souza"));
        users.add(new User(++seq, "ronnie", "coleman"));
    }


    public static List<User> findAll() {
        return users;
    };

    public User save(User user) {
        user.setId(++seq);
        users.add(user);
        return user;
    }

}
