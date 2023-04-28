package edu.geekhub.controller;


import edu.geekhub.Service.CryptoCoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/")
public class CoinController {

    private CryptoCoinService cryptoCoinService;

    public CoinController(CryptoCoinService cryptoCoinService) {
        this.cryptoCoinService = cryptoCoinService;
    }

    @GetMapping("/btc")
    public String getBtcData(Model model) {
        Map<Date, Float> btcData = cryptoCoinService.getByName("Bitcoin");
        model.addAttribute("btcData", btcData);
        return "btc";
    }
}
