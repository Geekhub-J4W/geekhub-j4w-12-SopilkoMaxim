package edu.geekhub.orcostat.model;

import edu.geekhub.orcostat.OrcoStatService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrcTest {
    @Test
    void default_orc_price_is_lada_vesta_sport() {
        final Orc orc = new Orc(5,"22.10.22");

        assertEquals(10_000, orc.getPrice());
    }

    @Test
    void try_to_count_two_orcs() {
        OrcoStatService orcoStatService= new OrcoStatService();
        final Orc orc = new Orc(2,"22.10.22");

        assertEquals(20_000, orcoStatService.getLosesInDollars());
    }
}