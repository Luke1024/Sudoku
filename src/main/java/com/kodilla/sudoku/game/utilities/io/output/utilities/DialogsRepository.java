package com.kodilla.sudoku.game.utilities.io.output.utilities;

public class DialogsRepository {
    public void hello() {print("Welcome to Sudoku solving system." + "\n");}
    public void menu() {print("-i        to enter sudoku board input mode," + "\n" +
                              "-q        to exit input mode" + "\n" +
                              "-h        showing this help" + "\n" +
                              "-r        reset board sequence" + "\n" +
                              "-sh       show board" + "\n" +
                              "-x        to exit" + "\n");}
    public void inputRules() { print("Example input command: ( 2,4,5 ); where first number is X position," +"\n" +
                        "second number is Y position and third number is value." + "\n" +
                        "Numbers must be between 1-9 and separated with comma." + "\n" +
                        "If you want set value to empty write number 0" + '\n' +
                        "negative numbers are not correct" + '\n' +
                        "Muliple sequence values are possible like 2,4,5; 3,2,1; 5,6,3; " + "\n" +
                        "Single input is only correct with 3 numbers ended with ;" + "\n" +
                        "With uncompleted sequence like 2,4,5; 4,6; incorrect inputs are skipped." +
                        "You accept input sequence by write SUDOKU and calling sudoku solver." + "\n");}
    public void skippedIncorrect() {print("Skipped incorrect sequence: ");}
    public void solvingFailed() {print("Solving failed.");}

    private void print(String text){
        System.out.println(text);
    }
}
