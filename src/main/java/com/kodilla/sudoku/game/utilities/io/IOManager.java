package com.kodilla.sudoku.game.utilities.io;

import com.kodilla.sudoku.game.utilities.board.BoardContainer;
import com.kodilla.sudoku.game.utilities.board.utilities.SudokuField;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuBoard;
import com.kodilla.sudoku.game.utilities.board.utilities.board.elements.SudokuElement;
import com.kodilla.sudoku.game.utilities.io.output.utilities.DialogsRepository;
import com.kodilla.sudoku.game.utilities.io.output.utilities.UserCommand;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOManager {
    private BoardContainer boardContainer = new BoardContainer();
    private DialogsRepository dialogsRepository = new DialogsRepository();
    private Scanner scanner = new Scanner(System.in);
    private static String REGEX = "\\d\\,\\d\\,\\d\\;";
    private boolean solverCaller = false;

    public void gatherInputFromUser(){
        startDialog();
        readBoard();
    }

    public BoardContainer getBoardContainer(){
        return this.boardContainer;
    }

    public void setBoardContainer(BoardContainer boardContainer){
        this.boardContainer = boardContainer;
    }

    private void startDialog(){
        dialogsRepository.hello();
        dialogsRepository.menu();
        dialogsRepository.inputRules();
    }

    private void readBoard() {
        this.solverCaller = false;
        while ( ! this.solverCaller) {
            readAndActOnInput();
        }
    }

    private void readAndActOnInput(){
        String input = scanner.nextLine();
        UserCommand command = translateUserCommand(input);
        if (command == UserCommand.NO_COMMAND) help();
        else actOnCommand(command);
    }

    private void actOnCommand(UserCommand userCommand){
        if(userCommand == UserCommand.SHOW_BOARD) showBoard();
        if (userCommand == UserCommand.INPUT_NUMBERS) inputMode();
        if(userCommand == UserCommand.SOLVE_SUDOKU) {
            System.out.println("Calling sudoku solver....");
            this.solverCaller = true;
        }
        if (userCommand == UserCommand.RESET) restartBoard();
        if (userCommand == UserCommand.HELP) help();
        if(userCommand == UserCommand.EXIT_INPUT_MODE){
            System.out.println("Not in input mode...");
        }
        if (userCommand == UserCommand.EXIT) exitTheSystem();
    }

    public void showBoard(){
        System.out.println(this.boardContainer.getSudokuBoard().toString());
    }

    private void inputMode(){
        System.out.println("Entering input mode...");
        Pattern pattern = Pattern.compile(REGEX);
        while(true){
            String input = scanner.nextLine();
            UserCommand userCommand = translateUserCommand(input);
            if(userCommand != UserCommand.NO_COMMAND){
                if(userCommand == UserCommand.SHOW_BOARD) showBoard();
                if(userCommand == UserCommand.EXIT_INPUT_MODE){
                    System.out.println("Quitting input mode...");
                    break;
                }
                if(userCommand == UserCommand.SOLVE_SUDOKU){
                    System.out.println("Calling sudoku solver....");
                    this.solverCaller = true;
                    break;
                }
                if(userCommand == UserCommand.HELP) help();
                if(userCommand == UserCommand.EXIT) exitTheSystem();
            }
            processStringToFieldAndSaveInContainer(input, pattern);
        }
    }

    private void restartBoard(){
        System.out.println("Board in progress deleted.");
        this.boardContainer.setSudokuBoard(new SudokuBoard());
    }

    private void help(){
        dialogsRepository.menu();
        dialogsRepository.inputRules();
    }

    private void exitTheSystem(){
        System.out.println("exit ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.exit(0);
    }

    private void processStringToFieldAndSaveInContainer(String input, Pattern pattern){
        Matcher matcher = pattern.matcher(input);
        int counter=0;
        while(matcher.find()){
            String sequence = cropString(input, matcher.start(), matcher.end());
            SudokuField sudokuField = processSequenceToSudokuField(sequence);
            if(isValuesOk(sudokuField)){
                counter++;
                addFieldToBoard(sudokuField);
            }
        }
        printNumberOfAcceptedSequences(counter);
    }

    private void printNumberOfAcceptedSequences(int counter){
        if(counter==0) {
            System.out.println("No sequences accepted.");
        }
        if(counter==1) {
            System.out.println("1 sequence accepted.");
        }
        if(counter>1){
            System.out.println(counter + " sequences accepted.");
        }
    }

    private SudokuField processSequenceToSudokuField(String sequence){
        int x = Character.getNumericValue(sequence.charAt(0));
        int y = Character.getNumericValue(sequence.charAt(2));
        int value = Character.getNumericValue(sequence.charAt(4));
        return new SudokuField(value,x,y);
    }

    private boolean isValuesOk(SudokuField sudokuField){
        boolean valuesOk = false;
        boolean value_status = false;
        boolean x_status = false;
        boolean y_status = false;
        int value = sudokuField.getValue();
        int x = sudokuField.getX();
        int y = sudokuField.getY();
        if((value > 0 && value < 10) || value == 0) value_status = true;
        if(x > 0 && x < 10) x_status = true;
        if(y > 0 && y < 10) y_status = true;
        if(value_status && x_status && y_status){
            valuesOk = true;
        } else {
            System.out.println("Sequence incorrect.");
        }
        return valuesOk;
    }

    private String cropString(String input, int start, int end){
        return input.substring(start, end);
    }

    private void addFieldToBoard(SudokuField sudokuField){
        this.boardContainer.getSudokuBoard().setElement(
                sudokuField.getX(), sudokuField.getY(), new SudokuElement(sudokuField.getValue()));
    }

    private UserCommand translateUserCommand(String input){
        UserCommand userCommand = UserCommand.NO_COMMAND;

        if(input.toLowerCase().contains("sudoku")) userCommand = UserCommand.SOLVE_SUDOKU;
        if(input.toLowerCase().contains("-sh")) userCommand = UserCommand.SHOW_BOARD;
        if(input.toLowerCase().contains("-h")) userCommand = UserCommand.HELP;
        if(input.toLowerCase().contains("-i")) userCommand = UserCommand.INPUT_NUMBERS;
        if(input.toLowerCase().contains("-x")) userCommand = UserCommand.EXIT;
        if(input.toLowerCase().contains("-r")) userCommand = UserCommand.RESET;
        if(input.toLowerCase().contains("-q")) userCommand = UserCommand.EXIT_INPUT_MODE;

        return userCommand;
    }
}
