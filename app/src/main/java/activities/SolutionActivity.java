package activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import corp.andrew.tel.R;
import json.Solution;

/**
 * Created by corpa on Aug 19, 2016
 */

public class SolutionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    ImageView favoritePicture;
    Solution solutionIntoClass;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("favoritesFile", 0);

        solutionIntoClass = (Solution) getIntent().getExtras().getSerializable("solution");

        setContentView(R.layout.solution_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//Top bar with the settings and search
        setSupportActionBar(toolbar);

        showActionBar();

        final ImageView solutionImage = (ImageView) findViewById(R.id.solutionPicture);
        favoritePicture = (ImageView) findViewById(R.id.favoritePicture);
        final TextView solutionName = (TextView) findViewById(R.id.solutionName);
        final TextView solutionCompany = (TextView) findViewById(R.id.solutionCompany);
        final TextView txt = (TextView) findViewById(R.id.txt);
        final TextView histAndDevText = (TextView) findViewById(R.id.histAndDevText);
        final TextView availabilityText = (TextView) findViewById(R.id.availabilityText);
        final TextView specificationsText = (TextView) findViewById(R.id.specificationsText);
        final TextView additionalInformationText = (TextView) findViewById(R.id.addtionalInformationText);
        final TextView contactText = (TextView) findViewById(R.id.contactText);

        final ImageView backActionImageView = (ImageView) findViewById(R.id.action_back);
        final ImageView emailActionImageView = (ImageView) findViewById(R.id.action_email);
        final ImageView callActionImageView = (ImageView) findViewById(R.id.action_call);
        final ImageView websiteActionImageView = (ImageView) findViewById(R.id.action_website);

        assert solutionIntoClass != null;

        solutionImage.setImageDrawable(getDrawableImageFromPath(solutionIntoClass.getPathToImage()));

        //sets the picture of the favorite when you open the solution,.
        if (sharedPreferences.getBoolean(solutionIntoClass.getName(), false)) {
            favoritePicture.setImageResource(R.drawable.ic_favorite_white_24px);
        } else
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_white_24px);

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

        backActionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        emailActionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getEmail(solutionIntoClass.getContactTxt());
                if (!email.equals("")) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                    emailIntent.setType("text/plain");
                    emailIntent.setData(Uri.parse("mailto:" + email)); // or just "mailto:" for blank
                    emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    startActivity(Intent.createChooser(emailIntent, "Send Email Using: "));
                } else {
                    Toast.makeText(getApplicationContext(), "No Email Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callActionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = getPhoneNumber(solutionIntoClass.getContactTxt());
                phoneNumber = "";
                if (!phoneNumber.equals("")) {
                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                    phoneIntent.setData(Uri.parse("tel:" + phoneNumber));
                    phoneIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(phoneIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Not yet Implemented", Toast.LENGTH_SHORT).show();
                }
            }
        });

        websiteActionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String website = getWebsite(solutionIntoClass.getAdditionalinfoTxt());
                if (!website.equals("")) {
                    Intent emailIntent = new Intent(Intent.ACTION_VIEW); // it's not ACTION_SEND
                    emailIntent.setData(Uri.parse(website)); // or just "mailto:" for blank
                    emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    startActivity(Intent.createChooser(emailIntent, "Send Email Using: "));
                } else {
                    Toast.makeText(getApplicationContext(), "No Website Available", Toast.LENGTH_SHORT).show();
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
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_orange_24px);
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

    private Drawable getDrawableImageFromPath(String imagePath) {
        Drawable d = null;
        try {
            d = Drawable.createFromStream(getAssets().open(imagePath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

    private String getEmail(String contactText) {
        if (contactText.contains("[") && contactText.contains("]")) {
            return contactText.substring(contactText.indexOf("[") + 1, contactText.indexOf("]"));
        } else {
            return "";
        }
    }

    private String getPhoneNumber(String contactText) {


        return "";
    }

    private String getWebsite(String additionalInformation) {//refine this and make better
        if (additionalInformation.contains("http")) {
            return additionalInformation.substring(additionalInformation.indexOf("(") + 1, additionalInformation.indexOf(")"));
        } else {
            return "";
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    private void showActionBar() {
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.solution_activity_toolbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(v);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

}