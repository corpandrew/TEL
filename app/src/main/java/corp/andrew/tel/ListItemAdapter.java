package corp.andrew.tel;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import json.Solution;

/**
 * Created by corpa on 3/19/2016.
 */

public class ListItemAdapter extends ArrayAdapter<Solution> {

    private LayoutInflater inflater;
    int i = 0;

    public ListItemAdapter(Context context, int resourceId, List<Solution> list) {
        super(context,resourceId,list);
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Solution item = (Solution) getItem(position);

        View view = inflater.inflate(R.layout.list_item,null);

        TextView solutionName = (TextView)view.findViewById(R.id.solutionName);
        solutionName.setText(item.getName());

        TextView solutionCompany = (TextView) view.findViewById(R.id.solutionCompany);
        solutionCompany.setText(item.getContactName());

        ImageView solutionPicture = (ImageView) view.findViewById(R.id.solutionPicture);
        solutionPicture.setImageResource(R.drawable.coolbot);//item.getImageId());

        final ImageView favoritePicture = (ImageView) view.findViewById(R.id.favoritePicture);
        if(!item.getIsFavorite())
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);
        else
            favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);

        favoritePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!item.getIsFavorite()){
                    favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
                    item.setFavorite(true);
                } else {
                    favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);
                    item.setFavorite(false);
                }
            }
        });

        if(i % 2 == 0){
            view.setBackgroundColor(parent.getResources().getColor(R.color.grey));
        }

        i++;

        return view;
    }

}
