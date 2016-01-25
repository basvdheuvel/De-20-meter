package earth.baas.de20meter;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    Button missButton, singleButton, doubleButton, tripleButton;
    Game game;
    ListView scoreListView;
    ListAdapter scoreAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        scoreAdapter = scoreListView.getAdapter();

        game = new Game();
        game.addPlayer(new Player("a"));
        game.addPlayer(new Player("b"));
        game.addPlayer(new Player("c"));

        game.init();
        game.logState();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://earth.baas.de20meter/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://earth.baas.de20meter/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
