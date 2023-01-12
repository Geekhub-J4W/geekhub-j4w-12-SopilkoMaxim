package edu.geekhub.orcostat;

import edu.geekhub.orcostat.model.Orc;
import edu.geekhub.orcostat.model.Tank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrcoStatTest {

    private OrcoStatService orcoStatService;

    @BeforeEach
    void setUp() {
        orcoStatService = new OrcoStatService();
    }

    @Test
    void can_count_negatively_alive_orc() {
        int count = orcoStatService.getNegativelyAliveOrcCount();

        assertEquals(0, count);
    }

    @Test
    void can_add_negatively_alive_orc() {
        orcoStatService.addNegativelyAliveOrc(new Orc("20.10.22", 5));

        int count = orcoStatService.getNegativelyAliveOrcCount();

        assertEquals(1, count);
    }

    @Test
    void can_add_multiple_negatively_alive_orcs() {
        orcoStatService.addNegativelyAliveOrc(new Orc("22.10.22", 5));
        orcoStatService.addNegativelyAliveOrc(new Orc("22.10.22", 5));

        int count = orcoStatService.getTotalDeadOrcs();

        assertEquals(10, count);
    }

    @Test
    void can_count_destroyed_tanks() {
        int count = orcoStatService.getDestroyedTanksCount();

        assertEquals(0, count);
    }

    @Test
    void can_add_destroyed_tank_without_orc() {
        orcoStatService.addDestroyedTank(new Tank("22.10.22", 5));

        int count = orcoStatService.getTotalDeadTanks();

        assertEquals(5, count);
    }



    @Test
    void can_count_orcs_loses_in_dollars() {
        int damage = orcoStatService.getLosesInDollars();

        assertEquals(0, damage);
    }

    @Test
    void can_sum_orc_loses_in_dollars() {
        orcoStatService.addNegativelyAliveOrc(new Orc("22.10.22", 1));

        int damage = orcoStatService.getLosesInDollars();

        assertEquals(10_000, damage);
    }

    @Test
    void can_sum_tank_loses_in_dollars() {
        orcoStatService.addDestroyedTank(new Tank("22.10.22", 1));

        int losesInDollars = orcoStatService.getLosesInDollars();

        assertEquals(3_000_000, losesInDollars);
    }



    @Test
    void can_sum_tank_and_lost_orc_loses_cost_in_dollars() {

        orcoStatService.addDestroyedTank(new Tank("22.10.22", 1));
        orcoStatService.addNegativelyAliveOrc(new Orc("22.10.22", 2));

        int losesInDollars = orcoStatService.getLosesInDollars();

        assertEquals(3_020_000, losesInDollars);
    }
}
