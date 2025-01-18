package com.resp.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.resp.model.User;
import com.resp.dto.UserDto;
import com.resp.repository.UserRepo;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepo repo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Create a new user
    @PostMapping("/userRegister")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setMobilenumber(userDto.getMobilenumber());
        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        User savedUser = repo.save(user);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(savedUser.getId());
        savedUserDto.setUsername(savedUser.getUsername());
        savedUserDto.setMobilenumber(savedUser.getMobilenumber());
        savedUserDto.setEmail(savedUser.getEmail());
        savedUserDto.setPassword(savedUser.getPassword());  // Ensure you return the password if required
        savedUserDto.setRoles(savedUser.getRoles());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }

    // Get all users
    @GetMapping("/getAllRegisterUD")
    public List<User> getUsers() {
        return repo.findAll();
    }

    // Get user by ID
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = repo.findById(id);
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = userOptional.get();
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setMobilenumber(user.getMobilenumber());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());  // Ensure you return the password if required
        userDto.setRoles(user.getRoles());
        return ResponseEntity.ok(userDto);
    }

    // Update user
    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        Optional<User> userOptional = repo.findById(id);
        
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  
        }

        User existingUser = userOptional.get();
        existingUser.setUsername(updatedUserDto.getUsername());
        existingUser.setMobilenumber(updatedUserDto.getMobilenumber());
        existingUser.setEmail(updatedUserDto.getEmail());
        existingUser.setRoles(updatedUserDto.getRoles());
        if (updatedUserDto.getPassword() != null && !updatedUserDto.getPassword().isEmpty()) {
//            existingUser.setPassword(updatedUserDto.getPassword());
        	existingUser.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
        }
        
        User savedUser = repo.save(existingUser);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(savedUser.getId());
        savedUserDto.setUsername(savedUser.getUsername());
        savedUserDto.setMobilenumber(savedUser.getMobilenumber());
        savedUserDto.setEmail(savedUser.getEmail());
        savedUserDto.setPassword(savedUser.getPassword());  // Ensure you return the password if required
        savedUserDto.setRoles(savedUser.getRoles());
        return ResponseEntity.ok(savedUserDto);
    }

    // Delete user by ID
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = repo.findById(id);       
        if (userOptional.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
