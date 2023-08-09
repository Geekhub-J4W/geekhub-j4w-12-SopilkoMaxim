package edu.geekhub.Repository;

import com.sun.tools.javac.Main;
import edu.geekhub.Entity.Transfer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestExecutionListeners
@SpringBootTest(classes = Main.class)

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransferRepositoryTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private TransferRepository transferRepository;

    @Test
    @Sql("/schema.sql")
    public void testAddAndGetTransfers() {
        Transfer transfer = new Transfer("bitcoin", 1.5f, LocalDateTime.now(), 50000f, 1, "buy");
        transferRepository.addTransfer(transfer);
        List<Transfer> transfers = transferRepository.getTransfersByUserId(1);
        assertThat(transfers).hasSize(1);
        assertThat(transfers.get(0)).isEqualToComparingFieldByField(transfer);
    }

    @Test
    @Sql("/schema.sql")
    public void testGetTransfersByUserIdAndCoinName() {
        Transfer transfer1 = new Transfer("bitcoin", 1.5f, LocalDateTime.now(), 50000f, 1, "buy");
        Transfer transfer2 = new Transfer("ethereum", 2.5f, LocalDateTime.now(), 2500f, 1, "sell");
        Transfer transfer3 = new Transfer("bitcoin", 0.5f, LocalDateTime.now(), 55000f, 2, "buy");
        transferRepository.addTransfer(transfer1);
        transferRepository.addTransfer(transfer2);
        transferRepository.addTransfer(transfer3);

        List<Transfer> transfers = transferRepository.getTransfersByUserIdAndCoinName(1, "bitcoin");
        assertThat(transfers).hasSize(1);
        assertThat(transfers.get(0)).isEqualToComparingFieldByField(transfer1);

        transfers = transferRepository.getTransfersByUserIdAndCoinName(1, "ethereum");
        assertThat(transfers).hasSize(1);
        assertThat(transfers.get(0)).isEqualToComparingFieldByField(transfer2);

        transfers = transferRepository.getTransfersByUserIdAndCoinName(2, "bitcoin");
        assertThat(transfers).hasSize(1);
        assertThat(transfers.get(0)).isEqualToComparingFieldByField(transfer3);
    }

}
