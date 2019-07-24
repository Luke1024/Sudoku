package com.kodilla.sudoku.game.utilities.board.utilities;

public class SudokuField {
    private int value;
    private int x;
    private int y;

    public SudokuField(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
