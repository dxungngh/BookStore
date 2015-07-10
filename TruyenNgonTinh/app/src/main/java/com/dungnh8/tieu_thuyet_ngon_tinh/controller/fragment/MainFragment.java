package com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dungnh8.tieu_thuyet_ngon_tinh.R;
import com.dungnh8.tieu_thuyet_ngon_tinh.config.ExtrasConfig;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.activity.MainActivity;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment.tab.HotTabFragment;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment.tab.HistoryTabFragment;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment.tab.NewBookTabFragment;

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";

    private FragmentTabHost tabHost;

    private int selectedBookType;

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragment.selectedBookType = sectionNumber - 1;
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        drawTabs();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
            getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void drawTabs() {
        Log.i(TAG, "draw tabs");
        tabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
        Bundle bundle = new Bundle();
        bundle.putInt(ExtrasConfig.SELECTED_BOOK_TYPE, selectedBookType);

        tabHost.addTab(
            tabHost.newTabSpec("tab1").setIndicator(getString(R.string.history), null),
            HistoryTabFragment.class, null);
        tabHost.addTab(
            tabHost.newTabSpec("tab2").setIndicator(getString(R.string.new_book), null),
            NewBookTabFragment.class, bundle);
        tabHost.addTab(
            tabHost.newTabSpec("tab3").setIndicator(getString(R.string.hot), null),
            HotTabFragment.class, null);

        tabHost.setCurrentTab(1);
    }
}