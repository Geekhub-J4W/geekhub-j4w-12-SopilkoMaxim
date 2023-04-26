package edu.geekhub;

import edu.geekhub.user.User;
import edu.geekhub.user.UserRepository;
import edu.geekhub.user.UserService;
import edu.geekhub.user.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserValidator validator;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(repository);
        userService.setPasswordEncoder(passwordEncoder);
    }

    @Test
    void addUser_whenValidUser_shouldAddUserToRepository() {
        User user = new User("John Doe", "john.doe@example.com", "password");
        when(passwordEncoder.encode("password")).thenReturn("encoded_password");
        when(repository.getUsers()).thenReturn(Arrays.asList());
        userService.addUser(user);

        verify(passwordEncoder).encode("password");
        verify(repository).addUser(user);
    }


    @Test
    void addUser_whenUserHasEmptyName_shouldNotAddUserToRepository() {
        User user = new User("", "john.doe@example.com", "password");
        when(repository.getUsers()).thenReturn(Arrays.asList());
        userService.addUser(user);

        verify(repository, never()).addUser(user);
    }

    @Test
    void addUser_whenUserNameContainsDigit_shouldNotAddUserToRepository() {
        User user = new User("John Doe 123", "john.doe@example.com", "password");
        when(repository.getUsers()).thenReturn(Arrays.asList());
        userService.addUser(user);

        verify(repository, never()).addUser(user);
    }

    @Test
    void addUser_whenUserEmailIsInvalid_shouldNotAddUserToRepository() {
        User user = new User("John Doe", "invalid_email", "password");
        when(repository.getUsers()).thenReturn(Arrays.asList());
        userService.addUser(user);

        verify(repository, never()).addUser(user);
    }

    @Test
    void deleteUser_whenUserExists_shouldDeleteUserFromRepository() {
        int userId = 1;
        User user = new User("John Doe", "john.doe@example.com", "password");
        when(repository.getUserById(userId)).thenReturn(user);
        when(repository.getUsers()).thenReturn(Arrays.asList(user));
        when(validator.validateIdExist(userId, repository, Logger.getLogger(User.class.getName()))).thenReturn(true);
        userService.setValidator(validator);
        userService.deleteUser(userId);

        verify(repository).deleteUser(userId);
    }

    @Test
    void deleteUser_whenUserDoesNotExist_shouldNotDeleteUserFromRepository() {
        int userId = 1;
        when(repository.getUserById(userId)).thenReturn(null);
        when(repository.getUsers()).thenReturn(Arrays.asList());
        userService.deleteUser(userId);

        verify(repository, never()).deleteUser(userId);
    }

    @Test
    void getUserByEmail_whenUserExists_shouldReturnUser() {
        User user = new User(1, "John Doe", "johndoe@example.com", "password");
        when(repository.getUserByEmail(user.getEmail())).thenReturn(user);

        Optional<User> result = userService.getUserByEmail(user.getEmail());

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

}