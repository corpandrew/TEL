package activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
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

import java.util.List;

import fragments.SyncDialogFragment;
import corp.andrew.tel.ListItemAdapter;
import corp.andrew.tel.R;
import corp.andrew.tel.Sorting;
import json.Parsing;
import json.Solution;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Storage Permissions variables
    public MainActivity activity;
    public List<Solution> allSolutions;
    public SharedPreferences favoriteSharedPrefs;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText editSearch;
    private ListView listView;
    private ListItemAdapter currentListItemAdapter;
    private ListItemAdapter tempListItemAdapter;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Spanned tooolbarText;

    private int lastScrollIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        long startTime = System.nanoTime(); // For logging the time it takes to create everything on the main activity

//        FlurryAgent.init(this,"Q963TJRGRQC7DNTH9NHX");

        activity = this;
        super.onCreate(savedInstanceState);

        favoriteSharedPrefs = getSharedPreferences("favoritesFile", 0);

        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        toolbar = (Toolbar) findViewById(R.id.toolbar);//Top bar with the settings and search
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        tooolbarText = Html.fromHtml("<b>tel </b> / <i>All Solutions</i>");
        toolbar.setTitle(tooolbarText);
        //toolbar.setTextAppearance();//TODO make text smaller.

        setSupportActionBar(toolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_sync_white_24px);
        toolbar.setOverflowIcon(drawable);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Parsing parsing = new Parsing(this);// creates the parsing object

        allSolutions = parsing.parseJson();

        listView = (ListView) findViewById(R.id.ListView);
        // When the list is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solution s = (Solution) listView.getItemAtPosition(position);
                Intent i = new Intent(view.getContext(), SolutionActivity.class);
                i.putExtra("solution", s);
                i.putExtra("position", position);
                startActivity(i);
            }
        });

        onNavigationItemSelected(navigationView.getMenu().getItem(0));

//        long endTime = System.nanoTime();

//        long timeTaken = (endTime - startTime);
//        double seconds = (double) timeTaken / 1000000000.0;
    }

    private int getCheckedItem(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.isChecked()) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (isSearchOpened) {
            handleMenuSearch();
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Searched Solutions</i>");
            toolbar.setTitle(tooolbarText);
        } else if (getCheckedItem(navigationView) == 0 || getCheckedItem(navigationView) == -1) {
            finish();//todo still a littly buggy
        } else {
            onNavigationItemSelected(navigationView.getMenu().getItem(1));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        lastScrollIndex = listView.getFirstVisiblePosition();
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.setAdapter(tempListItemAdapter);
        listView.setSelection(lastScrollIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sync) {
            checkForUpdates();
            return true;
        } else if (id == R.id.action_search) {
            handleMenuSearch();
            return true;
        } /*else if (id == R.id.action_filter) {
            handleFilter();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Sorting sorting = new Sorting(allSolutions, favoriteSharedPrefs, getSupportFragmentManager());
        int id = item.getItemId();
        final ListView listView = (ListView) findViewById(R.id.ListView);

        if (id == R.id.nav_all_solutions) {
            currentListItemAdapter = new ListItemAdapter(this, 0, allSolutions, favoriteSharedPrefs, getSupportFragmentManager());
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>All Solutions</i>");//todo Make strings for these
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_favorites) {
            currentListItemAdapter = sorting.getFavoritesList(this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Favorites</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_agriculture_tools) {
            currentListItemAdapter = sorting.getSolutionList("agriculture,tools", this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Agriculture & Tools</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_energy_cooking) {
            currentListItemAdapter = sorting.getSolutionList("energy,cooking", this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Energy & Cooking</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_health_medical) {
            currentListItemAdapter = sorting.getSolutionList("health,medical", this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Health & Medical</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_education_connectivity) {
            currentListItemAdapter = sorting.getSolutionList("education", this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Education Solutions</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_housing_transport) {
            currentListItemAdapter = sorting.getSolutionList("housing", this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Housing</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_water_sanitation) {
            currentListItemAdapter = sorting.getSolutionList("water", this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Water & Sanitation</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_additional) {
            currentListItemAdapter = sorting.getSolutionList("other", this);
            listView.setAdapter(currentListItemAdapter);
            tooolbarText = Html.fromHtml("<b>tel </b> / <i>Other Solutions</i>");
            toolbar.setTitle(tooolbarText);
        } else if (id == R.id.nav_about_us) {
            Intent i = new Intent(this, AboutUsActivity.class);
            startActivity(i);
        }

        tempListItemAdapter = currentListItemAdapter;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solution s = (Solution) listView.getItemAtPosition(position);
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
            if (actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(false);
                actionBar.setDisplayShowTitleEnabled(true);
            }

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);

            mSearchAction.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_search));//todo check works

            isSearchOpened = false;
        } else {
            if (actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setCustomView(R.layout.search_bar);
                actionBar.setDisplayShowTitleEnabled(false);

                editSearch = (EditText) actionBar.getCustomView().findViewById(R.id.editSearch);

                editSearch.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editSearch, InputMethodManager.SHOW_IMPLICIT);
            }

            editSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //not needed
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    doSearch(editSearch.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    //not needed
                }
            });
            editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch(v.getText().toString());
                        return true;
                    }
                    return false;
                }
            });

            editSearch.requestFocus();

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editSearch, InputMethodManager.SHOW_IMPLICIT);

            mSearchAction.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_search));

            isSearchOpened = true;
        }
    }

    private void doSearch(String text) {
        Sorting sorting = new Sorting(allSolutions, favoriteSharedPrefs, getSupportFragmentManager());

        final ListView listView = (ListView) findViewById(R.id.ListView);
        currentListItemAdapter = sorting.getSearchedEntries(getApplicationContext(), text);
        listView.setAdapter(currentListItemAdapter);

        // When the list is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solution s = (Solution) listView.getItemAtPosition(position);
                Intent i = new Intent(view.getContext(), SolutionActivity.class);
                i.putExtra("solution", s);
                startActivity(i);
            }
        });
        tempListItemAdapter = currentListItemAdapter;
    }

    private void checkForUpdates() {
        new SyncDialogFragment().show(getFragmentManager(), "");
    }
}
