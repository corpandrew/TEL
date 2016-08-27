package activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;

import Fragments.NoInternetDialogFragment;
import corp.andrew.tel.DownloadFileTask;
import corp.andrew.tel.R;

/**
 * Created by corpa on Aug 19, 2016
 */

public class LoadScreenActivity extends AppCompatActivity {

    private static final String downloadString = "http://www.techxlab.org/pages.json";
    private static final int REQUEST_PERMISSIONS = 1;

    private static final int NO_NETWORK_STATE = 0;
    private static final int WIFI_STATE = 1;
    private static final int MOBILE_DATA_STATE = 2;

    private static String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    private String version = "file.json";
    private boolean sync = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //get sync from intent
            sync = bundle.getBoolean("sync");
        }

        if (!hasPermissions(this)) {//if doesnt have permissions.
            requestNeededPermissions(this);//request the permissions the app needs.
//            boolean hasWifi = checkWifiOnAndConnected();
        } else {//if has permissions
            int connectionType = hasConnection();
            if (connectionType == WIFI_STATE) { //check to see if it has wifi
                startDownloadTask(sync, version); //if has connection start download activity
            } else if (connectionType == MOBILE_DATA_STATE) {
                //new DataDialogFragment().show(getFragmentManager(), "Are you sure you want to download using data?");//Todo fix this
                startDownloadTask(sync, version); //if has connection start download activity
            } else {
                new NoInternetDialogFragment().show(getFragmentManager(), "No Internet, can't download json.");//if it doesnt start no internet fragment
            }
        }
    }

    public boolean hasPermissions(Activity activity) {
        for (String permission : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Requests the permissions needed from the user
     *
     * @param activity
     */
    public void requestNeededPermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int callPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        int wifiPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_NETWORK_STATE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED || callPermission != PackageManager.PERMISSION_GRANTED || wifiPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS,
                    REQUEST_PERMISSIONS
            );
        }
    }

    /**
     * If the user denies the permissions needed, it asks again, overriden from super class
     *
     * @param requestCode  - REQUEST_PERMISSIONS
     * @param permissions  - array of permissions
     * @param grantResults - array of results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission Was Granted

                    startDownloadTask(sync, version);
                } else {
                    //Permission Was Not Granted
                    requestNeededPermissions(this);
                }
        }
    }

    /**
     * Creates the new Download Async Task, unless the file is already downloaded
     *
     * @param sync
     * @param version
     */
    public void startDownloadTask(boolean sync, String version) {
        SharedPreferences sharedPreferences = getSharedPreferences("favoritesFile", 0);

        if (sync || sharedPreferences.getBoolean("firstRun", true) || !(new File(Environment.getExternalStorageDirectory().toString() + "/" + version).exists())) {
            setContentView(R.layout.loading_screen);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            //TODO Check if connected to internet before attempting to download.
            DownloadFileTask downloadFileTask = new DownloadFileTask(this, progressBar);
            downloadFileTask.execute(version, downloadString);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private int hasConnection() {//TODO MAKE IT RETURN IF YOU ARE ON DATA OR WIFI - RENAME getConnectionType
        ConnectivityManager internetService = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = internetService.getActiveNetworkInfo();

        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "Wifi", Toast.LENGTH_LONG).show();
                return WIFI_STATE;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Mobile 3G ", Toast.LENGTH_LONG).show();
                return MOBILE_DATA_STATE;
            } else {
                return -1;//TODO Figure out why it would ever get here
            }
        } else {
            Toast.makeText(this, "No Network ", Toast.LENGTH_LONG).show();
            return NO_NETWORK_STATE;
        }
    }

}
