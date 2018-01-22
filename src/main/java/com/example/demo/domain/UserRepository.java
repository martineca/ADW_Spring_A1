package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> findByFirstnameAndPassword(String username, String password);
    @Query("SELECT u FROM User u Where u.firstname=?1 and u.password=?2")
    List<User> checkUserInput(String accountname, String password);

}
