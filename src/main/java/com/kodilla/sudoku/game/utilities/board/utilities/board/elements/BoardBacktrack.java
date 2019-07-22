package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import com.kodilla.sudoku.game.utilities.board.utilities.SudokuField;

import java.util.List;

public class BoardBacktrack {
    private SudokuBoard sudokuBoard;
    private SudokuField sudokuField;
    private List<SudokuField> guessingHistory;

    public BoardBacktrack(SudokuBoard sudokuBoard, SudokuField sudokuField, List<SudokuField> guessingHistory) {
        this.sudokuBoard = sudokuBoard;
        this.sudokuField = sudokuField;
        this.guessingHistory = guessingHistory;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public SudokuField getSudokuField() {
        return sudokuField;
    }

    public List<SudokuField> getGuessingHistory() {
        return guessingHistory;
    }
}
