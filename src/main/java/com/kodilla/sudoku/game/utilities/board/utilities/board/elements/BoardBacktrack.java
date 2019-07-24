package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import com.kodilla.sudoku.game.utilities.board.utilities.SudokuField;

public class BoardBacktrack {
    private SudokuBoard sudokuBoard;
    private SudokuField sudokuField;

    public BoardBacktrack(SudokuBoard sudokuBoard, SudokuField sudokuField) {
        this.sudokuBoard = sudokuBoard;
        this.sudokuField = sudokuField;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public SudokuField getSudokuField() {
        return sudokuField;
    }
}
