package earth.baas.de20meter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bas on 1/25/16.
 */
public class PlayerListAdapter extends ArrayAdapter {
    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public PlayerListAdapter(Context ctx, int resourceId, List objects) {
        super(ctx, resourceId, objects);

        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context = ctx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(resource, null);

        Player player = (Player)getItem(position);

        TextView positionTextView = (TextView)convertView.findViewById(R.id.positionTextView);
        TextView nameTextView = (TextView)convertView.findViewById(R.id.nameTextView);

        positionTextView.setText(Integer.toString(player.getPosition()));
        nameTextView.setText(player.getName());

        if (player.hasTurn()) {
            positionTextView.setTypeface(positionTextView.getTypeface(), Typeface.BOLD);
            nameTextView.setTypeface(nameTextView.getTypeface(), Typeface.BOLD);
        }
        else {
            positionTextView.setTypeface(positionTextView.getTypeface(), Typeface.NORMAL);
            nameTextView.setTypeface(nameTextView.getTypeface(), Typeface.NORMAL);
        }

        return convertView;
    }
}
