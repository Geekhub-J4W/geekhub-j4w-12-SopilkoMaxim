package edu.geekhub.controller;

import edu.geekhub.Entity.User;
import edu.geekhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-info")
    public ResponseEntity<Object> getUserInfo() {
        User user = getUser();
        if (user == null) {
            return new ResponseEntity<>("{\"message\":\"User not found\"}", HttpStatus.NOT_FOUND);
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("fullName", user.getName());
        userInfo.put("email", user.getEmail());
        userInfo.put("balance", user.getBalance());
        userInfo.put("rating", user.getRating());
        userInfo.put("balance_coins", userService.getUserTotalBalance(user));

        Map<String, Float> wallet = new HashMap<>();
        wallet.put("lse", user.getWallet().getLse());
        wallet.put("bitcoin", user.getWallet().getBitcoin());
        wallet.put("ethereum", user.getWallet().getEthereum());
        wallet.put("bnb", user.getWallet().getBnb());
        wallet.put("xrp", user.getWallet().getXrp());
        wallet.put("solana", user.getWallet().getPolygon());
        wallet.put("tether", user.getWallet().getTether());
        wallet.put("usd", user.getWallet().getUsd());
        wallet.put("cardano", user.getWallet().getCardano());
        wallet.put("dogecoin", user.getWallet().getDogecoin());
        userInfo.put("wallet", wallet);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> registerNewUser(@RequestBody Map<String, Object> userData) {
        String email = (String) userData.get("email");
        String password = (String) userData.get("password");
        String fullName = (String) userData.get("full-name");

        User user = new User(fullName, email, password);
        if (userService.getUserByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User with this email already exists.");
        } else {
            userService.addUser(user);

            return ResponseEntity.ok().build();
        }
    }


    @GetMapping("/ratings")
    public Map<String, Float> getSortedUserRatings() {
        return userService.getSortedUserRatings();
    }

    @GetMapping("/update-rating")
    public ResponseEntity updateRating() {
        userService.updateRating(getUser());
        return ResponseEntity.ok().build();
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserByEmail(email).get();
    }

    @GetMapping("/user/role")
    public String getUserRole() {
        String userRole = getUser().getRole().toString();
        return userRole;
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/admin/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUser(@PathVariable  ("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/admin/users/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        User userDb = userService.getUserById(userId);
        userDb.setBalance(user.getBalance());
        userDb.setRole(user.getRole());
        userDb.setStatus(user.getStatus());
        userService.updateUser(userDb);
        return ResponseEntity.ok("User updated successfully");
    }

}
