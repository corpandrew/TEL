package activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import corp.andrew.tel.R;

/*
 * Created by corpa on 9/22/16.
 */
public class AboutUsActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_us);

        TextView emailTextView = (TextView) findViewById(R.id.contactEmailTextView);

        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mailto:contact@techxlab.org"));
                emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(Intent.createChooser(emailIntent, "Send Email Using: "));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
