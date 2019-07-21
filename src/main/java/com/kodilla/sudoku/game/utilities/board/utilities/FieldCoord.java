package com.kodilla.sudoku.game.utilities.board.utilities;

public class FieldCoord {
    private int x;
    private int y;

    public FieldCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
