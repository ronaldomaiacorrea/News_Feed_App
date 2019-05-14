package com.example.news_feed_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
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
            case R.id.bookmark:

                Intent bookmark = new Intent(SearchActivity.this, BookmarkActivity.class);
                startActivityForResult(bookmark, 25 );

            case R.id.help:

                Intent help = new Intent(SearchActivity.this, HelpActivity.class);
                startActivityForResult(help, 26);

            case R.id.about:

                Intent about = new Intent(SearchActivity.this, AboutActivity.class);
                startActivityForResult(about, 27);
        }

        return true;

    }
}
