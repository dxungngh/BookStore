package com.dungnh8.truyen_ngon_tinh.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.business.BookBusiness;
import com.dungnh8.truyen_ngon_tinh.business.ChapBusiness;
import com.dungnh8.truyen_ngon_tinh.config.ExtrasConfig;
import com.dungnh8.truyen_ngon_tinh.controller.activity.ChapDetailActivity;
import com.dungnh8.truyen_ngon_tinh.controller.adapter.ChapAdapter;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBookDetailListener;
import com.dungnh8.truyen_ngon_tinh.utils.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BookDetailFragment extends Fragment {
    private static final String TAG = BookDetailFragment.class.getSimpleName();

    private Book book;
    private int index = 1;
    private int preLast;
    private List<Chap> chapList;
    private Chap currentChap;

    private ImageView avatarImageView;
    private TextView titleTextView, authorTextView, catNameTextView, shortContentTextView, currentChapTextView;
    private ListView chapsListView;
    private ChapAdapter chapAdapter;

    private BookBusiness bookBusiness;
    private ChapBusiness chapBusiness;

    public static BookDetailFragment newInstance(Book book) {
        BookDetailFragment fragment = new BookDetailFragment();
        fragment.book = book;
        return fragment;
    }

    public BookDetailFragment() {
        initData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        setComponentViews(view);
        drawComponentViews();
        setAllListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBookDetail();
    }

    private void drawComponentViews() {
        ImageLoader.getInstance().displayImage(book.getThumbnail(), avatarImageView, ImageLoaderUtil.getDisplayOptions());
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        catNameTextView.setText(book.getCatName());
        shortContentTextView.setText(book.getDescription());

        if (book.getCurrentChap() > 0) {
            currentChapTextView.setVisibility(View.VISIBLE);
            currentChap = chapBusiness.getChapFromDatabase(book.getCurrentChap());
            currentChapTextView.setText(getString(R.string.current_chap) + currentChap.getTitle());
        } else {
            currentChapTextView.setVisibility(View.GONE);
        }
    }

    private void drawListOfChaps(List<Chap> chaps) {
        if (chapAdapter == null) {
            chapList = new ArrayList<>();
            chapList.addAll(chaps);
            chapAdapter = new ChapAdapter(getActivity(), chapList, book);
            chapsListView.setAdapter(chapAdapter);
        } else {
            chapList.addAll(chaps);
            chapAdapter.notifyDataSetChanged();
        }
    }

    private void getBookDetail() {
        if (index <= book.getPages() || book.getPages() <= 0) {
            bookBusiness.getBookDetail(book.getApi(), index, new OnGetBookDetailListener() {
                @Override
                public void onSuccess(List<Chap> result) {
                    index++;
                    drawListOfChaps(result);
                }

                @Override
                public void onFailed(Throwable error) {
                    Log.e(TAG, "getBookDetail", error);
                }
            });
        }
    }

    private void initData() {
        bookBusiness = (BookBusiness) ServiceRegistry.getService(BookBusiness.TAG);
        chapBusiness = (ChapBusiness) ServiceRegistry.getService(ChapBusiness.TAG);
    }

    private void setAllListener() {
        setChapListScrollToBottomListener();
        setCurrentChapClickListener();
    }

    private void setChapListScrollToBottomListener() {
        chapsListView.setOnScrollListener(
            new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    final int lastItem = firstVisibleItem + visibleItemCount;
                    if (lastItem == totalItemCount) {
                        if (preLast != lastItem) {
                            preLast = lastItem;
                            getBookDetail();
                        }
                    }
                }
            }
        );
    }

    private void setCurrentChapClickListener() {
        currentChapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book.getCurrentChap() > 0) {
                    Intent intent = new Intent(getActivity(), ChapDetailActivity.class);
                    intent.putExtra(ExtrasConfig.BOOK, book);
                    intent.putExtra(ExtrasConfig.CHAP, currentChap);
                    startActivity(intent);
                }
            }
        });
    }

    private void setComponentViews(View rootView) {
        avatarImageView = (ImageView) rootView.findViewById(R.id.fragment_book_detail_image);
        titleTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_title);
        authorTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_author);
        catNameTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_cat_name);
        shortContentTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_short_content);
        currentChapTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_current_chap);
        chapsListView = (ListView) rootView.findViewById(R.id.fragment_book_detail_chaps);
    }
}