package com.example.lab10_31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

public class Delete_note extends AppCompatActivity {

    public Button btn_yes, btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);
        btn_no=findViewById(R.id.buttonNo);
        btn_yes=findViewById(R.id.buttonYes);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = getIntent().getExtras();
                SharedPreferences sharedPref = getSharedPreferences("Notefile", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                Set<String> savedSet = sharedPref.getStringSet("NoteArray", null);
                savedSet.remove(b.getString("memo"));
                Set<String> newSet = new HashSet<>();
                if(savedSet != null) {
                    newSet.addAll(savedSet);
                }
                editor.putStringSet("NoteArray", newSet);
                editor.apply();
                finish();
            }
        });
    }
}