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
            if(getIterationCounter() == 1000){
                break;
            }
        }
        setSolvingStatusToSolved();
    }

    public BoardContainer getBoardContainer(){
        return this.boardContainer;
    }


    private boolean tryToFillSingleField(){
        boolean progress = true;
        removeNonAvailableElements();
        if( ! setNewValueIfPossible()) {
            guessValue();
            if( ! checkIfBoardIsOkSoFar()) {
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
    private int getIterationCounter(){
        return this.boardContainer.getIterationCounter();
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

    private boolean isBoardFull() {
        return this.boardContainer.getSudokuBoard().getEmptyFieldCoordsList().isEmpty();
    }

    private void guessValue() {

        List<SudokuField> emptyFieldSpaceAvailableForGuessing = this.boardContainer.getFieldSpaceAvailableForGuessing();

        if ( ! isBoardFull()) {

            SudokuField pickedField = emptyFieldSpaceAvailableForGuessing
                    .get(randomGenerator.nextInt(emptyFieldSpaceAvailableForGuessing.size()));

            saveBoardInBacktrack(pickedField);

            this.boardContainer.getSudokuBoard().setElement(
                    pickedField.getX(),
                    pickedField.getY(),
                    new SudokuElement(pickedField.getValue()));
        }
    }

    private boolean isBacktrackEmpty(){
        return this.boardContainer.isBacktrackEmpty();
    }

    private void loadBoardFromBacktrack() {
        BoardBacktrack boardBacktrack = this.boardContainer.getLastBacktrackAndDelete();
        boardBacktrack = deleteAvailableField(boardBacktrack);
        this.boardContainer.setSudokuBoard(boardBacktrack.getSudokuBoard());
    }

    private BoardBacktrack deleteAvailableField(BoardBacktrack boardBacktrack){
        SudokuBoard sudokuBoard = boardBacktrack.getSudokuBoard();
        SudokuField sudokuField = boardBacktrack.getSudokuField();

        sudokuBoard.getElement(sudokuField.getX(), sudokuField.getY())
                .getAvailableNumbers().remove(new Integer(sudokuField.getValue()));
        return new BoardBacktrack(sudokuBoard, sudokuField);
    }

    private void saveBoardInBacktrack(SudokuField pickedField) {
        try{
            SudokuBoard sudokuBoard = boardContainer.getSudokuBoard().deepCopy();
            this.boardContainer.addBacktrack(
                    new BoardBacktrack(sudokuBoard, pickedField));
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
    }
}
