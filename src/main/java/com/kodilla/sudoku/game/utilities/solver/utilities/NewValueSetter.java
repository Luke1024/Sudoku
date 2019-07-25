package com.kodilla.sudoku.game.utilities.solver.utilities;

import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuBoard;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuElement;

public class NewValueSetter {
    private SudokuBoard sudokuBoard;
    private boolean newValueStatus;

    public SudokuBoard setNewValue(SudokuBoard sudokuBoard){
        this.newValueStatus = false;
        this.sudokuBoard = sudokuBoard;
        if(setNewValueIfPossible()){
            this.newValueStatus = true;
        }
        return this.sudokuBoard;
    }

    public boolean isNewValueSet(){
        return this.newValueStatus;
    }

    private boolean setNewValueIfPossible(){
        boolean newValue = false;
        if(getLowestNumberOfAvailableElements()==1){
            setNewValue();
            newValue = true;
        }
        return newValue;
    }

    public int getLowestNumberOfAvailableElements(){
        int lowestSize = 9;
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                int size = this.sudokuBoard.getElement(x,y).getAvailableNumbers().size();
                if(size < lowestSize){
                    lowestSize = size;
                }
            }
        }
        return lowestSize;
    }

    private void setNewValue(){
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(isFieldEmpty(x, y) && this.sudokuBoard.getElement(x,y).getAvailableNumbers().size()==1) {
                        this.sudokuBoard.setElement(x, y, new SudokuElement(sudokuBoard.getElement(x,y).getAvailableNumbers().get(0)));
                }
            }
        }
    }

    private boolean isFieldEmpty(int x, int y) {
        return this.sudokuBoard.getElement(x, y).getValue() == SudokuElement.EMPTY_VALUE;
    }
}
