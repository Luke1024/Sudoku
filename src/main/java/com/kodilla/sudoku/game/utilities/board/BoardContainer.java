package com.kodilla.sudoku.game.utilities.board;

import com.kodilla.sudoku.game.utilities.board.utilities.FieldCoord;
import com.kodilla.sudoku.game.utilities.board.utilities.SudokuField;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.BoardBacktrack;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuBoard;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuElement;
import com.kodilla.sudoku.game.utilities.solver.utilities.SolverStatus;

import java.util.ArrayList;
import java.util.List;

public class BoardContainer {
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private List<BoardBacktrack> boardBacktrack = new ArrayList<>();
    private SolverStatus solverStatus;

    public void createBoardFromArray(int[][] boardArray)  {
        if(checkIfArrayIsOk(boardArray)) {
            createBoard(boardArray);
        } else {
            System.out.println("Incorrect array size");
        }
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

    public List<SudokuField> getFieldSpaceAvailableForGuessing(){
        List<FieldCoord> emptyFields = sudokuBoard.getEmptyFieldCoordsList();
        return processFieldCoordToFieldSpace(emptyFields);
    }

    private List<SudokuField> processFieldCoordToFieldSpace(List<FieldCoord> emptyFields) {
        List<SudokuField> sudokuFieldSpace = new ArrayList<>();
        for (FieldCoord emptyField : emptyFields) {
            for (int i = 0; i < 9; i++) {
                sudokuFieldSpace.add(new SudokuField(i + 1, new FieldCoord(emptyField.getX(), emptyField.getY())));
            }
        }
        return sudokuFieldSpace;
    }

    public BoardBacktrack getLastBacktrackAndDelete() {
        BoardBacktrack backtrack = this.boardBacktrack.get(boardBacktrack.size()-1);
        this.boardBacktrack.remove(boardBacktrack.size()-1);
        return backtrack;
    }

    public void addBacktrack(BoardBacktrack boardBacktrack) {
        this.boardBacktrack.add(boardBacktrack);
    }

    private boolean checkIfArrayIsOk(int[][]boardArray) {
        if(boardArray.length == 9 && boardArray[0].length == 9)return true;
        else return false;
    }

    private void createBoard(int[][]boardArray){
        setSudokuBoard(translateBoardFromArrayToBoard(boardArray));
    }

    private SudokuBoard translateBoardFromArrayToBoard(int[][]boardArray) {
        SudokuBoard sudokuBoard = new SudokuBoard();
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                sudokuBoard.setElement(x,y,new SudokuElement(filterIncorrectValuesAsEmpty(boardArray[x][y])));
            }
        }
        return sudokuBoard;
    }

    private int filterIncorrectValuesAsEmpty(int value){
        if(value >= 1 && value <= 9){
            return value;
        } else return SudokuElement.EMPTY_VALUE;
    }

    public boolean isBacktrackEmpty(){
        return this.boardBacktrack.isEmpty();
    }
}
