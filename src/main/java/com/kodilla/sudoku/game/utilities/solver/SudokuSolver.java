package com.kodilla.sudoku.game.utilities.solver;

import com.kodilla.sudoku.game.utilities.board.BoardContainer;
import com.kodilla.sudoku.game.utilities.board.utilities.FieldCoord;
import com.kodilla.sudoku.game.utilities.board.utilities.SudokuField;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.BoardBacktrack;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuBoard;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuElement;
import com.kodilla.sudoku.game.utilities.solver.utilities.BoardFillChecker;
import com.kodilla.sudoku.game.utilities.solver.utilities.NewValueSetter;
import com.kodilla.sudoku.game.utilities.solver.utilities.NonAvailableElementsRemover;
import com.kodilla.sudoku.game.utilities.solver.utilities.SolverStatus;

import java.util.List;
import java.util.Random;

public class SudokuSolver {
    private NonAvailableElementsRemover nonAvailableElementsRemover = new NonAvailableElementsRemover();
    private BoardFillChecker boardFillChecker = new BoardFillChecker();
    private NewValueSetter newValueSetter = new NewValueSetter();
    private Random randomGenerator = new Random();

    private BoardContainer boardContainer;


    public SudokuSolver(BoardContainer container){
        this.boardContainer = container;
    }

    public void solveSudoku() {
        while( ! isBoardFull()) {
            if( ! tryToFillSingleField()){
                break;
            }
        }
        setSolvingStatusToSolved();
    }

    private boolean tryToFillSingleField(){
        setSolvingStatusToInProgress();
        boolean progress = true;
        removeNonAvailableElements();
        if( ! setNewValueIfPossible()) {
            guessValue();
        }
        if ( ! checkIfBoardIsOkSoFar()) {
            if (!isBacktrackEmpty()) {
                loadBoardFromBacktrack();
            } else {
                setSolvingStatusToImpossible();
                progress = false;
            }
        }
        return progress;
    }

    private boolean checkIfBoardIsOkSoFar(){
        return boardFillChecker.checkBoard(boardContainer.getSudokuBoard());
    }

    private void setSolvingStatusToSolved(){
        this.boardContainer.setSolverStatus(SolverStatus.SOLVED);
    }

    private void setSolvingStatusToImpossible(){
        this.boardContainer.setSolverStatus(SolverStatus.IMPOSSIBLE_TO_SOLVE);
    }

    private void setSolvingStatusToInProgress(){
        this.boardContainer.setSolverStatus(SolverStatus.IN_PROGRESS);
    }

    private boolean setNewValueIfPossible(){
        this.boardContainer.setSudokuBoard(newValueSetter.setNewValue(boardContainer.getSudokuBoard()));
        return newValueSetter.isNewValueSet();
    }


    private void removeNonAvailableElements(){
        this.boardContainer.setSudokuBoard(nonAvailableElementsRemover.remove(this.boardContainer.getSudokuBoard()));
    }

    public BoardContainer getBoardContainer(){
        return this.boardContainer;
    }

    private boolean isBoardFull() {
        return this.boardContainer.getSudokuBoard().getEmptyFieldCoordsList().isEmpty();
    }




    private void guessValue() {

        List<SudokuField> emptyFieldSpaceAvailableForGuessing = this.boardContainer.getFieldSpaceAvailableForGuessing();
        SudokuField pickedField = emptyFieldSpaceAvailableForGuessing.get(randomGenerator.nextInt(emptyFieldSpaceAvailableForGuessing.size()));

        saveBoardInBacktrack(pickedField);

        this.boardContainer.getSudokuBoard().setElement(
                pickedField.getFieldCoord().getX(), pickedField.getFieldCoord().getY(), new SudokuElement(pickedField.getValue()));
    }

    private boolean isBacktrackEmpty(){
        return this.boardContainer.isBacktrackEmpty();
    }

    public void loadBoardFromBacktrack() {
        BoardBacktrack boardBacktrack = boardContainer.getLastBacktrackAndDelete();
        this.boardContainer.setSudokuBoard(boardBacktrack.getSudokuBoard());
    }

    private void saveBoardInBacktrack(SudokuField pickedField) {
        try{
            SudokuBoard sudokuBoard = boardContainer.getSudokuBoard().deepCopy();
            FieldCoord fieldCoord = new FieldCoord(pickedField.getFieldCoord().getX(), pickedField.getFieldCoord().getY());
            int value = pickedField.getValue();

            this.boardContainer.addBacktrack(
                    new BoardBacktrack(sudokuBoard, fieldCoord, value));
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
    }
}
