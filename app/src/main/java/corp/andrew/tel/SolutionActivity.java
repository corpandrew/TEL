package corp.andrew.tel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import json.Solution;

/**
 * Created by corpa on 3/22/2016.
 */

public class SolutionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        solutionImage.setImageResource(R.drawable.coolbot);//solutionIntoClass.getImageId());//R.drawable.coolbot);

        if(solutionIntoClass.getIsFavorite()) {
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);
        }
        else
            favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);

        favoritePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!solutionIntoClass.getIsFavorite()) {
                    favoritePicture.setImageResource(R.drawable.ic_favorite_orange_24px);
                    solutionIntoClass.setFavorite(true);//TODO TEST THIS, works just doesnt reload the items in the listadapter
                } else {
                    favoritePicture.setImageResource(R.drawable.ic_favorite_border_black_24px);
                    solutionIntoClass.setFavorite(false);//TODO TEST THIS
                }
                //Toast.makeText(getApplicationContext(),"Favorite?: " + solutionIntoClass.getIsFavorite(), Toast.LENGTH_SHORT).show();
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

}
