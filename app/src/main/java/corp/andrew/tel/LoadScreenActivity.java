package corp.andrew.tel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by corpa on Aug 19, 2016
 */
public class LoadScreenActivity extends AppCompatActivity {

    private static final String downloadString = "http://www.techxlab.org/pages.json";
    private String version = "file.json";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        DownloadFileTask downloadFileTask = new DownloadFileTask(this, progressBar);
        downloadFileTask.execute(version, downloadString);

    }

}
