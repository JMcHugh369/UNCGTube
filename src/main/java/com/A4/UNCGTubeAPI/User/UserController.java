//package com.A4.UNCGTubeAPI.User;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    // Register user
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute User user) {
//        userService.registerUser(user);
//        return "redirect:/login";
//    }
//
//    // Login user
//    @PostMapping("/login")
//    public String loginUser(@RequestParam String username, @RequestParam String password) {
//        User user = userService.authenticate(username, password);
//        if (user != null) {
//            // Set session or JWT token for the user
//            return "redirect:/dashboard"; // Redirect to the user's dashboard
//        }
//        return "login"; // Return to login page if authentication fails
//    }
//}
