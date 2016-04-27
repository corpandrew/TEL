package corp.andrew.tel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by corpa on 4/21/2016.
 */

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_layout);

        RadioButton test = (RadioButton) findViewById(R.id.radioTest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("DEBUG","Test Clicked");
            }
        });

    }


}
