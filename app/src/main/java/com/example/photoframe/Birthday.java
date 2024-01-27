package com.example.photoframe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Birthday extends AppCompatActivity {

    int[] photos={R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

       ActionBar actionBar= getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Birthday Frames");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#808080"));
        actionBar.setBackgroundDrawable(colorDrawable);

        GridView gridView = findViewById(R.id.birthdayGridviewID);

        CustomAdapter customAdapter= new CustomAdapter(this,photos);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(this::onItemClick);



    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(Birthday.this, "" + i + " is clicked", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Birthday.this, Editor.class);
        intent.putExtra("i",i);
        intent.putExtra("num",1);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Birthday.this, Create.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}