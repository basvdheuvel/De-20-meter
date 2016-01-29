package earth.baas.de20meter;

/**
 * Created by bas on 1/25/16.
 */
public class Player {
    private String name;
    private int position;
    private Game game;

    public Player(String name, Game game) {
        this.name = name;
        position = 20;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getThrow() {
        return game.getThrow();
    }

    public boolean hasTurn() {
        return game.playerHasTurn(this);
    }

    public void move(int n) {
        position -= n;
    }

    public void undo(int n) {
        position += n;
    }

    public String toString() {
        return "<Player: " + name + ", " + position + ", " + hasTurn() + ">";
    }
}
