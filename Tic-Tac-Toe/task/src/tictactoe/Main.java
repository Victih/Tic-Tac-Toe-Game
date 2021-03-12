package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] ticTacToe = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToe[i][j] = ' ';
            }
        }
        //-----printing empty field-----
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(ticTacToe[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");


        char currentPlayer = 'X';
        boolean gameFinished = false;
        Scanner scan = new Scanner(System.in);
        while (gameFinished == false) {

            //-----input of play-----

            int coordinateVertical = 0;
            int coordinateHorizontal = 0;
            boolean inputSuccess = false;
            System.out.print("Enter the coordinates: ");
            while (!inputSuccess) {
                boolean validNumber = false;
                while (!validNumber) {
                    try {
                        coordinateVertical = scan.nextInt();
                        coordinateHorizontal = scan.nextInt();
                        if ((coordinateVertical > 3 || coordinateVertical < 1) || (coordinateHorizontal > 3 || coordinateHorizontal < 1)) {
                            System.out.println("Coordinates should be from 1 to 3!");
                        } else {
                            validNumber = true;
                        }
                    } catch (Exception e) {
                        System.out.println("You should enter numbers!");
                    }
                }

                //-----saving input of user-----

                if (ticTacToe[coordinateVertical - 1][coordinateHorizontal - 1] == ' ') {
                    ticTacToe[coordinateVertical - 1][coordinateHorizontal - 1] = currentPlayer;
                    inputSuccess = true;
                    if (currentPlayer == 'X') {
                        currentPlayer = 'O';
                    } else {
                        currentPlayer = 'X';
                    }
                }else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }

            //-----field output-----

            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(ticTacToe[i][j] + " ");
                }
                System.out.println("|");
            }
            System.out.println("---------");

            //-----calculating the winner-----

            int count = 0;
            boolean threeXInARow = false;
            boolean threeOInARow = false;
            boolean impossible = false;
            boolean unfinished = false;
            for (int i = 0; i < 2; i++) {
                int countOfX = 0;
                int countOfO = 0;
                for (int j = 0; j < 3; j++) {
                    for (int y = 0; y < 3; y++) {
                        if (ticTacToe[j][y] == 'X') {
                            countOfX++;
                        } else if (ticTacToe[j][y] == 'O') {
                            countOfO++;
                        }
                    }
                }
                if (countOfO > countOfX + 1 || countOfX > countOfO + 1) { //true if the difference is bigger then 1
                    impossible = true;
                    break;
                }
                count = 0;
                char currentlyChecked;
                if (i == 0) {
                    currentlyChecked = 'O'; //first round it checks player O
                } else {
                    currentlyChecked = 'X'; //second round it checks player X
                }
                //---checks horizontal:---
                for (int j = 0; j < 3; j++) {
                    for (int y = 0; y < 3; y++) {
                        if (ticTacToe[j][y] == currentlyChecked) {
                            count++;
                            if (count == 3) {
                                if (currentlyChecked == 'O') {
                                    threeOInARow = true;
                                } else {
                                    threeXInARow = true;
                                }
                            }
                        }
                    }
                    count = 0; //reset count
                }
                //--- checks vertical---
                for (int j = 0; j < 3; j++) {
                    for (int y = 0; y < 3; y++) {
                        if (ticTacToe[y][j] == currentlyChecked) {
                            count++;
                            if (count == 3) {
                                if (currentlyChecked == 'O') {
                                    threeOInARow = true;
                                } else {
                                    threeXInARow = true;
                                }
                            }
                        }
                    }
                    count = 0; //reset count
                }
                //-----checks diagonal-----
                for (int j = 0; j < 3; j++) {
                    if (ticTacToe[j][j] == currentlyChecked) {
                        count++;
                        if (count == 3) {
                            if (currentlyChecked == 'O') {
                                threeOInARow = true;
                            } else {
                                threeXInARow = true;
                            }
                        }
                    }
                }
                count = 0;
                for (int j = 0; j < 3; j++) {
                    if (ticTacToe[j][2 - j] == currentlyChecked) {
                        count++;
                        if (count == 3) {
                            if (currentlyChecked == 'O') {
                                threeOInARow = true;
                            } else {
                                threeXInARow = true;
                            }
                        }
                    }
                }
            }
            //-----output-----
            if (impossible || (threeOInARow && threeXInARow)) {
                System.out.println("Impossible");
                gameFinished = true;
            } else if (!threeXInARow && !threeOInARow) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTacToe[j][i] == ' ') {
                            unfinished = true;
                            break;
                        }
                    }
                    if (unfinished) {
                        break;
                    }
                }
                if (!unfinished) {
                    System.out.println("Draw");
                    gameFinished = true;
                }
            } else if (threeXInARow) {
                System.out.println("X wins");
                gameFinished = true;
            } else if (threeOInARow) {
                System.out.println("O wins");
                gameFinished = true;
            } else
                System.out.println("This shouldn't happen (Error 1)");
        }
    }
}
