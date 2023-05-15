package edu.geekhub.controller;

import edu.geekhub.Service.CryptoCoinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CoinController.class)
@WithMockUser
class CoinControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptoCoinService cryptoCoinService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getBtcData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("Bitcoin")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/btc").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getSolanaData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("solana")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/solana").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getEthData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("ethereum")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/eth").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getBnbData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("bnb")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/bnb").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));

    }

    @Test
    void getXrpData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("xrp")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/xrp").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getLseData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("lido_staked_ether")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/lse").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getTetherData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("tether")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/tether").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getUsdData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("usd_coin")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/usd_coin").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getCardanoData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("cardano")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/cardano").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }

    @Test
    void getDogecoinData() throws Exception {
        Map<String, Float> data = new HashMap<>();
        data.put("data", 42000.0f);
        when(cryptoCoinService.getByName("dogecoin")).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/dogecoin").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)));
    }
}