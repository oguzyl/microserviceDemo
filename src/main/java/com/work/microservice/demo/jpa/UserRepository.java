package com.work.microservice.demo.jpa;

import com.work.microservice.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
