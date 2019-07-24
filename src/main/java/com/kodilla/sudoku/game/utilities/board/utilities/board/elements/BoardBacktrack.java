package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import com.kodilla.sudoku.game.utilities.board.utilities.SudokuField;

import java.util.List;

public class BoardBacktrack {
    private SudokuBoard sudokuBoard;

    public BoardBacktrack(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }
}
