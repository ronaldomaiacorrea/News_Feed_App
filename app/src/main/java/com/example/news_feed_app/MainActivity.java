package com.example.news_feed_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static int ACTIVITY_BOOKMARK = 25;
    private static int ACTIVITY_HELP = 26;
    private static int ACTIVITY_ABOUT = 27;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                Intent bookmark = new Intent(MainActivity.this, BookmarkActivity.class);
                startActivityForResult(bookmark, 25 );

            case R.id.help:

                Intent help = new Intent(MainActivity.this, HelpActivity.class);
                startActivityForResult(help, 26);

            case R.id.about:

                Intent about = new Intent(MainActivity.this, AboutActivity.class);
                startActivityForResult(about, 27);
        }

        return true;
    }




}
