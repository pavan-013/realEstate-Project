package com.resp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.resp.dto.LoginDto;
import com.resp.dto.UserDto;
import com.resp.exceptions.UserException;
import com.resp.model.User;
import com.resp.repository.UserRepo;
import com.resp.security.TokenUtil;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    private Map<String, String> activeSessions = new HashMap<>();

    // Login logic
    public Optional<UserDto> loginUser(LoginDto loginDto) throws UserException {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("User Found: " + user.getEmail());

            // For plain text passwords
            if (passwordEncoder.matches(password, user.getPassword())) {
            	String token = TokenUtil.generateToken();
                activeSessions.put(token, user.getEmail());
                    
                System.out.println("Password matches");
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUsername(user.getUsername());
                userDto.setMobilenumber(user.getMobilenumber());
                userDto.setEmail(user.getEmail());
                userDto.setPassword(user.getPassword());
                userDto.setRoles(user.getRoles());
                userDto.setToken(token);
                return Optional.of(userDto);
            } else {
                System.out.println("Password does not match");
                throw new UserException("Incorrect password");
            }
        } else {
            System.out.println("Email not found");
            throw new UserException("Email not found");
        }
    }
    //logout logic
    public void logoutUser(String token) throws UserException {
        if (activeSessions.containsKey(token)) {
            activeSessions.remove(token);
        } else {
            throw new UserException("Invalid session token");
        }
    }
    
    public boolean isAuthenticated(String token) {
        return activeSessions.containsKey(token);
    }

}
