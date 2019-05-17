package com.example.news_feed_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Toolbar toolBar;
    private ListView listView;
    private String savedSearch;
    private ArrayList<NewsFeed> newsFeeds;
    private FeedAdapter feedAdapter;
    private SQLiteDatabase db;
    private FeedDatabase feedDatabase;
    private FeedSearchTag feedSearchTag;

    private static int ACTIVITY_BOOKMARK = 23;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toolBar = (Toolbar) findViewById(R.id.myToolBar);
        setSupportActionBar(toolBar);
        listView = (ListView) findViewById(R.id.listView);
        newsFeeds = new ArrayList<NewsFeed>();
        feedAdapter = new FeedAdapter();
        feedDatabase = new FeedDatabase(this);
        db = feedDatabase.getWritableDatabase();

        // get the previous search tag = subject
        Intent intent = getIntent();
        savedSearch = intent.getStringExtra("Search");
        Log.i("Search value is ", savedSearch);

        //add the previous search tag to Database
        feedSearchTag = new FeedSearchTag(savedSearch);
        ContentValues newSearch = new ContentValues();
        newSearch.put(FeedDatabase.COL_TAG, feedSearchTag.getSearchTag());
        long id = db.insert(FeedDatabase.TABLE_SEARCH_TAGS, null, newSearch);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.share:

                shareItem();
                break;

            case R.id.bookmark:

                bookmark();
                break;

            case R.id.about:

                about();
                break;
        }

        return true;

    }

    private void bookmark() {

        Intent bookmarks = new Intent(SearchActivity.this, BookmarkActivity.class);
        startActivityForResult(bookmarks, 23);

    }

    private void about() {

    }

    private void shareItem() {


    }

    public class FeedAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsFeeds.size();
        }

        @Override
        public Object getItem(int position) {
            return newsFeeds.get(position);
        }

        @Override
        public View getView(int position, View old, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View rowView = layoutInflater.inflate(R.layout.activity_row, parent, false);

            //ImageView imageView = (U)
            TextView rowName = (TextView) rowView.findViewById(R.id.name);
            TextView rowTitle = (TextView) rowView.findViewById(R.id.titleText);
            TextView rowUrl = (TextView) rowView.findViewById(R.id.url);

            rowName.setText(newsFeeds.get(position).getName());
            rowTitle.setText(newsFeeds.get(position).getTitle());
            rowUrl.setText(newsFeeds.get(position).getUrl());

            Log.i("Adapter:Row value Name", newsFeeds.get(position).getName());
            Log.i("Adapter:Row value Title", newsFeeds.get(position).getTitle());
            Log.i("Adapter:Row value Link", newsFeeds.get(position).getUrl());

            //return the row:
            return rowView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    }



}
