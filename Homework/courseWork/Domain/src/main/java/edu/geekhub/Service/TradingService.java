package edu.geekhub.Service;

import edu.geekhub.Entity.Transfer;
import edu.geekhub.Entity.User;
import edu.geekhub.Entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TradingService {
    @Autowired
    private UserService userService;

    @Autowired
    private CryptoCoinService cryptoCoinService;

    @Autowired
    private TransferService transferService;
    public void buyCoins(User user, String coinName, double quantity) {
        user.getWallet().setByName(user.getWallet().getBitcoin()+(float) quantity,coinName);
        float updateBalace = (float) (user.getBalance() - (cryptoCoinService.getLastPriceByName(coinName)*quantity));
        user.setBalance(updateBalace);
        userService.updateUser(user);
        transferService.addTransfer(
                new Transfer(coinName,quantity, LocalDateTime.now(),cryptoCoinService.getLastPriceByName(coinName)*quantity,user.getId(),"Buy"));
    }

    public void sellCoins(User user, String coinName, double amount) {
        float updateBalace = (float) (user.getBalance() + (cryptoCoinService.getLastPriceByName(coinName)*amount));
        user.getWallet().setByName(user.getWallet().getBitcoin()-(float) amount,coinName);
        user.setBalance(updateBalace);
        userService.updateUser(user);
        transferService.addTransfer(
                new Transfer(coinName,amount, LocalDateTime.now(),cryptoCoinService.getLastPriceByName(coinName)*amount,user.getId(),"Sell"));

    }
}
