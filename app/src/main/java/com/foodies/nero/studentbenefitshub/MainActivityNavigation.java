package com.foodies.nero.studentbenefitshub;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;

import android.content.res.Configuration;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String[] titles;
    private TextView txtProfileName;
    private String email;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles = getResources().getStringArray(R.array.navigation_drawer_items_titles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (firebaseUser != null) {
            Menu menu = navigationView.getMenu();
            MenuItem login = menu.findItem(R.id.nav_login);
            login.setTitle("Logout");
        }
        try {
            if (FirebaseAuth.getInstance().getCurrentUser().getProviderData() != null) {
                Menu menu = navigationView.getMenu();
                MenuItem login = menu.findItem(R.id.nav_login);
                login.setTitle("Logout");
            }
        } catch (Exception e) {
            Intent intent = getIntent();
            String decideLogInName = intent.getStringExtra("SkippedLogin");
            if (decideLogInName == null) {
                Menu menu = navigationView.getMenu();
                MenuItem login = menu.findItem(R.id.nav_login);
                login.setTitle("Logout");
            }
        }
        if (firebaseUser != null) {
            email = firebaseUser.getEmail();
            txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userName);
            txtProfileName.setText(email);
        }
        navigationView.invalidate();
        navigationView.setItemIconTintList(null);

        Home home = new Home();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentFrame, home);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        this.getSupportActionBar().setTitle(("Home"));
    }

/*    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        int backCount = fragmentManager.getBackStackEntryCount();

        if (backCount < 0) {
            finish();
        }
    }
/*    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = new Fragment();

        if (id == R.id.nav_home) {
            Home home = new Home();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, home);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            this.getSupportActionBar().setTitle(("Home"));
        } else if (id == R.id.nav_nearme) {
            NearMe nearMe = new NearMe();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, nearMe);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            this.getSupportActionBar().setTitle(("Near Me"));
        }else if (id == R.id.nav_health_fitness) {
            HealthFitness healthFitness = new HealthFitness();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, healthFitness);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            this.getSupportActionBar().setTitle(("Health & Fitness"));
        }else if (id == R.id.nav_entertainment) {
            Entertainment entertainment = new Entertainment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, entertainment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            this.getSupportActionBar().setTitle(("Entertainment"));
        }else if (id == R.id.nav_eat) {
            Eat eat = new Eat();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, eat);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            this.getSupportActionBar().setTitle(("Eat"));
        }else if (id == R.id.nav_shop) {
            Shop shop = new Shop();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, shop);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            this.getSupportActionBar().setTitle(("Shop"));
        } else if (id == R.id.nav_login) {
            firebaseAuth.signOut();
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();
            startActivity(new Intent(this, LoginActivityPage.class));
            finish();
        } else if (id == R.id.nav_about) {
            About about = new About();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, about);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            this.getSupportActionBar().setTitle(("About"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
