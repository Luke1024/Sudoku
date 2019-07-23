package com.kodilla.sudoku.game.utilities.solver;

import com.kodilla.sudoku.game.utilities.board.BoardContainer;
import org.junit.Assert;
import org.junit.Test;

public class SudokuSolverTestSuite {

    @Test
    public void shouldResolveGivenSudoku() {
        BoardContainer boardContainerWithInput = new BoardContainer();

        int input[][]= {{0,0,0,2,6,0,7,0,1},
                        {6,8,0,0,7,0,0,9,0},
                        {1,9,0,0,0,4,5,0,0},
                        {8,2,0,1,0,0,0,4,0},
                        {0,0,4,6,0,2,9,0,0},
                        {0,5,0,0,0,3,0,2,8},
                        {0,0,9,3,0,0,0,7,4},
                        {0,4,0,0,5,0,0,3,6},
                        {7,0,3,0,1,8,0,0,0}};

        int output[][]= {{4,3,5,2,6,9,7,8,1},
                         {6,8,2,5,7,1,4,9,3},
                         {1,9,7,8,3,4,5,6,2},
                         {8,2,6,1,9,5,3,4,7},
                         {3,7,4,6,8,2,9,1,5},
                         {9,5,1,7,4,3,6,2,8},
                         {5,1,9,3,2,6,8,7,4},
                         {2,4,8,9,5,7,1,3,6},
                         {7,6,3,4,1,8,2,5,9}};

        boardContainerWithInput.createBoardFromArray(input);

        SudokuSolver sudokuSolver = new SudokuSolver(boardContainerWithInput);
        sudokuSolver.solveSudoku();

        BoardContainer boardContainerWithOutput = new BoardContainer();
        boardContainerWithOutput.createBoardFromArray(output);

        Assert.assertEquals(sudokuSolver.getBoardContainer().getSudokuBoard().toString(),
                boardContainerWithOutput.getSudokuBoard().toString());

        System.out.println(sudokuSolver.getBoardContainer().getSudokuBoard().toString());
    }

    @Test
    public void shouldResolveEmptySudoku() {
        BoardContainer boardContainer = new BoardContainer();
        SudokuSolver sudokuSolver = new SudokuSolver(boardContainer);

        sudokuSolver.solveSudoku();

        System.out.println("Solver status: " + sudokuSolver.getBoardContainer().getSudokuBoard().toString());
        System.out.println(sudokuSolver.getBoardContainer().getSudokuBoard().toString());
    }
}
