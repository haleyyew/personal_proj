package com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2;


import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

import static com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2.QueryLengthChopper.chopQuery;


public class SearchQueryRandomizer_version2 extends ActionBarActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener, MenuItem.OnMenuItemClickListener {
    TextView mainTextView;
    Button mainButton;
    Button mainSearchButton;
    EditText mainEditText;
    ListView mainListView;

    ArrayAdapter adapter;
    ArrayList arrayList = new ArrayList();
    Spinner mainSelector;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_query_randomizer_version2);
        mainTextView = (TextView) findViewById(R.id.textView);
        mainTextView.setText("Let's find out how good Google Custom Search is when searching for " +
                "your desired web page using your randomized query string.");
        mainButton = (Button) findViewById(R.id.randomizeButton);
        mainButton.setOnClickListener(this);
        mainSearchButton = (Button) findViewById(R.id.googleSearchButton);
        mainSearchButton.setOnClickListener(this);
        mainEditText = (EditText) findViewById(R.id.textbox);

        mainListView = (ListView) findViewById(R.id.listQuery);
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);

        mainSelector = (Spinner)findViewById(R.id.selector);
        String[] options = new String[]{"Do not randomize", "Delete 25% of query", "Delete 50% of query"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        mainSelector.setAdapter(adapter);
        mainSelector.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_query_randomizer_version2, menu);
        MenuItem shareItem = menu.findItem(R.id.action_settings);
        shareItem.setOnMenuItemClickListener(this);
        return true;
    }


    @Override
    public void onClick(View view) {
        String str = mainEditText.getText().toString();
        switch (view.getId()) {
/*            case  R.id.randomizeButton: {
                break;
            }*/
            case R.id.googleSearchButton: {
                arrayList.add(str);
                adapter.notifyDataSetChanged();
            }

            case R.id.selector: {
                String choppedString = chopQuery(str);
                mainEditText.setText(choppedString, TextView.BufferType.EDITABLE);
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        displayMessage(i, arrayList.get(i));
    }

    private void displayMessage(int i, Object o) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Page Title");
        alert.setMessage("Page URL");
        alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
        alert.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(this, "No settings can be configured for this simple android app.", Toast.LENGTH_LONG).show();
        return false;
    }
}
