package com.example.lab10_31;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;


public class Add_Note extends AppCompatActivity {

    public EditText savingText;
    public Button savingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        savingText=findViewById(R.id.textSave);
        savingButton=findViewById(R.id.buttonSave);
        savingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteToAdd = savingText.getText().toString();


                //Current
                SharedPreferences sharedPref = getSharedPreferences("Notefile", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                Set<String> savedSet = sharedPref.getStringSet("NoteArray", null);
                Set<String> newSet = new HashSet<>();
                if(savedSet != null) {
                    newSet.addAll(savedSet);
                }
                newSet.add(noteToAdd);

                editor.putStringSet("NoteArray", newSet);
                editor.apply();
                finish();
            }
        });
    }

}