package com.example.root.appnotes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    String noteName="";
    ListView notesListView ;
    static ArrayList<String> notesArrayList ;
    static ArrayAdapter<String> notesArrayAdapter;
    static Set<String> notesSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notesArrayList = new ArrayList<>();


        notesListView = (ListView) findViewById(R.id.notesListView);

        SharedPreferences notesShPref = this.getSharedPreferences("com.example.root.appnotes", Context.MODE_PRIVATE);

        notesSet = notesShPref.getStringSet("notes",null);



       /* if(notesSet!=null)
        {
            notesArrayList.addAll(notesSet);
        }else
        {
            notesSet = new HashSet<>(notesArrayList);

            notesArrayList.add("Add new Task .. ");

            notesSet.addAll(notesArrayList);

            notesShPref.edit().putStringSet("notes",notesSet).apply();

        }*/
        for(String data : notesShPref.getStringSet("notes",null))
        {
            Log.e("Content onCreate",data);
        }
        notesArrayList.addAll(notesSet);

        notesArrayAdapter = new ArrayAdapter<String>(this,R.layout.textviewlayout,notesArrayList);



        notesListView.setAdapter(notesArrayAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this,EditActivity.class);
                i.putExtra("item",position);
                startActivity(i);
            }
        });




        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {



                new AlertDialog.Builder(MainActivity.this).setTitle("Delete Note !")
                        .setIcon(android.R.drawable.ic_input_delete)
                        .setMessage("DELETE THIS NOTE ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notesArrayList.remove(position);
                                notesArrayAdapter.notifyDataSetChanged();
                                SharedPreferences notesShPref = MainActivity.this.getSharedPreferences("com.example.root.appnotes", Context.MODE_PRIVATE);

                                notesSet = notesShPref.getStringSet("notes",null);

                                notesSet.clear();

                                notesSet.addAll(notesArrayList);

                                notesShPref.edit().remove("notes").apply();

                                notesShPref.edit().putStringSet("notes",notesSet).apply();



                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


                return true;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final EditText edittext = new EditText(this);

        //noinspection SimplifiableIfStatement
        if (id == R.id.addNote) {

            final AlertDialog.Builder addNote = new AlertDialog.Builder(this);

            addNote.setTitle("Add Note !")
                    .setIcon(android.R.drawable.ic_input_add)
                    .setMessage("Add New Note :")
                    .setView(edittext)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences notesShPref = getSharedPreferences("com.example.root.appnotes",Context.MODE_PRIVATE);

                            notesSet = notesShPref.getStringSet("notes",null);

                            noteName = edittext.getText().toString();

                            notesSet=new HashSet<String>(notesArrayList);

                            notesSet.add(noteName);
                            for (String example : notesSet)
                            {
                                Log.e("Set Content",example);
                            }

                            notesShPref.edit().putStringSet("notes",notesSet).apply();

                            notesArrayList.clear();

                            notesArrayList.addAll(notesSet);

                            notesArrayAdapter.notifyDataSetChanged();

                            Log.e("String Content",noteName);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    })
                    .show();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
