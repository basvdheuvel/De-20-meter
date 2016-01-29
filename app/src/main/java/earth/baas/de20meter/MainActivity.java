package earth.baas.de20meter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button missButton, singleButton, doubleButton, tripleButton;
    Game game;
    ListView scoreListView;
    PlayerListAdapter playerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        game.addPlayer(new Player("a", game));
        game.addPlayer(new Player("b", game));
        game.addPlayer(new Player("c", game));

        game.init();
        game.logState();

        update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_undo) {
            Log.d(TAG, "Undo clicked");
            game.undoMove();
            update();
            return true;
        }

        if (id == R.id.action_settings) {
            Log.d(TAG, "Settings clicked");
            return true;
        }

        return super.onOptionsItemSelected(item);
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
