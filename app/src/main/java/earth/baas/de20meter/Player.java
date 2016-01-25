package earth.baas.de20meter;

/**
 * Created by bas on 1/25/16.
 */
public class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        position = 20;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int scoreSingle() {
        return move(1);
    }

    public int scoreDouble() {
        return move(2);
    }

    public int scoreTriple() {
        return move(3);
    }

    private int move(int n) {
        position -= n;
        return position;
    }

    public String toString() {
        return "<Player: " + name + ", " + position + ">";
    }
}
