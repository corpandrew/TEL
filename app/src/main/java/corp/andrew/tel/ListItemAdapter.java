package corp.andrew.tel;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import Fragments.ImagePopOutFragment;
import json.Solution;

/**
 * Created by corpa on Aug 21, 2016
 */

public class ListItemAdapter extends ArrayAdapter<Solution> {

    private int i = 0;
    private LayoutInflater inflater;
    private SharedPreferences prefs;
    private Context context;
    private FragmentManager fragmentManager;

    public ListItemAdapter(Context context, int resourceId, List<Solution> list, SharedPreferences prefs, FragmentManager fragmentManager) {
        super(context, resourceId, list);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.prefs = prefs;
        this.fragmentManager = fragmentManager;
    }

    private void addFavorite(String name, boolean favorite) {
        prefs.edit().putBoolean(name, favorite).apply();
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

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        final ViewHolder holder;
        final Solution viewSolution = getItem(position);

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.list_item, null);

            holder.solutionName = (TextView) convertView.findViewById(R.id.solutionName);

            holder.solutionCompany = (TextView) convertView.findViewById(R.id.solutionCompany);

            holder.solutionPicture = (ImageView) convertView.findViewById(R.id.solutionPicture);

            holder.favoritePicture = (ImageView) convertView.findViewById(R.id.favoritePicture);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        assert viewSolution != null;

        holder.position = position;

        holder.solutionName.setTextColor(ContextCompat.getColor(context, R.color.black));
        holder.solutionName.setText(viewSolution.getName());

        holder.solutionCompany.setTextColor(ContextCompat.getColor(context, R.color.black));
        holder.solutionCompany.setText(viewSolution.getContactName());

        final String pathToImage = viewSolution.getPathToImage();
        holder.solutionPicture.setImageDrawable(getDrawableImageFromPath(pathToImage));

        //DrawableTask drawableTask = new DrawableTask(position,holder,pathToImage, context);
        //drawableTask.doInBackground();

        holder.solutionPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("imagePath", pathToImage);

                ImagePopOutFragment imagePopOut = new ImagePopOutFragment();
                imagePopOut.setArguments(bundle);

                imagePopOut.show(fragmentManager, "imagePop");
            }
        });

        if (!prefs.getBoolean(viewSolution.getName(), false)) {
            holder.favoritePicture.setImageResource(R.drawable.ic_favorite_border_orange_24px);
        } else {
            holder.favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
        }

        holder.favoritePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!prefs.getBoolean(viewSolution.getName(), false)) {
                    holder.favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
                    addFavorite(viewSolution.getName(), true);
                } else {
                    holder.favoritePicture.setImageResource(R.drawable.ic_favorite_border_orange_24px);
                    addFavorite(viewSolution.getName(), false);
                }
            }
        });

        if (i % 2 == 0) {
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
        }

        i++;

        return convertView;
    }

    private static class DrawableTask extends AsyncTask<String, Integer, Drawable> {//Todo use this task to make loading time faster.
        private int mPosition;
        private ViewHolder holder;
        private String pathToImage;
        private Context context;

        public DrawableTask(int position, ViewHolder holder, String pathToImage, Context context) {
            mPosition = position;
            this.holder = holder;
            this.pathToImage = pathToImage;
            this.context = context;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            Drawable d = null;
            try {
                d = Drawable.createFromStream(context.getAssets().open(pathToImage), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return d;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if (holder.position == mPosition) {
                holder.solutionPicture.setImageDrawable(drawable);
            }
        }
    }

    private static class ViewHolder {
        TextView solutionName, solutionCompany;
        ImageView solutionPicture, favoritePicture;
        int position;
    }

}
