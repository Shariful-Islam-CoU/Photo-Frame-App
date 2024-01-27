package com.example.photoframe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.widget.GridView;
import android.widget.Toast;

public class Nature extends AppCompatActivity {

    int[] photos={R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nature);

        ActionBar actionBar= getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Nature Frames");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#808080"));
        actionBar.setBackgroundDrawable(colorDrawable);

        GridView gridView = findViewById(R.id.natureGridviewID);

        CustomAdapter customAdapter= new CustomAdapter(this,photos);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener((adapterView, view, i, l) -> {

         //   Toast.makeText(Nature.this, "" + i + " is clicked", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Nature.this, Editor.class);
            intent.putExtra("i",i);
            intent.putExtra("num",4);
            startActivity(intent);
            finish();
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Nature.this, Create.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}