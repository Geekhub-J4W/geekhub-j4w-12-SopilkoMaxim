package edu.geekhub.controller;

import edu.geekhub.Entity.User;
import edu.geekhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login.html")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/index.html")
    public ModelAndView showHomePage() {
        return new ModelAndView("index");
    }

    @GetMapping("/register.html")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register");
    }

    @PostMapping("/register.html")
    @PreAuthorize("permitAll()")
    public ModelAndView registerNewUser(@RequestParam String email,
                                        @RequestParam String password,
                                        @RequestParam String fullName) {
        User user = new User(fullName, email, password);
        userService.addUser(user);
        return new ModelAndView("redirect:/login.html");
    }

}
