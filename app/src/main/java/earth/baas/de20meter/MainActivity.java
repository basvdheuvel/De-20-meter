package earth.baas.de20meter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button missButton, singleButton, doubleButton, tripleButton;
    Game game;
    ListView scoreListView;
    ListAdapter scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(baas.de20meter.R.layout.activity_main);

        missButton = (Button)findViewById(baas.de20meter.R.id.missButton);
        singleButton = (Button)findViewById(baas.de20meter.R.id.singleButton);
        doubleButton = (Button)findViewById(baas.de20meter.R.id.doubleButton);
        tripleButton = (Button)findViewById(baas.de20meter.R.id.tripleButton);

        missButton.setOnClickListener(missListener);
        singleButton.setOnClickListener(singleListener);
        doubleButton.setOnClickListener(doubleListener);
        tripleButton.setOnClickListener(tripleListener);

        scoreListView = (ListView)findViewById(baas.de20meter.R.id.scoreListView);
        scoreAdapter = scoreListView.getAdapter();

        game = new Game();
        game.addPlayer(new Player("a"));
        game.addPlayer(new Player("b"));
        game.addPlayer(new Player("c"));

        game.init();
        game.logState();
    }

    private View.OnClickListener missListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("test", "Miss clicked");
            game.miss();
            game.logState();
        }
    };

    private View.OnClickListener singleListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("test", "Single clicked");
            game.scoreSingle();
            game.logState();
        }
    };

    private View.OnClickListener doubleListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("test", "Double clicked");
            game.scoreDouble();
            game.logState();
        }
    };

    private View.OnClickListener tripleListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("test", "Triple clicked");
            game.scoreTriple();
            game.logState();
        }
    };
}
