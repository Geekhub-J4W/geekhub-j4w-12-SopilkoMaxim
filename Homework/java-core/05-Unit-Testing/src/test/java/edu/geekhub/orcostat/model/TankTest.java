package edu.geekhub.orcostat.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TankTest {
    @Test
    void can_build_tank() {
        new Tank(5, "22.10.22");
    }

    @Test
    void default_tank_price_is_3_000_000() {
        Tank tank = new Tank(5, "22.10.22");

        assertEquals(3_000_000, tank.getPrice());
    }

    @Test
    void empty_tank_has_zero_equipage() {
        Tank tank = new Tank(5, "22.10.22");

        int count = tank.getEquipage().count();

        assertEquals(0, count);
    }

}