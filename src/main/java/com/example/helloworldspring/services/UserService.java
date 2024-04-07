package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.UserDTO;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.repositories.AuthRepository;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AuthRepository authRepository;

    @Autowired
    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }


    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

//    public UserDTO patchUser(String username, UserDTO userDTO) {
//        User user = userRepository.findByUsername(username);
//        if (userDTO.getName() != null) {
//            user.setName(userDTO.getName());
//        }
//        if (userDTO.getEmail() != null) {
//            user.setEmail(userDTO.getEmail());
//        }
//        if (userDTO.getUsername() != null) {
//            user.setUsername(userDTO.getUsername());
//        }
//        userRepository.save(user);
//        return new UserDTO(user.getUsername(), user.getEmail(), user.getName());
//    }
//
//    public UserDTO DeleteUser(String username) {
//        User user = userRepository.findByUsername(username);
//        userRepository.delete(user);
//        return new UserDTO(user.getUsername(), user.getEmail(), user.getName());
//    }

}
