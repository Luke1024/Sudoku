package com.kodilla.sudoku.game.utilities.solver;
import com.kodilla.sudoku.game.utilities.board.BoardContainer;
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
        resetIterationCounter();
        setSolvingStatusToInProgress();
        while( ! isBoardFull()) {
            countIteration();
            if( ! tryToFillSingleField()){
                break;
            }
        }
        setSolvingStatusToSolved(); //this is not correct
    }

    private boolean tryToFillSingleField(){
        boolean progress = true;
        removeNonAvailableElements();

        if( ! setNewValueIfPossible()) {
            if(! guessValue()){
                progress = loadBackTrack();
            }
        }
        return progress;
    }

    private boolean loadBackTrack(){
        if (!isBacktrackEmpty()) {
            loadBoardFromBacktrack();
            return true;
        } else {
            setSolvingStatusToImpossible();
            return false;
        }
    }

    private void countIteration(){
        int count = this.boardContainer.getIterationCounter();
        count++;
        this.boardContainer.setIterationCounter(count);
    }

    private void resetIterationCounter(){
        this.boardContainer.setIterationCounter(0);
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

    private boolean guessValue() {

        List<SudokuField> emptyFieldSpaceAvailableForGuessing = this.boardContainer.getFieldSpaceAvailableForGuessing();

        if ( ! isBoardFull()) {

            SudokuField pickedField = emptyFieldSpaceAvailableForGuessing
                    .get(randomGenerator.nextInt(emptyFieldSpaceAvailableForGuessing.size()));


            saveBoardInBacktrack();

            this.boardContainer.getSudokuBoard().setElement(
                    pickedField.getFieldCoord().getX(),
                    pickedField.getFieldCoord().getY(),
                    new SudokuElement(pickedField.getValue()));
        }

        if (checkIfBoardIsOkSoFar()){
            return true;
        } else {
            return false;
        }
    }

    private boolean isBacktrackEmpty(){
        return this.boardContainer.isBacktrackEmpty();
    }

    public void loadBoardFromBacktrack() {
        BoardBacktrack boardBacktrack = this.boardContainer.getLastBacktrackAndDelete();
        this.boardContainer.setSudokuBoard(boardBacktrack.getSudokuBoard());
    }

    private void saveBoardInBacktrack() {
        try{
            SudokuBoard sudokuBoard = boardContainer.getSudokuBoard().deepCopy();
            this.boardContainer.addBacktrack(
                    new BoardBacktrack(sudokuBoard));
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
    }
}
