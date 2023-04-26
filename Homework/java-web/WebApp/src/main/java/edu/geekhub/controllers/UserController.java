package edu.geekhub.controllers;

import com.google.gson.Gson;
import edu.geekhub.user.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> showRegisterPage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/register/register.html"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.addUser(user);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "User registered successfully");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setLocation(URI.create("/login"));
        return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.CREATED);
    }

    @GetMapping("superadmin/users")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public String allCustomers() {
        return new Gson().toJson(userService.getUsers());
    }

    @GetMapping("superadmin/users/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")

    public String customerById(@PathVariable("id") Integer id) {
        return new Gson().toJson(userService.getUserById(id));
    }

    @PostMapping("superadmin/users")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public boolean create(@RequestBody User user) {
        userService.addUser(user);
        return true;
    }

    @DeleteMapping("superadmin/users/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public boolean delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return true;
    }

    @PutMapping("superadmin/users/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public boolean update(@RequestBody User user){
        userService.update(user.getId(), user);
        return true;
    }
}
