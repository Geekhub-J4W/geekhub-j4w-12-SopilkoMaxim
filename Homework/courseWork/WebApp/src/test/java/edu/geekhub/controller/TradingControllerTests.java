package edu.geekhub.controller;


import edu.geekhub.Entity.Transfer;
import edu.geekhub.Entity.User;
import edu.geekhub.Entity.Wallet;
import edu.geekhub.Service.CryptoCoinService;
import edu.geekhub.Service.TransferService;
import edu.geekhub.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(TradingController.class)
@WithMockUser
public class TradingControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @MockBean
    private CryptoCoinService cryptoCoinService;

    @MockBean
    private TransferService transferService;

    @Test
    public void testGetBtcInfo() throws Exception {
        User user = new User();
        user.setBalance(10000);
        user.setWallet(new Wallet());
        user.getWallet().setBitcoin(1);
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer());
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(cryptoCoinService.getLastPriceByName(anyString())).thenReturn(10000f);
        when(transferService.getTransfersByUserIdAndCoinName(anyInt(), anyString())).thenReturn(transfers);


        mockMvc.perform(MockMvcRequestBuilders.get("/btc-info"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastPrice").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.btc_amount").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transfers").exists());
    }

    @Test
    public void testBuyBtcCoin() throws Exception {
        User user = new User();
        user.setWallet(new Wallet());
        user.setBalance(10000);
        when(cryptoCoinService.getLastPriceByName("bitcoin")).thenReturn(1000f);
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.post("/buy-btc")
                        .param("amount", "10"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/btc.html"));

        assertEquals(user.getWallet().getBitcoin(), 10);
        assertEquals(user.getBalance(), 0);

    }

    @Test
    public void testSellBtcCoin() throws Exception {
        User user = new User();
        user.setWallet(new Wallet());
        user.getWallet().setBitcoin(10);
        when(cryptoCoinService.getLastPriceByName("bitcoin")).thenReturn(1000f);
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.post("/sell-btc")
                        .param("amount", "10"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/btc.html"));

        assertEquals(user.getWallet().getBitcoin(), 0);
        assertEquals(user.getBalance(), 10000);

    }
}
