package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private String[][] board;
    private int numMoves;

    public Board() {
        board = new String[3][3];
        clear();
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
        numMoves = 0;
    }

    /**
     * Makes a move, assuming valid inputs.
     * 
     * @param row
     * @param col
     * @param player
     */
    public void makeMove(int row, int col, String player) {
        board[row][col] = player;
        numMoves++;
    }

    /**
     * Undos a move, assuming valid inputs.
     * 
     * @param row
     * @param col
     * @param player
     */
    public void undoMove(int row, int col, String player) {
        board[row][col] = " ";
        numMoves--;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    res.append(board[i][j] + "\n");
                } else {
                    res.append(board[i][j] + "|");
                }
            }
            if (i != 2) {
                res.append("- - -\n");
            }
        }

        return res.toString();
    }

    public boolean checkWin(String player) {
        for (int i = 0; i < 3; i++) {
            // checking rows
            if (board[i][0].equals(player) && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return true;
            }
            // checking columns
            if (board[0][i].equals(player) && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return true;
            }
        }

        // checking diagonals
        if (board[0][0].equals(player) && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])
                || board[2][0].equals(player) && board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])) {
            return true;
        }

        return false;
    }

    public boolean gameOver() {
        return checkWin("O") || checkWin("X") || numMoves == 9;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col].equals(" ");
    }

    public List<Integer> getMoves() {
        List<Integer> moves = new ArrayList<>();
        // could be optimised by considering corners or the centre first with alpha-beta
        // pruning
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    moves.add(i * 3 + j);
                }
            }
        }
        // shuffling to make more interesting moves
        Collections.shuffle(moves);
        return moves;
    }
}
