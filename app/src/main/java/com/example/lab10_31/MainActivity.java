package com.example.lab10_31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

import kotlin.DeepRecursiveFunction;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> listNoteItems = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    public ListView noteview;
    public Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd= findViewById(R.id.buttonAdd);
        noteview = findViewById(R.id.listOfNotes);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNoteItems);
        noteview.setAdapter(adapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        SharedPreferences sharedPref = this.getSharedPreferences("Notefile", this.MODE_PRIVATE);

        Set<String> savedSet = sharedPref.getStringSet("NoteArray", null);

        if(savedSet != null) {
            listNoteItems.clear();
            listNoteItems.addAll(savedSet);
            adapter.notifyDataSetChanged();
        }
        noteview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Delete_note.class);
                Bundle b = new Bundle();
                b.putString("memo",(String) noteview.getItemAtPosition(i));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Add_Note.class);
                startActivity(intent);
            }
        });
    }
}