import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> storageNumbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        char[][] arrayTacToeGame = new char[3][3];
        int userPosition;
        int machinePosition;
        boolean gameOver = true;

        welcome(); // Display welcome message

        while (gameOver) {
            displayBoard(arrayTacToeGame); // Print the current game board

            try {
                System.out.println("`````````````````````````````");
                System.out.println("numbers of position: \n1 2 3 \n4 5 6 \n7 8 9 ");
                System.out.println("Input position number (1-9): ");
                userPosition = sc.nextInt();

                if (checkInputsDuplicates(storageNumbers, userPosition) == 1) {
                    System.out.println("Position already taken");
                    continue;
                }

                updateBoard(arrayTacToeGame, userPosition, 'X');

                if (checkWinner(arrayTacToeGame, 'X')) {
                    System.out.println("Congratulations! You are the winner!");
                    break;
                }

                machinePosition = getRandomPosition(rand, storageNumbers);
                updateBoard(arrayTacToeGame, machinePosition, 'O');

                if (checkWinner(arrayTacToeGame, 'O')) {
                    System.out.println("Machine wins! Better luck next time.");
                    break;
                }

            } catch (Exception e) {
                System.out.println("only numbers is allowed ! Please enter a number between 1 and 9.");
                sc.next();
            }
        }
    }

    public static void welcome() {
        System.out.println("Welcome to Tic Tac Toe!");
    }

    public static void displayBoard(char[][] board) {
        System.out.println("Current Board:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (j % 2 == 1) {
                    System.out.print("|");
                }else if (j % 2 == 1  || j != 0) {
                    System.out.print("|");
                }

                System.out.print(board[i][j] == 0 ? "-" : board[i][j]+" " );

            }
            System.out.println();
        }
    }

    public static void updateBoard(char[][] board, int position, char xo) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        board[row][col] = xo;
    }

    public static int checkInputsDuplicates(ArrayList<Integer> storageNumbers, int input) {
        if (storageNumbers.contains(input)) {
            return 1;
        } else {
            storageNumbers.add(input);
            return 0;
        }
    }

    public static int getRandomPosition(Random rand, ArrayList<Integer> storageNumbers) {
        int position;
        do {
            position = rand.nextInt(9) + 1;
        } while (storageNumbers.contains(position));
        storageNumbers.add(position);
        return position;
    }

    public static boolean checkWinner(char[][] board, char XO) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == XO && board[i][1] == XO && board[i][2] == XO) {
                return true;
            }
            if (board[0][i] == XO && board[1][i] == XO && board[2][i] == XO) {
                return true;
            }
        }
        return (board[0][0] == XO && board[1][1] == XO && board[2][2] == XO) ||
                (board[0][2] == XO && board[1][1] == XO && board[2][0] == XO);
    }
}
