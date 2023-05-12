package edu.geekhub.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.geekhub.Entity.Transfer;
import edu.geekhub.Repository.TransferRepository;

public class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    private TransferService transferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transferService = new TransferService();
        transferService.transferRepository = transferRepository;
    }

    @Test
    public void testAddTransfer() {
        Transfer transfer = new Transfer("BTC", 1.0, LocalDateTime.now(), 50000f, 1, "Buy");
        transferService.addTransfer(transfer);
        verify(transferRepository).addTransfer(transfer);
    }

    @Test
    public void testGetTransfersByUserId() {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer("BTC", 1.0, LocalDateTime.now(), 50000f, 1, "Buy");
        Transfer transfer2 = new Transfer("BTC", 2.0, LocalDateTime.now(), 75000f, 1, "Sell");
        transfers.add(transfer1);
        transfers.add(transfer2);

        when(transferRepository.getTransfersByUserId(1)).thenReturn(transfers);

        List<Transfer> result = transferService.getTransfersByUserId(1);
        assertEquals(transfers, result);
    }

    @Test
    public void testGetTransfersByUserIdAndCoinName() {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer("BTC", 1.0, LocalDateTime.now(), 50000f, 1, "Buy");
        Transfer transfer2 = new Transfer("BTC", 2.0, LocalDateTime.now(), 75000f, 1, "Sell");
        transfers.add(transfer1);
        transfers.add(transfer2);

        when(transferRepository.getTransfersByUserIdAndCoinName(1, "BTC")).thenReturn(transfers);

        List<Transfer> result = transferService.getTransfersByUserIdAndCoinName(1, "BTC");
        assertEquals(transfers, result);
    }
}
