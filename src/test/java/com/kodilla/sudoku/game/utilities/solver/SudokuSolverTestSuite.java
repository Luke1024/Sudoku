package com.kodilla.sudoku.game.utilities.solver;

import com.kodilla.sudoku.game.utilities.board.BoardContainer;
import org.junit.Assert;
import org.junit.Test;

public class SudokuSolverTestSuite {

    @Test
    public void shouldResolveSudoku() {

        int input_1[][]= {{0,0,0,2,6,0,7,0,1},
                        {6,8,0,0,7,0,0,9,0},
                        {1,9,0,0,0,4,5,0,0},
                        {8,2,0,1,0,0,0,4,0},
                        {0,0,4,6,0,2,9,0,0},
                        {0,5,0,0,0,3,0,2,8},
                        {0,0,9,3,0,0,0,7,4},
                        {0,4,0,0,5,0,0,3,6},
                        {7,0,3,0,1,8,0,0,0}};

        int output_1[][]= {{4,3,5,2,6,9,7,8,1},
                         {6,8,2,5,7,1,4,9,3},
                         {1,9,7,8,3,4,5,6,2},
                         {8,2,6,1,9,5,3,4,7},
                         {3,7,4,6,8,2,9,1,5},
                         {9,5,1,7,4,3,6,2,8},
                         {5,1,9,3,2,6,8,7,4},
                         {2,4,8,9,5,7,1,3,6},
                         {7,6,3,4,1,8,2,5,9}};


        int input_2[][]= {{1,0,0,4,8,9,0,0,6},
                         {7,3,0,0,0,0,0,4,0},
                         {0,0,0,0,0,1,2,9,5},
                         {0,0,7,1,2,0,6,0,0},
                         {5,0,0,7,0,3,0,0,8},
                         {0,0,6,0,9,5,7,0,0},
                         {9,1,4,6,0,0,0,0,0},
                         {0,2,0,0,0,0,0,3,7},
                         {8,0,0,5,1,2,0,0,4}};

        int output_2[][]= {{1,5,2,4,8,9,3,7,6},
                          {7,3,9,2,5,6,8,4,1},
                          {4,6,8,3,7,1,2,9,5},
                          {3,8,7,1,2,4,6,5,9},
                          {5,9,1,7,6,3,4,2,8},
                          {2,4,6,8,9,5,7,1,3},
                          {9,1,4,6,3,7,5,8,2},
                          {6,2,5,9,4,8,1,3,7},
                          {8,7,3,5,1,2,9,6,4}};

        BoardContainer boardContainerWithInput_1 = new BoardContainer();
        BoardContainer boardContainerWithInput_2 = new BoardContainer();

        boardContainerWithInput_1.createBoardFromArray(input_1);
        boardContainerWithInput_2.createBoardFromArray(input_2);

        SudokuSolver sudokuSolver_1 = new SudokuSolver(boardContainerWithInput_1);
        SudokuSolver sudokuSolver_2 = new SudokuSolver(boardContainerWithInput_2);

        sudokuSolver_1.solveSudoku();
        sudokuSolver_2.solveSudoku();

        BoardContainer boardContainerWithOutput_1 = new BoardContainer();
        BoardContainer boardContainerWithOutput_2 = new BoardContainer();

        boardContainerWithOutput_1.createBoardFromArray(output_1);
        boardContainerWithOutput_2.createBoardFromArray(output_2);

        Assert.assertEquals(sudokuSolver_1.getBoardContainer().getSudokuBoard().toString(),
                boardContainerWithOutput_1.getSudokuBoard().toString());
        Assert.assertEquals(sudokuSolver_2.getBoardContainer().getSudokuBoard().toString(),
                boardContainerWithOutput_2.getSudokuBoard().toString());

        System.out.println("Iterations: " + sudokuSolver_1.getBoardContainer().getIterationCounter());
        System.out.println(sudokuSolver_1.getBoardContainer().getSudokuBoard().toString());
        System.out.println();
        System.out.println("Iterations: " + sudokuSolver_2.getBoardContainer().getIterationCounter());
        System.out.println(sudokuSolver_2.getBoardContainer().getSudokuBoard().toString());
    }
}
