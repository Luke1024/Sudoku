package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {

    private List<SudokuElement> row = new ArrayList<>();

    public SudokuRow() {
        for(int i=0; i<9; i++) {
            row.add(new SudokuElement(SudokuElement.EMPTY_VALUE));
        }
    }

    public SudokuElement getElement(int index) {
        return row.get(index);
    }

    public void setElement(int index, SudokuElement sudokuElement) {
        row.set(index, sudokuElement);
    }
}
