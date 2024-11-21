//package com.A4.UNCGTubeAPI.User;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepo userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    // Register new user
//    public User registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password before saving
//        return userRepository.save(user);
//    }
//
//    // Authenticate user
//    public User authenticate(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user;
//        }
//        return null;
//    }
//}
