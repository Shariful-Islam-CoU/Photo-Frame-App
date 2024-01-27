package com.example.photoframe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;


public class Create extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Create Frame");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#808080"));
        actionBar.setBackgroundDrawable(colorDrawable);

        CardView birthdayView = findViewById(R.id.birthdayID);
        CardView flowerView = findViewById(R.id.flowerId);
        CardView loveView = findViewById(R.id.loveID);
        CardView natureView = findViewById(R.id.natureID);

        birthdayView.setOnClickListener(this);
        flowerView.setOnClickListener(this);
        loveView.setOnClickListener(this);
        natureView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.birthdayID){
            Intent intent= new Intent(Create.this,Birthday.class);
            startActivity(intent);
            finish();
        }
       else if(view.getId()==R.id.flowerId){
            Intent intent= new Intent(Create.this,Flowers.class);
            startActivity(intent);
            finish();}
      else  if(view.getId()==R.id.loveID){
            Intent intent= new Intent(Create.this,Love.class);
            startActivity(intent);
            finish();}
      else  if(view.getId()==R.id.natureID){
            Intent intent= new Intent(Create.this,Nature.class);
            startActivity(intent);
            finish();}

    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(Create.this,MainActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }
}