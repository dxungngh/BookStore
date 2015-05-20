package com.dungnh8.truyen_ngon_tinh.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.config.ExtrasConfig;
import com.dungnh8.truyen_ngon_tinh.controller.fragment.ChapDetailFragment;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;

public class ChapDetailActivity extends BaseActivity {
    private static final String TAG = ChapDetailActivity.class.getSimpleName();

    private ChapDetailFragment fragment;

    private Book book;
    private Chap chap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initData();
        drawComponentViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chap_detail_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_back:
                goToPrevChap();
                return true;
            case R.id.action_next:
                goToNextChap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void drawActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(chap.getTitle());
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void drawBookDetailFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = ChapDetailFragment.newInstance(book, chap);
        fragmentManager.beginTransaction()
            .replace(R.id.book_detail_container, fragment)
            .commit();
    }

    private void drawComponentViews() {
        drawActionBar();
        drawBookDetailFragment();
    }

    private void goToNextChap() {
        fragment.goToNextChap();
    }

    private void goToPrevChap() {
        fragment.goToPrevChap();
    }

    private void initData() {
        book = (Book) getIntent().getSerializableExtra(ExtrasConfig.BOOK);
        chap = (Chap) getIntent().getSerializableExtra(ExtrasConfig.CHAP);
    }
}