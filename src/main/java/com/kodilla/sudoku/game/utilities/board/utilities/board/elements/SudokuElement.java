package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuElement {

    public static final int EMPTY_VALUE = -1;
    private int value;
    private List<Integer> availableNumbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    public SudokuElement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getAvailableNumbers(){
        return availableNumbers;
    }

    public void setAvailableNumbers(List<Integer> availableNumbers) {
        this.availableNumbers = availableNumbers;
    }
}
