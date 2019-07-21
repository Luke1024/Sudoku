package com.kodilla.sudoku.game.utilities.board;

import org.junit.Test;

public class BoardContainerTest {
    BoardContainer boardContainer = new BoardContainer();

    int input[][]={{0,0,0,2,0,0,0,6,3},
            {3,0,0,0,0,5,4,0,1},
            {0,0,1,0,0,3,9,8,0},
            {0,0,1,0,0,3,9,8,0},
            {0,0,0,0,0,0,0,9,0},
            {0,3,0,0,0,0,0,0,0},
            {0,2,6,3,0,0,5,0,0},
            {5,0,3,7,0,0,0,0,8},
            {4,7,0,0,0,1,0,0,0}};

    @Test
    public void testCreateBoardFromArray(){
        boardContainer.createBoardFromArray(input);
        for(int x=0; x<9;x++){
            for(int y=0; y<9; y++){
                System.out.println(boardContainer.getSudokuBoard().getElement(x,y).getValue());
            }
        }
    }
}