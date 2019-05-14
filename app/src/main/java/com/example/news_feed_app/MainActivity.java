package com.example.news_feed_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Variable declarations
     */
    private ImageButton searchBtn;
    private ImageButton cancelBtn;
    private SharedPreferences sp;
    private ListView searchTags;
    private SQLiteDatabase sqLiteDatabase;
    private EditText searchText;
    private String searchedItems;
    private Cursor query;
    private ArrayAdapter<String> adapterTags;
    private ArrayList<String> feedSearchTags;
    private FeedDatabase feedDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBtn = (ImageButton) findViewById(R.id.searchBtn);
        cancelBtn = (ImageButton) findViewById(R.id.cancelBtn);
        searchTags = (ListView) findViewById(R.id.searchTags);
        searchText = (EditText) findViewById(R.id.searchText);
        sp = getSharedPreferences("searchTag", Context.MODE_PRIVATE);

        feedDatabase = new FeedDatabase(this);
        sqLiteDatabase = feedDatabase.getReadableDatabase();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchedItems = searchText.getText().toString();

                if (!(searchedItems == null || searchedItems.equals(""))) {
                    openSearchActivity(searchedItems);
                } else {

                    Toast.makeText(MainActivity.this, "Please, type something!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadData();

        searchTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                searchText.setText(feedSearchTags.get(position));
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.setText("");
            }
        });

    }

    private void openSearchActivity(String searchedItems) {

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("text", searchedItems);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra("Search", searchedItems);
        startActivity(intent);
    }

    private void loadData() {

        String savedSearch = sp.getString("text", "");
        searchText.setText(savedSearch);

        //query data from Table: Search_Tags to inflate ListView
        query = (Cursor) sqLiteDatabase.query(false, FeedDatabase.TABLE_SEARCH_TAGS, new String[]{FeedDatabase.COL_ID,
                FeedDatabase.COL_TAG}, null, null, null, null, null, null);

        int searchTag = query.getColumnIndex(FeedDatabase.COL_TAG);
        feedSearchTags = new ArrayList<>();

        while (query.moveToNext()) {

            String searchTagText = query.getString(searchTag);
            Log.i("Item from database :" , searchTagText);
            feedSearchTags.add(searchTagText);
        }

        // set the adapter to the list view
        adapterTags = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, feedSearchTags);
        searchTags.setAdapter(adapterTags);
        adapterTags.notifyDataSetChanged();
    }


}