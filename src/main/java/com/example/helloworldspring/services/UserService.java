package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.UserDTO;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

}
