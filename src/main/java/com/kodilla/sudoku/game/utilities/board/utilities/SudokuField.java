package com.kodilla.sudoku.game.utilities.board.utilities;

public class SudokuField {
    private int value;
    private FieldCoord fieldCoord;

    public SudokuField(int value, FieldCoord fieldCoord) {
        this.value = value;
        this.fieldCoord = fieldCoord;
    }

    public int getValue() {
        return value;
    }

    public FieldCoord getFieldCoord() {
        return fieldCoord;
    }
}
