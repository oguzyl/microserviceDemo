package com.work.microservice.demo.service;

import com.work.microservice.demo.dao.UserDao;
import com.work.microservice.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public User findOne(int id) {
        return userDao.getFindOne(id);
    }

    public void deleteById(int id) {
        userDao.deleteById(id);
    }
}
