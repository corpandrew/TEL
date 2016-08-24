package corp.andrew.tel;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import json.Solution;

/**
 * Created by corpa on Aug 21, 2016
 */

public class ListItemAdapter extends ArrayAdapter<Solution> {

    int i = 0;
    SharedPreferences.Editor editor;
    private LayoutInflater inflater;
    private SharedPreferences prefs;
    private Context context;

    public ListItemAdapter(Context context, int resourceId, List<Solution> list, SharedPreferences prefs) {
        super(context, resourceId, list);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.prefs = prefs;
        editor = prefs.edit();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Solution viewSolution = getItem(position);

        View view = inflater.inflate(R.layout.list_item, null);

        TextView solutionName = (TextView) view.findViewById(R.id.solutionName);
        solutionName.setTextColor(parent.getResources().getColor(R.color.black));
        solutionName.setText(viewSolution.getName());

        TextView solutionCompany = (TextView) view.findViewById(R.id.solutionCompany);
        solutionCompany.setTextColor(parent.getResources().getColor(R.color.black));
        solutionCompany.setText(viewSolution.getContactName());

        ImageView solutionPicture = (ImageView) view.findViewById(R.id.solutionPicture);
        solutionPicture.setImageDrawable(getDrawableImageFromPath(viewSolution.getPathToImage()));

        final ImageView favoritePicture = (ImageView) view.findViewById(R.id.favoritePicture);

        if (!prefs.getBoolean(viewSolution.getName(), false)) {
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_orange_24px);
        } else {
            favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
        }

        favoritePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!prefs.getBoolean(viewSolution.getName(), false)) {
                    favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
                    addFavorite(viewSolution.getName(), true);
                } else {
                    favoritePicture.setImageResource(R.drawable.ic_favorite_border_orange_24px);
                    addFavorite(viewSolution.getName(), false);
                }
            }
        });

        if (i % 2 == 0) {
            view.setBackgroundColor(parent.getResources().getColor(R.color.grey));
        }

        i++;

        return view;
    }


    private void addFavorite(String name, boolean favorite) {
        editor.putBoolean(name, favorite).apply();
    }

    private Drawable getDrawableImageFromPath(String imagePath) {
        Drawable d = null;
        try {
            d = Drawable.createFromStream(context.getAssets().open(imagePath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

}
