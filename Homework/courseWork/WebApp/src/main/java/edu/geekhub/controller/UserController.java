package edu.geekhub.controller;

import edu.geekhub.Entity.User;
import edu.geekhub.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/user-info")
    public ResponseEntity<Object> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getUserByEmail(email).get();
        if (user == null) {
            return new ResponseEntity<>("{\"message\":\"User not found\"}", HttpStatus.NOT_FOUND);
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("fullName", user.getName());
        userInfo.put("email", user.getEmail());
        userInfo.put("balance", user.getBalance());
        userInfo.put("rating", user.getRating());

        Map<String, Float> wallet = new HashMap<>();
        wallet.put("lse", user.getWallet().getLse());
        wallet.put("bitcoin", user.getWallet().getBitcoin());
        wallet.put("ethereum", user.getWallet().getEthereum());
        wallet.put("bnb", user.getWallet().getBnb());
        wallet.put("xrp", user.getWallet().getXrp());
        wallet.put("polygon", user.getWallet().getPolygon());
        wallet.put("tether", user.getWallet().getTether());
        wallet.put("usd", user.getWallet().getUsd());
        wallet.put("cardano", user.getWallet().getCardano());
        wallet.put("dogecoin", user.getWallet().getDogecoin());
        userInfo.put("wallet", wallet);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> registerNewUser(@RequestParam String email,
                                                  @RequestParam String password,
                                                  @RequestParam String fullName,
                                                  HttpServletResponse response) throws IOException {
        User user = new User(fullName, email, password);
        userService.addUser(user);
        response.sendRedirect("/login.html");
        return null;
    }
    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }



}
