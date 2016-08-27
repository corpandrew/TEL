package Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

import corp.andrew.tel.R;

/**
 * Created by corpa on Aug 27, 2016
 */
public class ImagePopOutFragment extends DialogFragment {
    Drawable imageDrawable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.image_pop_out, container, false); //just return the  view ;

        ImageView imageView = (ImageView) v.findViewById(R.id.solutionImagePopOut);

        String path = getArguments().getString("imagePath");

        imageDrawable = getDrawableImageFromPath(path);

        imageView.setImageDrawable(imageDrawable);

        /*BitmapDrawable bd = (BitmapDrawable) imageDrawable;
        int height = bd.getBitmap().getHeight();
        int width = bd.getBitmap().getWidth();

        imageView.getLayoutParams().height = height;
        imageView.getLayoutParams().width = width;
        imageView.requestLayout();*/

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Drawable getDrawableImageFromPath(String imagePath) {
        Drawable d = null;
        try {
            d = Drawable.createFromStream(getActivity().getAssets().open(imagePath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }
}



