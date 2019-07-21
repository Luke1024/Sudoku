package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

public class Prototype<T> implements Cloneable {
    @Override
    public T clone() throws CloneNotSupportedException {
        return (T)super.clone();
    }
}