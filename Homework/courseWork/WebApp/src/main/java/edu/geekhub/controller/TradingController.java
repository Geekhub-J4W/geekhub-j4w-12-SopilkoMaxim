package edu.geekhub.controller;

import edu.geekhub.Entity.Transfer;
import edu.geekhub.Entity.User;
import edu.geekhub.Service.CryptoCoinService;
import edu.geekhub.Service.TradingService;
import edu.geekhub.Service.TransferService;
import edu.geekhub.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        User user = getUser();
        Map<String, Object> btcInfo = new HashMap<>();
        btcInfo.put("balance", user.getBalance());
        btcInfo.put("lastPrice", cryptoCoinService.getLastPriceByName("bitcoin"));
        btcInfo.put("btc_amount", user.getWallet().getBitcoin());

        List<Transfer> transfers = transferService.getTransfersByUserIdAndCoinName(getUser().getId(),"bitcoin");
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
        btcInfo.put("transfers", transferData);

        return ResponseEntity.ok().body(btcInfo);
    }


    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserByEmail(email).get();
    }


    @PostMapping("/buy-btc")
    public ResponseEntity buyBtcCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.buyCoins(getUser(),"bitcoin", amount);
        response.sendRedirect("/btc.html");
        return null;
    }

    @PostMapping("/sell-btc")
    public ResponseEntity sellBtcCoin(@RequestParam double amount, HttpServletResponse response) throws IOException {
        tradingService.sellCoins(getUser(),"bitcoin", amount);
        response.sendRedirect("/btc.html");
        return null;
    }
    @GetMapping("/transfers")
    public List<Transfer> getAllTransfersForCurrentUser() {
        return transferService.getTransfersByUserId(getUser().getId());
    }

}
