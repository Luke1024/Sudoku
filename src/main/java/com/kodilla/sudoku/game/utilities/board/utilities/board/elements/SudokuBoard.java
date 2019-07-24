package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import com.kodilla.sudoku.game.utilities.board.utilities.FieldCoord;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard extends Prototype {
    private List<SudokuRow> board = new ArrayList<>();

    public SudokuBoard() {
        for(int i = 0; i<9; i++) {
            this.board.add(new SudokuRow());
        }
    }

    public List<SudokuRow> getBoard() {
        return board;
    }

    public SudokuElement getElement(int x, int y) {
        return board.get(x).getElement(y);
    }

    public void setElement(int x, int y, SudokuElement sudokuElement) {
        this.board.get(x).setElement(y, sudokuElement);
    }

    public List<FieldCoord> getEmptyFieldCoordsList(){
        List<FieldCoord> emptyFieldCoords = new ArrayList<>();
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                if(getElement(x,y).getValue() == SudokuElement.EMPTY_VALUE){
                    emptyFieldCoords.add(new FieldCoord(x,y));
                }
            }
        }
        return emptyFieldCoords;
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException {
        SudokuBoard clonedBoard = new SudokuBoard();
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                clonedBoard.setElement(x,y, new SudokuElement(getElement(x,y).getValue()));
            }
        }
        return clonedBoard;
    }

    @Override
    public String toString() {
        String boardToString = "";
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                int value = getElement(x,y).getValue();
                if(value == -1){
                    boardToString += "  ";
                } else {
                    boardToString += value + " ";
                }
            }
            boardToString += "\n";
        }
        boardToString = setGrid(boardToString);

        return boardToString;
    }

    private String setGrid(String board){
        board = setHorizontalLines(board);
        board = setVerticalLines(board);
        return board;
    }

    private String setHorizontalLines(String board){
        String line = "| ";
        int index = 0;
        for(int x=0; x<9; x++){
            board = insertString(board, line, index);
            board = insertString(board, line, index + 8);
            board = insertString(board, line, index + 16);
            board = insertString(board, line, index + 24);
            index = index + 27;
        }
        return board;
    }

    private String setVerticalLines(String board){
        String line ="-------------------------\n";
        int index = 0;
        for(int x=0; x<4; x++){
            board = insertString(board, line, index);
            index = index + 107;
        }
        return board;
    }

    private String insertString(String string, String insertedString, int index){
        StringBuffer newString = new StringBuffer(string);

        newString.insert(index, insertedString);

        return newString.toString();
    }
}
