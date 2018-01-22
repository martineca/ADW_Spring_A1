package com.example.demo.service;

import com.example.demo.domain.LoginForm;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User u)
    {
        return userRepository.save(u);
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public void delete(User u){
         userRepository.delete(u);
    }

    public List<User> validateLogin(LoginForm user)
    {
       return userRepository.findByFirstnameAndPassword(user.getUsername(),user.getPassword());
       // List<User> users=userRepository.checkUserInput(user.getUsername(),user.getPassword());
     //   return users !=null && users.size()>0;
    }

}
