package com.onlineshopping.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.onlineshopping.onlineshop.PasswordEncoder;
import com.onlineshopping.onlineshop.models.UserModel;
import com.onlineshopping.onlineshop.services.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
     private UserRepo userRepo;

     // Render mapping for registration form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // jsp file name
    }

    // Action mapping for registration form submission
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {

        UserModel user = new UserModel(username, password);
        if (userRepo.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("error", "Username already exists. Please choose a different username.");
            return "redirect:/register"; // Redirect back to registration page
        }
        user.setPassword(PasswordEncoder.encode(password));
        userRepo.save(user);
        return "redirect:/login";
    }

    // renderMapping login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Action mapping for login form submission
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        UserModel user = userRepo.findByUsername(username);
        if (user == null || !PasswordEncoder.validatePassword(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password. Please try again.");
            return "redirect:/login"; // Redirect back to login page with error message
        }
        // set token to session after login
        final String token = PasswordEncoder.generateToken(username);
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        return "redirect:/profile";
    }


    // Render mapping for profile page
    @GetMapping("/profile")
    public String showProfile() {
        return "profile"; 
    }

    // Action mapping for logout
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Clear session
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

    // @GetMapping("/user")
    // public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("Authorization") String authorizationHeader) {
    //     // Check if Authorization header is present
    //     if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
    //     }
    //     // Extract token from Authorization header
    //     String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

    //     // Retrieve user from database using token
    //     UserModel authenticatedUser = userRepo.findByToken(token);
    //     if (authenticatedUser != null) {
    //         return ResponseEntity.status(HttpStatus.OK).body(authenticatedUser);
    //     } else {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    //     }
    // }

    // @GetMapping("/users/orders") 
    // public ResponseEntity<?> fetchMyOrders(@RequestHeader("Authorization") String authorizationHeader) {
    //      // Check if Authorization header is present
    //      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
    //     }
    //     // Extract token from Authorization header
    //     String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

    //     // Retrieve user from database using token
    //     UserModel authenticatedUser = userRepo.findByToken(token);
    //     if (authenticatedUser == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    //     }
    //     List<OrderModel> orders = authenticatedUser.getOrders();
    //     return ResponseEntity.status(HttpStatus.ACCEPTED).body(orders);
    // }
}
