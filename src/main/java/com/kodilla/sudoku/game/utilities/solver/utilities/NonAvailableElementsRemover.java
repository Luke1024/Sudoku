package com.kodilla.sudoku.game.utilities.solver.utilities;

import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuBoard;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuElement;

import java.util.List;
import java.util.stream.Collectors;

public class NonAvailableElementsRemover {
    private SudokuBoard sudokuBoard;

    public SudokuBoard remove(SudokuBoard sudokuBoard){
        this.sudokuBoard = sudokuBoard;
        iterateOverBoardAndRemoveNonAvailableElements();
        return this.sudokuBoard;
    }

    private void iterateOverBoardAndRemoveNonAvailableElements() {
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++){
                if(isElementEmpty(x, y)) {
                    deleteImpossibleValues(x, y);
                }
            }
        }
    }

    private boolean isElementEmpty(int x, int y) {
        return sudokuBoard.getElement(x, y).getValue() == SudokuElement.EMPTY_VALUE;
    }

    private void deleteImpossibleValues(int x, int y) {
        List<Integer> numbersToRemove = sudokuBoard.getElement(x, y).getAvailableNumbers()
                .stream().filter(n -> checkIfRemoveNumber(n, x, y)).collect(Collectors.toList());
        numbersToRemove.stream().forEach(n -> deleteNumber(n, x,y));
    }

    private boolean checkIfRemoveNumber(Integer n, int x, int y) {
        return isInRow(n, x) || isInColumn(n, y) || isInBox(n, x, y);
    }

    private void deleteNumber(Integer n, int x, int y) {
        this.sudokuBoard.getElement(x, y).getAvailableNumbers().remove(new Integer(n));
    }

    private boolean isInRow(Integer number, int x) {
        boolean contains = false;
        for (int y = 0; y < 9; y++) {
            if (sudokuBoard.getElement(x, y).getValue() == number) {
                contains = true;
            }
        }
        return contains;
    }

    private boolean isInColumn(Integer number, int y) {
        boolean contains = false;
        for (int x = 0; x < 9; x++) {
            if (sudokuBoard.getElement(x, y).getValue() == number) {
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
                if (sudokuBoard.getElement(boxX * 3 + a, boxY * 3 + b).getValue() == number) {
                    contains = true;
                }
            }
        }
        return contains;
    }
}
