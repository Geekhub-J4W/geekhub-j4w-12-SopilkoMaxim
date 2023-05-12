package edu.geekhub.Service;

import edu.geekhub.Entity.User;
import edu.geekhub.Entity.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TradingServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private TransferService transferService;
    @Mock
    private CryptoCoinService cryptoCoinService;
    @InjectMocks
    private TradingService tradingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testBuyCoins() {
        User user = new User();
        user.setBalance(100000f);
        user.setWallet(new Wallet());
        user.getWallet().setByName(10f, "bitcoin");

        when(cryptoCoinService.getLastPriceByName("bitcoin")).thenReturn(10000f);

        tradingService.buyCoins(user, "bitcoin", 2.5);

        assertEquals(12.5f, user.getWallet().getBitcoin(), 0.001f);
        assertEquals(75000f, user.getBalance(), 0.001f);
        verify(userService).updateUser(user);
        }

    @Test
    public void testSellCoins() {
        User user = new User();
        user.setBalance(100000f);
        user.setWallet(new Wallet());
        user.getWallet().setByName(10f, "bitcoin");

        when(cryptoCoinService.getLastPriceByName("bitcoin")).thenReturn(10000f);

        tradingService.sellCoins(user, "bitcoin", 2.5);

        assertEquals(7.5f, user.getWallet().getBitcoin(), 0.001f);
        assertEquals(125000f, user.getBalance(), 0.001f);
        verify(userService).updateUser(user);
        }

}
