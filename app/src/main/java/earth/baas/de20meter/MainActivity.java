package earth.baas.de20meter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button missButton, singleButton, doubleButton, tripleButton;
    Game game;
    ListView scoreListView;
    PlayerListAdapter playerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        missButton = (Button) findViewById(R.id.missButton);
        singleButton = (Button) findViewById(R.id.singleButton);
        doubleButton = (Button) findViewById(R.id.doubleButton);
        tripleButton = (Button) findViewById(R.id.tripleButton);

        missButton.setOnClickListener(missListener);
        singleButton.setOnClickListener(singleListener);
        doubleButton.setOnClickListener(doubleListener);
        tripleButton.setOnClickListener(tripleListener);

        scoreListView = (ListView) findViewById(R.id.scoreListView);

        game = new Game();

        scoreListView = (ListView)findViewById(R.id.scoreListView);
        playerListAdapter = new PlayerListAdapter(this, R.layout.fragment_player,
                game.getPlayers());
        scoreListView.setAdapter(playerListAdapter);

        game.addPlayer(new Player("a"));
        game.addPlayer(new Player("b"));
        game.addPlayer(new Player("c"));
        game.addPlayer(new Player("d"));
        game.addPlayer(new Player("e"));
        game.addPlayer(new Player("f"));
        game.addPlayer(new Player("g"));
        game.addPlayer(new Player("h"));
        game.addPlayer(new Player("i"));
        game.addPlayer(new Player("j"));
        game.addPlayer(new Player("k"));
        game.addPlayer(new Player("l"));
        game.addPlayer(new Player("m"));
        game.addPlayer(new Player("n"));
        game.addPlayer(new Player("o"));
        game.addPlayer(new Player("p"));
        game.addPlayer(new Player("q"));
        game.addPlayer(new Player("r"));

        game.init();
        game.logState();

        update();
    }

    private View.OnClickListener missListener = new View.OnClickListener() {
        public void onClick(View v) {
            game.miss();
            game.logState();

            update();
        }
    };

    private View.OnClickListener singleListener = new View.OnClickListener() {
        public void onClick(View v) {
            game.scoreSingle();
            game.logState();

            update();
        }
    };

    private View.OnClickListener doubleListener = new View.OnClickListener() {
        public void onClick(View v) {
            game.scoreDouble();
            game.logState();

            update();
        }
    };

    private View.OnClickListener tripleListener = new View.OnClickListener() {
        public void onClick(View v) {
            game.scoreTriple();
            game.logState();

            update();
        }
    };

    private void update() {
        playerListAdapter.notifyDataSetChanged();
        scoreListView.smoothScrollToPosition(game.getCurrentPlayerIndex());
    }
}
