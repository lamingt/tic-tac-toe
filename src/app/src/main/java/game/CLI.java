package game;

import java.util.Scanner;

public class CLI {
    private static Board board = new Board();
    private static Scanner scanner = new Scanner(System.in);
    private static String player = "";
    private static String enginePlayer = "";
    private static Boolean engineTurn = false;
    private static Engine engine = new Engine("X");
    private static int playerScore = 0;
    private static int engineScore = 0;
    private static int numDraws = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-tac-toe! Make moves by typing the row and column space separated.");
        System.out.println("For example, the top left corner would be 1 1.");

        play();
    }

    public static void play() {
        while (!board.gameOver()) {
            if (player.equals("")) {
                System.out.print("Pick a player (O or X): ");
                player = scanner.nextLine();
                if (player.equals("X")) {
                    enginePlayer = "O";
                    engine = new Engine("O");
                    engineTurn = false;
                } else if (player.equals("O")) {
                    enginePlayer = "X";
                    engine = new Engine("X");
                    engineTurn = true;
                } else {
                    System.out.println("Error: Enter 'O' or 'X'");
                    player = "";
                }
                continue;
            }

            System.out.println(board.toString());
            if (engineTurn) {
                int move = engine.minimax(board, -10, 10, true)[1];
                int row = move / 3;
                int col = move % 3;
                board.makeMove(row, col, enginePlayer);
                engineTurn = false;
            } else {
                System.out.print("Enter your move: ");
                String[] input = scanner.nextLine().split(" ");
                int row = Integer.valueOf(input[0]) - 1;
                int col = Integer.valueOf(input[1]) - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2 || !board.isEmpty(row, col)) {
                    System.out.println("Error: Invalid move.");
                    continue;
                }

                board.makeMove(row, col, player);
                engineTurn = true;
            }
        }

        if (board.checkWin(player)) {
            System.out.println("You have won!");
            playerScore++; // this isnt happening
        } else if (board.checkWin(enginePlayer)) {
            System.out.println("You have lost!");
            engineScore++;
        } else {
            System.out.println("It is a draw!");
            numDraws++;
        }
        System.out.println(board.toString());

        System.out.println("You have " + playerScore + " win(s).");
        System.out.println("The engine has " + engineScore + " win(s).");
        System.out.println("You have " + numDraws + " draw(s).");

        System.out.print("Would you like to keep playing (Y/N)? ");

        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            board.clear();
            player = "";
            play();
        } else if (answer.equals("N")) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Error: Invalid response.");
        }

        scanner.close();
    }
}
