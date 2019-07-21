package com.kodilla.sudoku.game.utilities.board.utilities.board.elements;

import com.kodilla.sudoku.game.utilities.board.BoardContainer;
import org.junit.Assert;
import org.junit.Test;

public class SudokuBoardTest {
    @Test
    public void shouldAddGrid(){
        BoardContainer boardContainer = new BoardContainer();

        int input[][]= {{9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9}};

        boardContainer.createBoardFromArray(input);

        String boardWithNines = "";
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                boardWithNines += 9 + " ";
            }
            boardWithNines += "\n";
        }
        boardWithNines = setGrid(boardWithNines);

        Assert.assertEquals(boardContainer.getSudokuBoard().toString(), boardWithNines);
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