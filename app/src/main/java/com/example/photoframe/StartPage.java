package com.example.photoframe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class StartPage extends AppCompatActivity {
  private int p,c=0;
  private  ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();


        VideoView videoView = findViewById(R.id.videoViewID);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vv);
        videoView.setVideoURI(uri);
        videoView.start();

        progressBar = findViewById(R.id.progressBarId);

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                c++;
                progressBar.setProgress(c);
                if (c == 100) {
                    t.cancel();
                    appStart();
                }

            }
        };
        t.schedule(tt, 0, 25);
    }

    public void appStart(){
        Intent intent= new Intent(StartPage.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}