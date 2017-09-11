package corp.andrew.tel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import corp.andrew.tel.activities.LoadScreenActivity;
import corp.andrew.tel.activities.MainActivity;

/**
 * Created by corpa on Aug 19, 2016
 */

public class DownloadFileTask extends AsyncTask<String, Integer, String> {

    private LoadScreenActivity activity;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    public DownloadFileTask(LoadScreenActivity activity, ProgressBar progressBar) {
        this.activity = activity;
        this.progressBar = progressBar;
        sharedPreferences = activity.getSharedPreferences("favoritesFile", 0);
    }

    @Override
    protected String doInBackground(final String... f_url) {
        int count;
        try {
            String root = Environment.getExternalStorageDirectory().toString();

            System.out.println("Downloading");
            URL url = new URL(f_url[1]);

            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lengthOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file

            OutputStream output = new FileOutputStream(root + "/" + f_url[0]);
            byte data[] = new byte[1024];

            long total = 0;
            ProgressRunnable progressRunnable = new ProgressRunnable(progressBar, total, lengthOfFile);
            while ((count = input.read(data)) != -1) {
                total += count;

                progressRunnable.setTotal(total);
                progressRunnable.setLengthOfFile(lengthOfFile);
                //activity.runOnUiThread(progressRunnable);

                // writing data to file
                output.write(data, 0, count);

            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        System.out.println("Downloaded");
        sharedPreferences.edit().putBoolean("firstRun", false).apply();

        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}