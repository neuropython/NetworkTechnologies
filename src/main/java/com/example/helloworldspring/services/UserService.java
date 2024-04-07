package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.UserDTO;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.repositories.AuthRepository;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final AuthRepository authRepository;

    @Autowired
    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
            userDTO.setRole(user.getRole().toString()); // assuming the User class has a getRole method that returns a Role object, and the Role class has a getName method that returns the name of the role
            userDTOs.add(userDTO);
        }
        return userDTOs;
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
