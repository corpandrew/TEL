package corp.andrew.tel;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import json.Solution;

/**
 * Created by corpa on Aug 19, 2016
 */

public class SolutionActivity extends Activity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("favoritesFile", 0);

        final Solution solutionIntoClass = (Solution) getIntent().getExtras().getSerializable("solution");

        setContentView(R.layout.solution_main);

        final ImageView solutionImage = (ImageView) findViewById(R.id.solutionImage);
        final ImageView favoritePicture = (ImageView) findViewById(R.id.favoritePicture);
        final TextView solutionName = (TextView) findViewById(R.id.solutionName);
        final TextView solutionCompany = (TextView) findViewById(R.id.solutionCompany);
        final TextView txt = (TextView) findViewById(R.id.txt);
        final TextView histAndDevText = (TextView) findViewById(R.id.histAndDevText);
        final TextView availabilityText = (TextView) findViewById(R.id.availabilityText);
        final TextView specificationsText = (TextView) findViewById(R.id.specificationsText);
        final TextView additionalInformationText = (TextView) findViewById(R.id.addtionalInformationText);
        final TextView contactText = (TextView) findViewById(R.id.contactText);

        solutionImage.setImageResource(R.drawable.coolbot);

        //sets the picture of the favorite when you open the solution,.
        assert solutionIntoClass != null;
        if (sharedPreferences.getBoolean(solutionIntoClass.getName(), false)) {
            favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
        } else
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);

        //clicking the favorites button does this
        favoritePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sharedPreferences.getBoolean(solutionIntoClass.getName(), false)) {
                    favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
                    changeFavorite(solutionIntoClass.getName(), true);//TODO TEST THIS, works just doesnt reload the items in the listadapter
                } else {
                    favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);
                    changeFavorite(solutionIntoClass.getName(), false);//TODO TEST THIS
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

    private void changeFavorite(String name, boolean favorite) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, favorite);
        // Commit the edits!
        editor.apply();
    }

}