package com.example.root.appnotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashSet;

public class EditActivity extends AppCompatActivity implements TextWatcher{
    int noteLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText editText = (EditText) findViewById(R.id.editText);

        Intent i = getIntent();

        noteLocation = i.getIntExtra("item", -1);
        if(noteLocation != -1)
        {
            editText.setText(MainActivity.notesArrayList.get(noteLocation));
        }
        editText.addTextChangedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        MainActivity.notesArrayList.set(noteLocation,String.valueOf(s));
        MainActivity.notesArrayAdapter.notifyDataSetChanged();
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.root.appnotes", Context.MODE_PRIVATE);
        MainActivity.notesSet = new HashSet<>(MainActivity.notesArrayList);
        MainActivity.notesSet.clear();
        MainActivity.notesSet.addAll(MainActivity.notesArrayList);
        sharedPreferences.edit().putStringSet("notes",MainActivity.notesSet).apply();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
