package edu.geekhub.Service;

import edu.geekhub.Entity.Transfer;
import edu.geekhub.Repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferService {
    @Autowired
    TransferRepository transferRepository;

    public void addTransfer(Transfer transfer) {
        transferRepository.addTransfer(transfer);
    }

    public List<Transfer> getTransfersByUserId(int userId) {
        return transferRepository.getTransfersByUserId(userId);
    }

    public List<Transfer> getTransfersByUserIdAndCoinName(int userId, String coinName) {
        return transferRepository.getTransfersByUserIdAndCoinName(userId,coinName);
    }
}
