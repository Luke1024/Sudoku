package com.kodilla.sudoku.game;

import com.kodilla.sudoku.game.utilities.io.IOManager;
import com.kodilla.sudoku.game.utilities.solver.SudokuSolver;

public class SudokuManager {
    private static IOManager ioManager = new IOManager();

    public void runSudoku(){
        gatherInput();
        solveSudoku();
        showResults();
    }

    private void gatherInput(){
        ioManager.gatherInputFromUser();
    }

    private void solveSudoku(){
        System.out.println("Starting sudoku solver ....");
        SudokuSolver sudokuSolver = new SudokuSolver(ioManager.getBoardContainer());
        sudokuSolver.solveSudoku();
        ioManager.setBoardContainer(sudokuSolver.getBoardContainer());
    }

    private void showResults(){
        System.out.println("Showing board .... ");
        ioManager.showBoard();
    }
}
