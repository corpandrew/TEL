package activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import Fragments.ImagePopOutFragment;
import corp.andrew.tel.R;
import json.Solution;

/**
 * Created by corpa on Aug 19, 2016
 */

public class SolutionActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ImageView favoritePicture;
    Solution solutionIntoClass;
    FragmentTransaction ft;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#00000000"));
        }

        sharedPreferences = getSharedPreferences("favoritesFile", 0);

        solutionIntoClass = (Solution) getIntent().getExtras().getSerializable("solution");

        setContentView(R.layout.solution_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//Top bar with the settings and search
        setSupportActionBar(toolbar);

        showActionBar();
        favoritePicture = (ImageView) findViewById(R.id.favoritePicture);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        final LinearLayout textViewLayout = (LinearLayout) findViewById(R.id.textViewLayout);

        final TextView toolbarTitle = (TextView) findViewById(R.id.telTextView);
        final TextView solutionName = (TextView) findViewById(R.id.solutionName);
        final TextView solutionCompany = (TextView) findViewById(R.id.solutionCompany);

        final TextView txt = (TextView) findViewById(R.id.txt);

        final TextView histAndDevText = (TextView) findViewById(R.id.histAndDevText);
        final TextView histAndDevHeader = (TextView) findViewById(R.id.historyanddevelopment);

        final TextView availabilityText = (TextView) findViewById(R.id.availabilityText);
        final TextView availabilityHeader = (TextView) findViewById(R.id.availability);

        final TextView specificationsText = (TextView) findViewById(R.id.specificationsText);
        final TextView specificationsHeader = (TextView) findViewById(R.id.specifications);

        final TextView additionalInformationText = (TextView) findViewById(R.id.addtionalInformationText);
        final TextView additionalInformationHeader = (TextView) findViewById(R.id.additionalinformation);

        final TextView contactText = (TextView) findViewById(R.id.contactText);
        final TextView contactHeader = (TextView) findViewById(R.id.contact);

        final ImageView solutionImage = (ImageView) findViewById(R.id.solutionPicture);
        final ImageView gradient = (ImageView) findViewById(R.id.gradient);

        final ImageView backActionImageView = (ImageView) findViewById(R.id.action_back);
        final TextView telWebsiteActionTextView = (TextView) findViewById(R.id.action_telWebsite);
        final ImageView emailActionImageView = (ImageView) findViewById(R.id.action_email);
        final ImageView websiteActionImageView = (ImageView) findViewById(R.id.action_website);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        final String website = getWebsite(solutionIntoClass.getAdditionalinfoTxt());
        final String email = getEmail(solutionIntoClass.getContactTxt());

        assert solutionIntoClass != null;

        solutionImage.setImageDrawable(getDrawableImageFromPath(solutionIntoClass.getPathToImage()));
        Bitmap bitmap = ((BitmapDrawable)solutionImage.getDrawable()).getBitmap();
        Palette palette = new Palette.Builder(bitmap).generate();
        solutionImage.setBackgroundColor(palette.getMutedColor(0xFFFF));

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


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= -750) {
                    if (!toolbarTitle.getText().equals(""))
                        toolbarTitle.setText("");
                } else if (verticalOffset <= -749) {
                    if (!toolbarTitle.getText().equals(solutionIntoClass.getName()))
                        toolbarTitle.setText(solutionIntoClass.getName());
                }
            }
        });

        gradient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("imagePath", solutionIntoClass.getPathToImage());

                ImagePopOutFragment imagePopOut = new ImagePopOutFragment();
                imagePopOut.setArguments(bundle);

                imagePopOut.show(getSupportFragmentManager(), "imagePop");

            }
        });

        telWebsiteActionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String website = "http://www.techxlab.org" + solutionIntoClass.getHref();
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW); // it's not ACTION_SEND
                websiteIntent.setData(Uri.parse(website)); // or just "mailto:" for blank
                websiteIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(Intent.createChooser(websiteIntent, "navigate to website"));
            }
        });

        emailActionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        websiteActionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!website.equals("")) {
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW); // it's not ACTION_SEND
                    websiteIntent.setData(Uri.parse(website)); // or just "mailto:" for blank
                    websiteIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    startActivity(Intent.createChooser(websiteIntent, "navigate to website"));
                } else {
                    Toast.makeText(getApplicationContext(), "No Website Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        solutionName.setText(solutionIntoClass.getName());
        solutionCompany.setText(solutionIntoClass.getContactName());
        txt.setText(solutionIntoClass.getTxt());

        if (solutionIntoClass.getHistDevTxt() == null) {
            histAndDevText.setText("");
            textViewLayout.removeView(histAndDevHeader);//todo find a more efficient way
            textViewLayout.removeView(histAndDevText);
        } else {
            histAndDevText.setText(solutionIntoClass.getHistDevTxt());
        }
        if (solutionIntoClass.getAdditionalinfoTxt() == null) {
            additionalInformationText.setText("");
            textViewLayout.removeView(additionalInformationHeader);
            textViewLayout.removeView(additionalInformationText);
        } else {
            if (solutionIntoClass.getAdditionalinfoTxt().contains("[Website]") || solutionIntoClass.getAdditionalinfoTxt().contains("[Product page]") || solutionIntoClass.getAdditionalinfoTxt().contains("Documentation")) {
                additionalInformationText.setText(R.string.more_info_on_site);
            } else {
                additionalInformationText.setText(solutionIntoClass.getAdditionalinfoTxt());
            }
        }
        if (solutionIntoClass.getAvailabilityTxt() == null) {
            availabilityText.setText("");
            textViewLayout.removeView(availabilityHeader);
            textViewLayout.removeView(availabilityText);
        } else {
            availabilityText.setText(solutionIntoClass.getAvailabilityTxt());
        }
        if (solutionIntoClass.getContactTxt() == null) {
            contactText.setText("");
            textViewLayout.removeView(contactHeader);
            textViewLayout.removeView(contactText);
        } else {
            if (solutionIntoClass.getContactTxt().contains("mailto:")) {
                contactText.setText(solutionIntoClass.getContactTxt().replace("[" + email + "](mailto:" + email + ")", email));
            } else {
                contactText.setText(solutionIntoClass.getContactTxt());
            }
        }
        if (solutionIntoClass.getSpecificationsTxt() == null) {
            specificationsText.setText("");
            textViewLayout.removeView(specificationsHeader);
            textViewLayout.removeView(specificationsText);
        } else {
            specificationsText.setText(solutionIntoClass.getSpecificationsTxt());
        }
    }

    private void changeFavorite(final String name, boolean favorite) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!sharedPreferences.getBoolean(solutionIntoClass.getName(), false)) {//if false set true
            favoritePicture.setImageResource(R.drawable.ic_favorite_white_24px);
        } else {
            favoritePicture.setImageResource(R.drawable.ic_favorite_border_white_24px);
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