package edu.geekhub.config;

import edu.geekhub.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDb implements UserDetails {

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    private boolean status=true;

    public UserDb(User user) {
        name=user.getEmail();
        password=user.getPassword();
        authorities= Arrays.stream(user.getRole().toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        if(user.getStatus().toString().equals("BANNED"))
            status=false;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
