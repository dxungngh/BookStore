package com.dungnh8.truyen_ngon_tinh.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.config.ExtrasConfig;
import com.dungnh8.truyen_ngon_tinh.controller.BaseActivity;
import com.dungnh8.truyen_ngon_tinh.controller.fragment.ChapDetailFragment;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;

public class ChapDetailActivity extends BaseActivity {
    private static final String TAG = ChapDetailActivity.class.getSimpleName();

    private Chap chap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initData();
        drawComponentViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
        fragmentManager.beginTransaction()
            .replace(R.id.book_detail_container, ChapDetailFragment.newInstance(chap))
            .commit();
    }

    private void drawComponentViews() {
        drawActionBar();
        drawBookDetailFragment();
    }

    private void initData() {
        chap = (Chap) getIntent().getSerializableExtra(ExtrasConfig.CHAP);
    }
}