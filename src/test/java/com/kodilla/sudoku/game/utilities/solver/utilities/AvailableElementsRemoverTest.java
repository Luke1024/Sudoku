package com.kodilla.sudoku.game.utilities.solver.utilities;

import com.kodilla.sudoku.game.utilities.board.BoardContainer;
import com.kodilla.sudoku.game.utilities.solver.SudokuSolver;
import org.junit.Test;

public class AvailableElementsRemoverTest {
    @Test
    public void shouldRemoveAvailableElements(){
        BoardContainer boardContainer = new BoardContainer();
        NonAvailableElementsRemover availableElementsRemover = new NonAvailableElementsRemover();

        int input[][]= {{4, 3, 5, 2, 6, 9, 7, 8, 1},
                        {6, 8, 2, 5, 7, 1, 4, 9, 3},
                        {1, 9, 7, 8, 3, 4, 5, 6, 2},
                        {8, 2, 6, 1, 9, 5, 3, 4, 7},
                        {3, 7, 4, 6, 8, 2, 9, 1, 5},
                        {9, 5, 1, -1, 4, 3, 6, 2, 8},
                        {5, 1, 9, 3, 2, 6, 8, 7, 4},
                        {2, 4, 8, 9, 5, 7, 1, 3, 6},
                        {7, 6, 3, 4, 1, 8, 2, 5, 9}};

        boardContainer.createBoardFromArray(input);
        boardContainer.setSudokuBoard(availableElementsRemover.remove(boardContainer.getSudokuBoard()));
        SudokuSolver sudokuSolver = new SudokuSolver(boardContainer);

        int input2[][]= {{1, 8, 0, 4, 6, 3, 0, 5, 9},
                         {0, 3, 2, 0, 5, 8, 0, 0, 0},
                         {0, 5, 0, 0, 0, 1, 0, 0, 0},
                         {0, 0, 1, 3, 4, 2, 0, 0, 0},
                         {0, 0, 4, 0, 0, 0, 1, 0, 0},
                         {0, 0, 0, 9, 1, 5, 6, 0, 0},
                         {0, 0, 0, 6, 0, 0, 0, 8, 0},
                         {0, 0, 0, 5, 8, 0, 3, 1, 0},
                         {8, 7, 0, 1, 2, 4, 0, 9, 6}};

        boardContainer.createBoardFromArray(input2);
        boardContainer.setSudokuBoard(availableElementsRemover.remove(boardContainer.getSudokuBoard()));
    }
}