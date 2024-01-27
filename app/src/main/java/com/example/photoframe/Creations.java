package com.example.photoframe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.widget.GridView;
import android.widget.Toast;

public class Creations extends AppCompatActivity {

    int[] photos={R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif,R.drawable.sharif};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creations);

        ActionBar actionBar= getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Creations");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#808080"));
        actionBar.setBackgroundDrawable(colorDrawable);

        GridView gridView = findViewById(R.id.creationsGridviewID);

        CustomAdapter customAdapter= new CustomAdapter(this,photos);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(Creations.this,""+i+" is clicked",Toast.LENGTH_SHORT).show());

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Creations.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}