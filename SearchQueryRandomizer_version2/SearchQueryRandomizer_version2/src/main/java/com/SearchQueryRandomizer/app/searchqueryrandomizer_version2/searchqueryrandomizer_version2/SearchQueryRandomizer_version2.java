package com.SearchQueryRandomizer.app.searchqueryrandomizer_version2.searchqueryrandomizer_version2;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;



public class SearchQueryRandomizer_version2 extends ActionBarActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener, MenuItem.OnMenuItemClickListener, AdapterView.OnItemSelectedListener, TextWatcher {
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
        mainTextView.setText("Let's find out how good Google Search is when searching for " +
                "your desired web page using your randomized query string. First add it to the search queue." +
                "Then click on one of the entries to search.");

        mainSearchButton = (Button) findViewById(R.id.googleSearchButton);
        mainSearchButton.setOnClickListener(this);
        mainEditText = (EditText) findViewById(R.id.textbox);
        mainEditText.addTextChangedListener(this);

        mainListView = (ListView) findViewById(R.id.listQuery);
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);

        mainSelector = (Spinner)findViewById(R.id.selector);
        String[] options = new String[]{"Do not randomly delete anything", "Delete 25% of query", "Delete 50% of query"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainSelector.setAdapter(arrayAdapter);
        mainSelector.setOnItemSelectedListener(this);
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

//            case R.id.selector: {
//                String choppedString = chopQuery(str);
//                mainEditText.setText(choppedString, TextView.BufferType.EDITABLE);
//            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        displayMessage(i, arrayList.get(i));
    }

    private void displayMessage(int i, Object o) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final String str = (String) o;
        alert.setTitle("Query: " + (String) o);
        alert.setMessage("Click on Search with Google to open your default internet browser, opens Google and" +
                "search for your selected query.");
        alert.setPositiveButton("Search with Google", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String finalQuery = getFinalQuery(str);
                Uri uri = Uri.parse(finalQuery);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        alert.show();
    }

    private String getFinalQuery(String str) {
        String concat = "http://www.google.com/";
        concat += "search?q=";
        concat += str.replaceAll(" ", "+");
        return concat;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(this, "No settings can be configured for this simple android app.", Toast.LENGTH_LONG).show();
        return false;
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int option = i;
        String str = mainEditText.getText().toString();
        QueryLengthChopper chopper = new QueryLengthChopper();
        String choppedString = chopper.chopQuery(str, option);
        mainEditText.setText(choppedString);
        mainSelector.setSelection(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        for(int i = editable.length(); i > 0; i--){

            if(editable.subSequence(i-1, i).toString().equals("\n"))
                editable.replace(i-1, i, "");

        }

    }
}
