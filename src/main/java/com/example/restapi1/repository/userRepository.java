package com.example.restapi1.repository;

import com.example.restapi1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository  extends JpaRepository<User, Integer> {
}
