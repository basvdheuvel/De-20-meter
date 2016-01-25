package earth.baas.de20meter;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by bas on 1/25/16.
 */
public class Game {
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private Player currentPlayer;

    public Game() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void init() {
        currentPlayerIndex = 0;
        currentPlayer = players.get(0);
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    public void miss() {
        nextPlayer();
    }

    public void scoreSingle() {
        currentPlayer.scoreSingle();
        nextPlayer();
    }

    public void scoreDouble() {
        currentPlayer.scoreDouble();
        nextPlayer();
    }

    public void scoreTriple() {
        currentPlayer.scoreTriple();
        nextPlayer();
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
}
