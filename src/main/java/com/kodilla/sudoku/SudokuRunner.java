package com.kodilla.sudoku;

import com.kodilla.sudoku.game.SudokuManager;

public class SudokuRunner {
    private static SudokuManager sudokuManager = new SudokuManager();

    public static void main(String[] args ) {
        sudokuManager.runSudoku();
    }
}
