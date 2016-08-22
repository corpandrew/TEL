package activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import corp.andrew.tel.R;
import json.Solution;

/**
 * Created by corpa on Aug 19, 2016
 */

public class SolutionActivity extends Activity {

    SharedPreferences sharedPreferences;
    ImageView favoritePicture;
    Solution solutionIntoClass;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("favoritesFile", 0);

        solutionIntoClass = (Solution) getIntent().getExtras().getSerializable("solution");

        setContentView(R.layout.solution_main);

        final ImageView solutionImage = (ImageView) findViewById(R.id.solutionImage);
        favoritePicture = (ImageView) findViewById(R.id.favoritePicture);
        final TextView solutionName = (TextView) findViewById(R.id.solutionName);
        final TextView solutionCompany = (TextView) findViewById(R.id.solutionCompany);
        final TextView txt = (TextView) findViewById(R.id.txt);
        final TextView histAndDevText = (TextView) findViewById(R.id.histAndDevText);
        final TextView availabilityText = (TextView) findViewById(R.id.availabilityText);
        final TextView specificationsText = (TextView) findViewById(R.id.specificationsText);
        final TextView additionalInformationText = (TextView) findViewById(R.id.addtionalInformationText);
        final TextView contactText = (TextView) findViewById(R.id.contactText);

        assert solutionIntoClass != null;

        solutionImage.setImageResource(R.drawable.coolbot);

        //sets the picture of the favorite when you open the solution,.
        if (sharedPreferences.getBoolean(solutionIntoClass.getName(), false)) {
            favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
        } else
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);

        //clicking the favorites button does this
        favoritePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sharedPreferences.getBoolean(solutionIntoClass.getName(), false)) {
                    changeFavorite(solutionIntoClass.getName(), false);
                } else {
                    changeFavorite(solutionIntoClass.getName(), true);
                }
            }
        });

        solutionName.setText(solutionIntoClass.getName());
        solutionCompany.setText(solutionIntoClass.getContactName());
        txt.setText(solutionIntoClass.getTxt());
        histAndDevText.setText(solutionIntoClass.getHistDevTxt());
        availabilityText.setText(solutionIntoClass.getAvailabilityTxt());
        specificationsText.setText(solutionIntoClass.getSpecificationsTxt());
        additionalInformationText.setText(solutionIntoClass.getAdditionalinfoTxt());
        contactText.setText(solutionIntoClass.getContactTxt());
    }

    private void changeFavorite(final String name, boolean favorite) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!sharedPreferences.getBoolean(solutionIntoClass.getName(), false)) {//if false set true
            favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
        } else {
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);
        }

        System.out.println("Before Favorite: " + solutionIntoClass.getIsFavorite());

        solutionIntoClass.setFavorite(!favorite);
        editor.putBoolean(name, !favorite);
        // Commit the edits!
        editor.apply();

        System.out.println("After Favorite: " + solutionIntoClass.getIsFavorite());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}