package com.work.microservice.demo.dao;

import com.work.microservice.demo.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDao {

    private static List<User> users = new ArrayList<User>();
    private static int seq = 0;

    static {
        users.add(new User(++seq, "alex", LocalDate.now().minusYears(20)));
        users.add(new User(++seq, "ronnie", LocalDate.now().minusYears(30)));
    }


    public static List<User> findAll() {
        return users;
    };

    public User save(User user) {
        user.setId(++seq);
        users.add(user);
        return user;
    }

    public User getFindOne(int id) {
        Predicate<? super User> predicate = _user -> _user.getId() == id;
        User _user = users.stream().filter(predicate).findFirst().orElse(null);
        return _user;
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = _user -> _user.getId() == id;
        users.removeIf(predicate);
    }
}
