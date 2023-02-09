package com.work.microservice.demo.jpa;

import com.work.microservice.demo.model.Post;
import com.work.microservice.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


}
