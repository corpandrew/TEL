package corp.andrew.tel;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import json.Parsing;
import json.Solution;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Solution> allSolutions;

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText editSearch;
    private ListView listView;
    private ListItemAdapter listItemAdapter;

    public MainActivity mainActivityReference;

    public MainActivity(){
        mainActivityReference = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long startTime = System.nanoTime();
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

//        getWindow().setNavigationBarColor(getResources().getColor(R.color.green));

        Parsing parsing = new Parsing(this);

        allSolutions = parsing.parseJson(this);
        //final List<Solution> solutionList = parsing.parseJson(this);

        listItemAdapter = new ListItemAdapter(this, 0, allSolutions);

        listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(listItemAdapter);

        // When the list is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solution s = (Solution) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Name = " + allSolutions.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), SolutionActivity.class);
                i.putExtra("solution", s);
                i.putExtra("positon", position);
                startActivity(i);
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
        } else if (isSearchOpened) {
            handleMenuSearch();
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
            setContentView(R.layout.settings_layout);
            return true;
        } else if (id == R.id.action_search) {
            handleMenuSearch();
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
            listView.setAdapter(new ListItemAdapter(this, 0, allSolutions));
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solution s = (Solution) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Name = " + s.getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), SolutionActivity.class);
                i.putExtra("solution", s);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    protected void handleMenuSearch() {
        final ActionBar actionBar = getSupportActionBar();

        if (isSearchOpened) {
            if(actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(false);
                actionBar.setDisplayShowTitleEnabled(true);
            }

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);

            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_action_search));

            isSearchOpened = false;
        } else {
            if(actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setCustomView(R.layout.search_bar);
                actionBar.setDisplayShowTitleEnabled(false);


                editSearch = (EditText) actionBar.getCustomView().findViewById(R.id.editSearch);
            }

            editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch(v);
                        return true;
                    }
                    return false;
                }
            });

            editSearch.requestFocus();

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editSearch, InputMethodManager.SHOW_IMPLICIT);

                mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_action_search));

            isSearchOpened = true;
        }
    }

    private void doSearch(TextView v) {

        String text = v.getText().toString();

        Sorting sorting = new Sorting(allSolutions);

        final ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(sorting.getSearchedEntries(getApplicationContext(), text));

        // When the list is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solution s = (Solution) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Name = " + allSolutions.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), SolutionActivity.class);
                i.putExtra("solution", s);
                startActivity(i);
            }
        });

    }

    public ListItemAdapter getListItemAdapter() {
        return listItemAdapter;
    }

    public void setListItemAdapter(ListItemAdapter listItemAdapter){
        this.listItemAdapter = listItemAdapter;
    }

    public MainActivity getMainActivityReference() {
        return mainActivityReference;
    }

    public List<Solution> getAllSolutions() {
        return allSolutions;
    }
}
