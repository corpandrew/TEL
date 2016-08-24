package activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import java.io.File;

import corp.andrew.tel.DownloadFileTask;
import corp.andrew.tel.R;

/**
 * Created by corpa on Aug 19, 2016
 */

public class LoadScreenActivity extends AppCompatActivity {

    private static final String downloadString = "http://www.techxlab.org/pages.json";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        verifyStoragePermissions(this);

        boolean sync = getIntent().getBooleanExtra("sync", false);
        String version = "file.json";

        SharedPreferences sharedPreferences = getSharedPreferences("favoritesFile", 0);

        if (sync || sharedPreferences.getBoolean("firstRun", true) || !(new File(Environment.getExternalStorageDirectory().toString() + "/" + version).exists())) {
            setContentView(R.layout.loading_screen);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            DownloadFileTask downloadFileTask = new DownloadFileTask(this, progressBar);
            downloadFileTask.execute(version, downloadString);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
