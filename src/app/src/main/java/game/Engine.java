package game;

public class Engine {
    String player;
    String otherPlayer;

    public Engine(String player) {
        this.player = player;
        this.otherPlayer = player == "X" ? "O" : "X";
    }

    /**
     * Finds an optimal move using the minimax algorithm and alpha-beta pruning.
     * 
     * @param board
     * @param alpha
     * @param beta
     * @param maximizingPlayer
     * @return { eval, move }
     */
    public int[] minimax(Board board, int alpha, int beta, boolean maximizingPlayer) {
        if (board.gameOver())
            return new int[] { Evaluator.evaluate(board, player), -1 };

        int bestMove = -1;
        if (maximizingPlayer) {
            int maxEval = -10;
            for (int move : board.getMoves()) {
                // Extracting row and col from move integer
                int row = move / 3;
                int col = move % 3;

                board.makeMove(row, col, player);
                int eval = minimax(board, alpha, beta, false)[0];
                board.undoMove(row, col, player);

                // Pruning
                if (beta <= alpha)
                    break;

                if (eval > maxEval) {
                    maxEval = eval;
                    bestMove = move;
                }
                alpha = Math.max(alpha, eval);
            }
            return new int[] { maxEval, bestMove };
        } else {
            int minEval = 10;
            for (int move : board.getMoves()) {
                // Extracting row and col from move integer
                int row = move / 3;
                int col = move % 3;

                board.makeMove(row, col, otherPlayer);
                int eval = minimax(board, alpha, beta, true)[0];
                board.undoMove(row, col, otherPlayer);

                // Pruning
                if (beta <= alpha)
                    break;

                if (eval < minEval) {
                    minEval = eval;
                    bestMove = move;
                }
                beta = Math.min(beta, eval);
            }
            return new int[] { minEval, bestMove };
        }
    }
}
