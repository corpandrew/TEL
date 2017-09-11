package corp.andrew.tel.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

import corp.andrew.tel.R;

/**
 * Created by corpa on Aug 19, 2016
 */

public class FilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ArrayList<String> checkedItems = new ArrayList<>();

//        final Sorting solutionIntoClass = (Sorting) getIntent().getExtras().getSerializable("sorting");

        final CheckBox radioButton = (CheckBox) findViewById(R.id.radio);
        final Button searchButton = (Button) findViewById(R.id.search_button);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.65));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all the filters and apply them to the current listitems.
                //then close the activity and show them

                if (radioButton.isChecked()) {
                    checkedItems.add("");
                }
            }
        });


    }
}
