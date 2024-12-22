package game;

public class Evaluator {
    /**
     * Returns 1 if engine has won, -1 if engine has lost, and 0 otherwise.
     * 
     * @param board
     * @return
     */
    public static int evaluate(Board board, String enginePlayer) {
        if (board.checkWin(enginePlayer == "O" ? "X" : "O"))
            return -1;
        else if (board.checkWin(enginePlayer))
            return 1;
        else
            return 0;
    }
}
