package edu.geekhub.Service;


import edu.geekhub.Entity.User;
import edu.geekhub.Entity.Wallet;
import edu.geekhub.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CryptoCoinService cryptoCoinService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService();
        userService.userRepository = userRepository;
        userService.cryptoCoinService = cryptoCoinService;
        userService.passwordEncoder = passwordEncoder;
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setBalance(10000f);

        when(passwordEncoder.encode("password")).thenReturn("encoded_password");

        userService.addUser(user);

        verify(passwordEncoder).encode("password");
        verify(userRepository).addUser(user);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setBalance(10000f);

        userService.updateUser(user);

        verify(userRepository).updateUser(1, user);
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setBalance(10000f);

        when(userRepository.getUserByEmail("john@example.com")).thenReturn(user);

        Optional<User> result = userService.getUserByEmail("john@example.com");

        assertEquals(user, result.get());
    }

    @Test
    void testGetUserTotalBalance() {
        User user = new User();
        user.setId(1);
        user.setBalance(10000f);
        user.setWallet(new Wallet());
        user.getWallet().setBitcoin(1f);
        user.getWallet().setEthereum(2f);

        when(cryptoCoinService.getLastPriceByName("bitcoin")).thenReturn(50000f);
        when(cryptoCoinService.getLastPriceByName("ethereum")).thenReturn(3000f);

        float result = userService.getUserTotalBalance(user);

        assertEquals(10000f + 1f * 50000f + 2f * 3000f, result, 0.001f);
    }

    @Test
    void testUpdateRating() {
        User user = new User();
        user.setId(1);
        user.setBalance(1000000f);
        user.setWallet(new Wallet());
        user.getWallet().setBitcoin(1f);
        user.getWallet().setEthereum(2f);

        when(cryptoCoinService.getLastPriceByName("bitcoin")).thenReturn(50000f);
        when(cryptoCoinService.getLastPriceByName("ethereum")).thenReturn(3000f);

        userService.updateRating(user);

        assertEquals(56000f, user.getRating(), 0.001f);
        verify(userRepository).updateUser(1, user);
    }

    @Test
    public void testGetSortedUserRatings() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Tor");
        user1.setBalance(5000f);
        User user2 = new User();
        user2.setId(2);
        user2.setName("Stark");
        user2.setBalance(10000f);
        User user3 = new User();
        user3.setId(3);
        user3.setName("Vanda");
        user3.setBalance(15000f);

        doReturn(1000f).when(cryptoCoinService).getLastPriceByName("bitcoin");
        doReturn(2000f).when(cryptoCoinService).getLastPriceByName("ethereum");
        doReturn(3000f).when(cryptoCoinService).getLastPriceByName("dogecoin");

        doReturn(Map.of(
                "Tor", 5000f * 1000f + 1000f,
                "Stark", 10000f * 1000f + 2000f,
                "Vanda", 15000f * 1000f + 3000f
        )).when(userRepository).getSortedUserRatings();

        Map<String, Float> result = userService.getSortedUserRatings();

        assertEquals(3, result.size());
        assertEquals(15003000f, result.get("Vanda"), 0.001f);
        assertEquals(10002000f, result.get("Stark"), 0.001f);
        assertEquals(5001000f, result.get("Tor"), 0.001f);
        verify(userRepository).getSortedUserRatings();
    }

}