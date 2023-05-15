package edu.geekhub.controller;

import edu.geekhub.Entity.Transfer;
import edu.geekhub.Entity.User;
import edu.geekhub.Service.CryptoCoinService;
import edu.geekhub.Service.TradingService;
import edu.geekhub.Service.TransferService;
import edu.geekhub.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TradingController {

    @Autowired
    private UserService userService;

    @Autowired
    private CryptoCoinService cryptoCoinService;

    @Autowired
    private TradingService tradingService;

    @Autowired
    private TransferService transferService;

    @GetMapping("/btc-info")
    public ResponseEntity<Map<String, Object>> getBtcInfo() {
        Map<String, Object> btcInfo = getStringObjectMap("bitcoin");

        return ResponseEntity.ok().body(btcInfo);
    }

    @GetMapping("/bnb-info")
    public ResponseEntity<Map<String, Object>> getBnbInfo() {
        Map<String, Object> bnbInfo = getStringObjectMap("bnb");

        return ResponseEntity.ok().body(bnbInfo);
    }

    @GetMapping("/cardano-info")
    public ResponseEntity<Map<String, Object>> getCardanoInfo() {
        Map<String, Object> cardanoInfo = getStringObjectMap("cardano");
        return ResponseEntity.ok().body(cardanoInfo);
    }

    @GetMapping("/dogecoin-info")
    public ResponseEntity<Map<String, Object>> getDogecoinInfo() {
        Map<String, Object> info = getStringObjectMap("dogecoin");
        return ResponseEntity.ok().body(info);
    }
    @GetMapping("/eth-info")
    public ResponseEntity<Map<String, Object>> getEthInfo() {
        Map<String, Object> info = getStringObjectMap("ethereum");
        return ResponseEntity.ok().body(info);
    }
    @GetMapping("/lse-info")
    public ResponseEntity<Map<String, Object>> getLseInfo() {
        Map<String, Object> info = getStringObjectMap("Lido_Staked_Ether");
        return ResponseEntity.ok().body(info);
    }
    @GetMapping("/solana-info")
    public ResponseEntity<Map<String, Object>> getSolanaInfo() {
        Map<String, Object> info = getStringObjectMap("solana");
        return ResponseEntity.ok().body(info);
    }

    @GetMapping("/tether-info")
    public ResponseEntity<Map<String, Object>> getTetherInfo() {
        Map<String, Object> info = getStringObjectMap("tether");
        return ResponseEntity.ok().body(info);
    }
    @GetMapping("/xrp-info")
    public ResponseEntity<Map<String, Object>> getXrpInfo() {
        Map<String, Object> info = getStringObjectMap("xrp");
        return ResponseEntity.ok().body(info);
    }
    @GetMapping("/usd_coin-info")
    public ResponseEntity<Map<String, Object>> getUsdInfo() {
        Map<String, Object> info = getStringObjectMap("usd_coin");
        return ResponseEntity.ok().body(info);
    }
    private Map<String, Object> getStringObjectMap(String name) {
        User user = getUser();
        Map<String, Object> info = new HashMap<>();
        info.put("balance", user.getBalance());
        info.put("lastPrice", cryptoCoinService.getLastPriceByName(name));
        String nameAmoun = name+"_amount";
        if(name.equals("ethereum")) nameAmoun="eth_amount";
        if(name.equals("Lido_Staked_Ether")) nameAmoun="lse_amount";
        if(name.equals("bitcoin")) nameAmoun="btc_amount";

        info.put(nameAmoun, user.getWallet().returnByName(name));
        List<Transfer> transfers = transferService.getTransfersByUserIdAndCoinName(getUser().getId(), name);
        List<Map<String, Object>> transferData = new ArrayList<>();
        for (Transfer transfer : transfers) {
            Map<String, Object> transferMap = new HashMap<>();
            transferMap.put("coinName", transfer.getCoinName());
            transferMap.put("amount", transfer.getAmount());
            transferMap.put("date", transfer.getDate());
            transferMap.put("price", transfer.getPrice());
            transferMap.put("operation", transfer.getOperation());
            transferData.add(transferMap);
        }
        info.put("transfers", transferData);
        return info;
    }


    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserByEmail(email).get();
    }


    @PostMapping("/buy-btc")
    public ResponseEntity buyBtcCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "bitcoin", amount);
        response.sendRedirect("/btc.html");
        return null;
    }

    @PostMapping("/sell-btc")
    public ResponseEntity sellBtcCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "bitcoin", amount);
        response.sendRedirect("/btc.html");
        return null;
    }

    @PostMapping("/buy-bnb")
    public ResponseEntity buyBnbCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "bnb", amount);
        response.sendRedirect("/bnb.html");
        return null;
    }

    @PostMapping("/sell-bnb")
    public ResponseEntity sellBnbCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "bnb", amount);
        response.sendRedirect("/bnb.html");
        return null;
    }

    @PostMapping("/buy-cardano")
    public ResponseEntity buyCardanoCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "cardano", amount);
        response.sendRedirect("/cardano.html");
        return null;
    }

    @PostMapping("/sell-cardano")
    public ResponseEntity sellCardanoCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "cardano", amount);
        response.sendRedirect("/cardano.html");
        return null;
    }

    @PostMapping("/buy-dogecoin")
    public ResponseEntity buyDogecoinCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "dogecoin", amount);
        response.sendRedirect("/dogecoin.html");
        return null;
    }

    @PostMapping("/sell-dogecoin")
    public ResponseEntity sellDogecoinCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "dogecoin", amount);
        response.sendRedirect("/dogecoin.html");
        return null;
    }
    @PostMapping("/buy-eth")
    public ResponseEntity buyEthCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "ethereum", amount);
        response.sendRedirect("/eth.html");
        return null;
    }

    @PostMapping("/sell-eth")
    public ResponseEntity sellEthCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "ethereum", amount);
        response.sendRedirect("/eth.html");
        return null;
    }
    @PostMapping("/buy-lse")
    public ResponseEntity buyLseCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "Lido_Staked_Ether", amount);
        response.sendRedirect("/lse.html");
        return null;
    }

    @PostMapping("/sell-lse")
    public ResponseEntity sellLseCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "Lido_Staked_Ether", amount);
        response.sendRedirect("/lse.html");
        return null;
    }
    @PostMapping("/buy-solana")
    public ResponseEntity buySolanaCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "solana", amount);
        response.sendRedirect("/solana.html");
        return null;
    }

    @PostMapping("/sell-solana")
    public ResponseEntity sellSolanaCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "solana", amount);
        response.sendRedirect("/solana.html");
        return null;
    }
    @PostMapping("/buy-tether")
    public ResponseEntity buyTetherCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "tether", amount);
        response.sendRedirect("/tether.html");
        return null;
    }

    @PostMapping("/sell-tether")
    public ResponseEntity sellTetherCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "tether", amount);
        response.sendRedirect("/tether.html");
        return null;
    }

    @PostMapping("/buy-xrp")
    public ResponseEntity buyXrpCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "xrp", amount);
        response.sendRedirect("/xrp.html");
        return null;
    }

    @PostMapping("/sell-xrp")
    public ResponseEntity sellXrpCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "xrp", amount);
        response.sendRedirect("/xrp.html");
        return null;
    }
    @PostMapping("/buy-usd_coin")
    public ResponseEntity buyUsdCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(), "usd_coin", amount);
        response.sendRedirect("/usd.html");
        return null;
    }

    @PostMapping("/sell-usd_coin")
    public ResponseEntity sellUsdCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(), "usd_coin", amount);
        response.sendRedirect("/usd.html");
        return null;
    }
    @GetMapping("/transfers")
    public List<Transfer> getAllTransfersForCurrentUser() {
        return transferService.getTransfersByUserId(getUser().getId());
    }

}
