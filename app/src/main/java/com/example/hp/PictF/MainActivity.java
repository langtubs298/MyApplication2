package com.example.hp.PictF;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fragment.AboutFragment;
import com.fragment.AskFragment;
import com.fragment.FeedbackFragment;
import com.fragment.FlashCardFragment;
import com.fragment.GameFragment;
import com.fragment.IntonationFragment;
import com.fragment.LinkingSoundFragment;
import com.fragment.PictureFragment;
import com.fragment.PronunciationFragment;
import com.fragment.SettingFragment;
import com.fragment.StressFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        }

        return super.onOptionsItemSelected(item);
    }

    //To call fragment
    public void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
        transaction.replace(R.id.fmContent, fragment);
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_picture) {

            callFragment(new PictureFragment());
        } else if (id == R.id.nav_flashCard) {

            callFragment(new FlashCardFragment());
        } else if (id == R.id.nav_pronunciation) {

            callFragment(new PronunciationFragment());
        } else if (id == R.id.nav_esd) {

            callFragment(new GameFragment());
        }
        else if(id==R.id.nav_intonation){

            callFragment(new IntonationFragment());
        }
        else if(id==R.id.nav_linking){

            callFragment(new LinkingSoundFragment());
        }
        else if(id==R.id.nav_stress){

            callFragment(new StressFragment());
        }
        else if(id==R.id.nav_ask){

            callFragment(new AskFragment());
        }
        else if (id == R.id.nav_feedBack) {

            callFragment(new FeedbackFragment());
        } else if (id == R.id.nav_about) {

            callFragment(new AboutFragment());
        } else if (id == R.id.nav_setting){
            callFragment(new SettingFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
