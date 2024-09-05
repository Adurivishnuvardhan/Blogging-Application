package com.Vishnu.BlogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Vishnu.BlogApp.model.Post;
import com.Vishnu.BlogApp.model.User;
import com.Vishnu.BlogApp.service.PostService;
import com.Vishnu.BlogApp.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    /**
     * Display the registration form for a new user.
     * @param model The model to hold an empty user object.
     * @return The view name to render.
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Renders register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users/login"; // Redirect to login page after registration
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Renders login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.validateUser(username, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user); // Store user in session
            return "redirect:/posts"; // Redirect to posts page
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Redirect back to login if invalid
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        return "redirect:/users/logout-page"; // Redirect to logout page
    }

    @GetMapping("/logout-page")
    public String logoutPage() {
        return "logout"; // Renders logout.html
    }

    

   
}
