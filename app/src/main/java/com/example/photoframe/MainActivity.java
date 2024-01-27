package com.example.photoframe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    double backPressTime;
  Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#808080"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);

        LinearLayout createLayout = findViewById(R.id.createId);
        LinearLayout creationLayout = findViewById(R.id.creationId);
        LinearLayout rateLayout = findViewById(R.id.rateId);
        LinearLayout shareLayout = findViewById(R.id.shareId);

        createLayout.setOnClickListener(this);
        creationLayout.setOnClickListener(this);
        rateLayout.setOnClickListener(this);
        shareLayout.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.createId){
            Intent createIntent = new Intent(MainActivity.this, Create.class);
            startActivity(createIntent);
            finish();
        }
       else if(v.getId()==R.id.creationId){
            Intent creationsIntent = new Intent(MainActivity.this, Creations.class);
            startActivity(creationsIntent);
            finish();
        }
       else if(v.getId()==R.id.rateId){
            //Toast.makeText(MainActivity.this,"Rate Us",Toast.LENGTH_SHORT).show();
           // Intent intent=new Intent(MainActivity.this,StartPage.class);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=photoframe.vkraushanapp.com.photoframe")));
        }
         else if(v.getId()==R.id.shareId){
            //Toast.makeText(MainActivity.this,"Share",Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            intent.putExtra(Intent.EXTRA_TEXT,"Share with others. \n\nhttps://play.google.com/store/apps/details?id=photoframe.vkraushanapp.com.photoframe");

            startActivity(Intent.createChooser(intent,"Share app link"));
        }


    }

    @Override
    public void onBackPressed() {
      if(backPressTime+2000>System.currentTimeMillis()){
          backToast.cancel();
          super.onBackPressed();
      }else{
         backToast = Toast.makeText(MainActivity.this,"Press back again to exit",Toast.LENGTH_SHORT);
         backToast.show();
      }
         backPressTime=System.currentTimeMillis();
    }


}