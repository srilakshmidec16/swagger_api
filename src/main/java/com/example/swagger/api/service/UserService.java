package com.example.swagger.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swagger.api.dao.UserRepository;
import com.example.swagger.api.exception.EmptyFirstNameException;
import com.example.swagger.api.exception.InvalidAgeException;
import com.example.swagger.api.exception.InvalidMobileNumberException;
import com.example.swagger.api.model.User;

import javassist.NotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User createUser(User user) {
    	if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new EmptyFirstNameException("First name cannot be empty");
        }
    	if (user.getAge() <= 0 || user.getAge()>150) {
            throw new InvalidAgeException("Invalid Age");
        }
    	if (user.getPhoneNumber()!=null || user.getPhoneNumber().length()!=10) {
    		throw new InvalidMobileNumberException("Phone number should be 10 digits");
    	}
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Update user details
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAge(userDetails.getAge());
        user.setPhoneNumber(userDetails.getPhoneNumber());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
}
