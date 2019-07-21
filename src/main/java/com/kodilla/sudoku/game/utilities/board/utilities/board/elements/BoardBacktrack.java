package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import com.kodilla.sudoku.game.utilities.board.utilities.FieldCoord;

public class BoardBacktrack {
    private SudokuBoard sudokuBoard;
    private FieldCoord fieldCoord;
    private int value;

    public BoardBacktrack(SudokuBoard sudokuBoard, FieldCoord fieldCoord, int value) {
        this.sudokuBoard = sudokuBoard;
        this.fieldCoord = fieldCoord;
        this.value = value;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public FieldCoord getFieldCoord() {
        return fieldCoord;
    }

    public int getValue() {
        return value;
    }
}
