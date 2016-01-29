package earth.baas.de20meter;

import android.util.Log;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by bas on 1/25/16.
 */
public class Game {
    private final static String TAG = "Game";

    private ArrayList<Player> players;

    private int currentPlayerIndex;
    private Player currentPlayer;
    private int currentThrow;

    private Stack<Move> moves;

    public Game() {
        players = new ArrayList<>();
        moves = new Stack<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void init() {
        currentPlayerIndex = 0;
        currentPlayer = players.get(0);
        currentThrow = 0;
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    public boolean playerHasTurn(Player player) {
        return player == currentPlayer;
    }

    public int getThrow() {
        return currentThrow;
    }

    public void miss() {
        newMove(0);
    }

    public void scoreSingle() {
        newMove(1);
    }

    public void scoreDouble() {
        newMove(2);
    }

    public void scoreTriple() {
        newMove(3);
    }

    private void newMove(int n) {
        Move move = new Move(currentPlayer, n, currentThrow);
        moves.push(move);
        move.doMove();

        currentThrow ++;
        if (currentThrow >= 3) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            currentPlayer = players.get(currentPlayerIndex);
            currentThrow = 0;
        }
    }

    public void undoMove() {
        Move move;
        try {
            move = moves.pop();
        }
        catch (EmptyStackException e) {
            Log.d(TAG, "No moves to undo");
            return;
        }

        move.undo();

        currentPlayer = move.getPlayer();
        currentPlayerIndex = players.indexOf(currentPlayer);
        currentThrow = move.getThrow();
    }

    private boolean isCurrentPlayer(Player player) {
        return currentPlayer == player;
    }

    public void logState() {
        String tag = "status";

        Log.d(tag, "Game:");

        for (Player player: players) {
            if (isCurrentPlayer(player)) {
                Log.d(tag, "! " + player);
            }
            else {
                Log.d(tag, "  " + player);
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    private class Move {
        private Player player;
        private int n;
        private int t;

        public Move(Player player, int n, int t) {
            this.player = player;
            this.n = n;
            this.t = t;
        }

        public Player getPlayer() {
            return player;
        }

        public int getThrow() {
            return t;
        }

        public void doMove() {
            player.move(n);
        }

        public void undo() {
            player.undo(n);
        }
    }
}
