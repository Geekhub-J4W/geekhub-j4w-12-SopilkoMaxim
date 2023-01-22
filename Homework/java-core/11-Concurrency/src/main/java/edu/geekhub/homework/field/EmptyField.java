package edu.geekhub.homework.field;

import edu.geekhub.homework.players.Transport;

public class EmptyField implements Fieldable{
    @Override
    public String getFieldValue() {
        return "_";
    }
}
