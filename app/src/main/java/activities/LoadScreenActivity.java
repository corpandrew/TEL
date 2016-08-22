package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import corp.andrew.tel.DownloadFileTask;
import corp.andrew.tel.R;

/**
 * Created by corpa on Aug 19, 2016
 */
public class LoadScreenActivity extends AppCompatActivity {

    private static final String downloadString = "http://www.techxlab.org/pages.json";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean sync = getIntent().getBooleanExtra("sync", false);

        SharedPreferences sharedPreferences = getSharedPreferences("favoritesFile", 0);

        if (sync || sharedPreferences.getBoolean("firstRun", true)) {
            setContentView(R.layout.loading_screen);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            DownloadFileTask downloadFileTask = new DownloadFileTask(this, progressBar);
            String version = "file.json";
            downloadFileTask.execute(version, downloadString);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
