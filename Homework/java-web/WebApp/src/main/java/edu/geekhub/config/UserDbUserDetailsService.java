package edu.geekhub.config;

import edu.geekhub.user.User;
import edu.geekhub.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDbUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.getUserByEmail(username);
        return user.map(UserDb::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}