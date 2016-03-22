package corp.andrew.tel;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import json.Parsing;
import json.Solution;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Solution> allSolutions;
    List<ListItem> alllistItemSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Parsing parsing = new Parsing();

        allSolutions = parsing.parseJson(this);

        List<ListItem> solutions = new ArrayList<>();
        long startTime = System.nanoTime();
        //final List<Solution> solutionList = parsing.parseJson(this);

        for(Solution s : allSolutions){
            ListItem item = new ListItem(s.getName(),s.getContactName(),R.drawable.coolbot,false);
            solutions.add(item);
        }

        alllistItemSolutions = solutions;

        ListItemAdapter listItemAdapter = new ListItemAdapter(this, 0, solutions);

        final ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(listItemAdapter);

        // When the list is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicked it! ID = " + allSolutions.get(position).getReferenceId() , Toast.LENGTH_SHORT).show();
            }
        });

        long endTime = System.nanoTime();

        long timeTaken = (endTime - startTime);
        double seconds = (double) timeTaken / 1000000000.0;
        Toast.makeText(this, "It took: " + seconds, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Sorting sorting = new Sorting(allSolutions);
        int id = item.getItemId();
        final ListView listView = (ListView) findViewById(R.id.ListView);

        if (id == R.id.nav_all_solutions) {
            listView.setAdapter(new ListItemAdapter(this,0,alllistItemSolutions));
        } else if (id == R.id.nav_favorites) {
            listView.setAdapter(sorting.getFavoritesList(this));
        } else if (id == R.id.nav_settings) {
            //setContentView(R.layout.settings_layout);
        } else if (id == R.id.nav_agriculture_tools) {
            listView.setAdapter(sorting.getSolutionList("agriculture,tools", this));
        } else if (id == R.id.nav_energy_cooking) {
            listView.setAdapter(sorting.getSolutionList("energy,cooking", this));
        } else if (id == R.id.nav_health_medical) {
            listView.setAdapter(sorting.getSolutionList("health,medical", this));
        } else if (id == R.id.nav_education_connectivity) {
            listView.setAdapter(sorting.getSolutionList("education", this));
        } else if (id == R.id.nav_housing_transport) {
            listView.setAdapter(sorting.getSolutionList("housing", this));
        } else if (id == R.id.nav_water_sanitation) {
            listView.setAdapter(sorting.getSolutionList("water", this));
        } else if (id == R.id.nav_additional) {
            listView.setAdapter(sorting.getSolutionList("other", this));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
