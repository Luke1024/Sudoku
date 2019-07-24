package com.kodilla.sudoku.game.utilities.solver.utilities;

import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuBoard;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuElement;

public class BoardFillChecker {
    private SudokuBoard sudokuBoard;

    public boolean checkBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        return checkIfBoardIsOk();
    }

    private boolean checkIfBoardIsOk() {
        boolean ok = true;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (isElementFull(x, y) && isElementRepeats(x, y)) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    private boolean isElementFull(int x, int y){
        return this.sudokuBoard.getElement(x,y).getValue() != SudokuElement.EMPTY_VALUE;
    }

    private boolean isElementRepeats(int x, int y){
        int number = this.sudokuBoard.getElement(x,y).getValue();
        return isInBox(number,x,y) || isInRow(number,x,y) || isInColumn(number,x,y);
    }

    private boolean isInRow(Integer number, int x, int yy) {
        boolean contains = false;
        for (int y = 0; y < 9; y++) {
            if (yy != y && this.sudokuBoard.getElement(x, y).getValue() == number) {
                contains = true;
            }
        }
        return contains;
    }

    private boolean isInColumn(Integer number,int xx, int y) {
        boolean contains = false;
        for (int x = 0; x < 9; x++) {
            if(xx != x && this.sudokuBoard.getElement(x, y).getValue() == number) {
                    contains = true;
            }
        }
        return contains;
    }

    private boolean isInBox(Integer number, int x, int y) {
        int boxX = x / 3;
        int boxY = y / 3;
        boolean contains = false;

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                int xx = boxX * 3 + a;
                int yy = boxY * 3 + b;
                if (xx != x && yy != y && this.sudokuBoard.getElement(xx, yy).getValue() == number) {
                        contains = true;
                }
            }
        }
        return contains;
    }
}
