package edu.geekhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("admin@gmail.com")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin);

        return new UserDbUserDetailsService();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(authRegistry -> authRegistry
//                        .requestMatchers( "/swagger-ui/**","/").permitAll()
//                        .requestMatchers( "/swagger-ui.html").permitAll()
//                        .requestMatchers("/register.html").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/register.html").permitAll()
//                        .requestMatchers("/btc.html").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                );
//                http.formLogin()
//                        .loginPage("/login.html")
//                        .permitAll();
//                http.logout()
//                        .permitAll();
//
//        return http.build();
//    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

}
