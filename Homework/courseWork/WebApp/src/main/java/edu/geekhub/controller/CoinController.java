package edu.geekhub.controller;


import edu.geekhub.Service.CryptoCoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class CoinController {

    private CryptoCoinService cryptoCoinService;

    public CoinController(CryptoCoinService cryptoCoinService) {
        this.cryptoCoinService = cryptoCoinService;
    }

    @GetMapping("/btc")
    public ResponseEntity<Map<String, Float>> getBtcData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("Bitcoin"));
    }
    @GetMapping("/solana")
    public ResponseEntity<Map<String, Float>> getSolanaData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("polygon"));
    }
    @GetMapping("/eth")
    public ResponseEntity<Map<String, Float>> getEthData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("ethereum"));
    }
    @GetMapping("/bnb")
    public ResponseEntity<Map<String, Float>> getBnbData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("bnb"));
    }
    @GetMapping("/xrp")
    public ResponseEntity<Map<String, Float>> getXrpData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("xrp"));
    }

    @GetMapping("/lse")
    public ResponseEntity<Map<String, Float>> getLseData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("lido_staked_ether"));
    }
    @GetMapping("/tether")
    public ResponseEntity<Map<String, Float>> getTetherData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("tether"));
    }
    @GetMapping("/usd_coin")
    public ResponseEntity<Map<String, Float>> getUsdData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("usd_coin"));
    }

    @GetMapping("/cardano")
    public ResponseEntity<Map<String, Float>> getCardanoData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("cardano"));
    }
    @GetMapping("/dogecoin")
    public ResponseEntity<Map<String, Float>> getDogecoinData() {
        return ResponseEntity.ok(cryptoCoinService.getByName("dogecoin"));
    }
}
