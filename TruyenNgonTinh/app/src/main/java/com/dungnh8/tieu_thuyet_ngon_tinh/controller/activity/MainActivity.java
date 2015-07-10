package com.dungnh8.tieu_thuyet_ngon_tinh.controller.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.dungnh8.tieu_thuyet_ngon_tinh.R;
import com.dungnh8.tieu_thuyet_ngon_tinh.config.BookConfig;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment.MainFragment;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment.NavigationDrawerFragment;

public class MainActivity extends BaseActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static final String TAG = MainActivity.class.getSimpleName();

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private int bookType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawNavigationDrawerFragment();
    }

    @Override
    public void onBackPressed() {
        showConfirmPopup(
            getString(R.string.app_name),
            getString(R.string.message_exit_app),
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String uri = String.format("market://details?id=%s", getPackageName());
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                    MainActivity.this.onBackPressed();
                    finish();
                }
            },
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.onBackPressed();
                    finish();
                }
            },
            true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_actions, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance(position + 1))
            .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                goToSearchScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void drawNavigationDrawerFragment() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
            getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(
            R.id.navigation_drawer,
            (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    private void goToSearchScreen() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void onSectionAttached(int number) {
        bookType = number - 1;
        mTitle = BookConfig.BOOK_TYPE[bookType];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
    }
}