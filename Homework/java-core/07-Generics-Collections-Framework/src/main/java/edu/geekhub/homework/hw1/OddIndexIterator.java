package edu.geekhub.homework.hw1;

import java.util.Iterator;
import java.util.List;

public class OddIndexIterator<E> implements Iterator<E> {
    private List<E> data;
    int position = 1;

    public OddIndexIterator(List<E> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (position<data.size()){
            return true;}
        else
        {return false;}
    }

    @Override
    public E next() {
        E nextValue = data.get(position);
        position+=2;
        return nextValue;
    }
}
