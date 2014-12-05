package bookstore.daniel.com.bookstore.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import bookstore.daniel.com.bookstore.R;
import bookstore.daniel.com.bookstore.fragment.NavigationDrawerFragment;
import bookstore.daniel.com.bookstore.fragment.PlaceholderFragment;


public class MainActivity extends ActionBarActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static final String TAG = MainActivity.class.getSimpleName();

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponentViews();
        setupComponentViews();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
            .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    private void setComponentViews() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
            getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
    }

    private void setupComponentViews() {
        mNavigationDrawerFragment.setUp(
            R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }
}