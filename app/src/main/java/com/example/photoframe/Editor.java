package com.example.photoframe;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Editor extends AppCompatActivity implements RotationGestureDetector.OnRotationGestureListener{

    int[] photos={R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1,R.drawable.sharif,R.drawable.sharif2,R.drawable.sharif3,R.drawable.sharif1};
    private double backPressTime;
   private Toast backToast;
    private Matrix matrix = new Matrix();
   private  float factor=1.0F,initialAngle,lastRotation=0,angle,aa;

    private RotationGestureDetector mRotationDetector;

   private  ImageView editorView;
   private LinearLayout editorLayout;
  private final int CODE = 1000;

  private Intent imgIntent;

  private int num;



  ActivityResultLauncher<String> activityResultLauncher;

  private AlertDialog.Builder alertdialogBuilder,alertdialogBuilder2;

  private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ActionBar actionBar= getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();


        mRotationDetector = new RotationGestureDetector(this);

        editorLayout=findViewById(R.id.editorLayoutID);

         editorView= findViewById(R.id.editorImageViewId);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            int a=bundle.getInt("i");
            num=bundle.getInt("num");
            editorLayout.setForeground(getDrawable(photos[a]));
            editorLayout.setBackground(getDrawable(R.drawable.flower));


        }


        scaleGestureDetector = new ScaleGestureDetector(
                this, new MySimpleOnScaleGestureListener(editorView));

        activityResultLauncher= registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        editorView.setImageURI(result);
                    }
                }

        );


        BottomNavigationView nev= findViewById(R.id.bottomNevID);
        nev.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.imageItemId:
                        //Toast.makeText(Editor.this, "IMAGE", Toast.LENGTH_SHORT).show();


                        activityResultLauncher.launch("image/*");


                        break;

                    case R.id.frameItemId:



                        alertdialogBuilder2= new AlertDialog.Builder(Editor.this);

                        alertdialogBuilder2.setTitle("Wait!");
                        alertdialogBuilder2.setMessage("Are you sure to discard your editing?");
                        alertdialogBuilder2.setIcon(R.drawable.alert_icon);
                        alertdialogBuilder2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent= new Intent(Editor.this,Create.class);
                                startActivity(intent);
                                finish();

                            }
                        });

                        alertdialogBuilder2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog alertDialog= alertdialogBuilder2.create();
                        alertDialog.show();

                        break;

                    case R.id.addTextItemId:
                        Toast.makeText(Editor.this, "ADD TEXT", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.stickerItemId:
                        Toast.makeText(Editor.this, "STICKERS", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.saveItemId:

                        Intent intent = new Intent(Editor.this,Creations.class);
                        startActivity(intent);
                        finish();

                        break;

                }


                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {

        alertdialogBuilder= new AlertDialog.Builder(this);

        alertdialogBuilder.setTitle("Really Exit?");
        alertdialogBuilder.setMessage("Are you sure to exit?");
        alertdialogBuilder.setIcon(R.drawable.alert_icon);
        alertdialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(num==1){
                    Intent intent = new Intent(Editor.this,Birthday.class);
                    startActivity(intent);
                    finish();
                }
              else  if(num==2){
                    Intent intent = new Intent(Editor.this,Flowers.class);
                    startActivity(intent);
                    finish();
                }
               else if(num==3){
                    Intent intent = new Intent(Editor.this,Love.class);
                    startActivity(intent);
                    finish();
                }
              else  if(num==4){
                    Intent intent = new Intent(Editor.this,Nature.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

        alertdialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog= alertdialogBuilder.create();
        alertDialog.show();

    }




    private double getAngle(double xTouch, double yTouch) {
        double x = xTouch - (editorView.getWidth() / 2d);
        double y = editorView.getHeight() - yTouch - (editorView.getHeight() / 2d);
        switch (getQuadrant(x, y)) {
            case 1:
                return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 2:
                return 180 - Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 3:
                return 180 + (-1 * Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
            case 4:
                return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            default:
                return 0;
        }
    }


/**
     * @return The selected quadrant.
     */

    private static int getQuadrant(double x, double y) {
        if (x >= 0) {
            return y >= 0 ? 1 : 4;
        } else {
            return y >= 0 ? 2 : 3;
        }
    }


  float x,y,dx,dy,r,dr,er,ir;
    int c=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if( event.getAction()==MotionEvent.ACTION_DOWN){
            x=event.getX();
            y=event.getY();

            r= (float) getAngle(x,y);



        }
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            dx=event.getX()-x;
            dy=event.getY()-y;

            er= (float) getAngle(event.getX(),event.getY());
            dr=er-r;

            ir= (float) getAngle(editorView.getX(),editorView.getY());

            editorView.setX(editorView.getX()+dx);
            editorView.setY(editorView.getY()+dy);


         //  editorView.setRotation(ir);



            x=event.getX();
            y=event.getY();


        }


        mRotationDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);

        return true;

    }



/*
@Override
public boolean onTouchEvent(MotionEvent event){
    mRotationDetector.onTouchEvent(event);
    return super.onTouchEvent(event);
}
*/


    public void OnRotation(RotationGestureDetector rotationDetector) {
         angle = rotationDetector.getAngle();
       // editorView.setRotation(angle);
       // Log.d("RotationGestureDetector", "Rotation: " + Float.toString(angle));
    }


    private class MySimpleOnScaleGestureListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener{


        ImageView viewMyImage;



        public MySimpleOnScaleGestureListener(ImageView iv) {
            super();

            viewMyImage = iv;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialAngle = lastRotation;
            return true;
        }


        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scaleFactor = detector.getScaleFactor() -1;

            factor += scaleFactor;
/*

            float angle = detector.getScaleFactor() * 180f;
            lastRotation = initialAngle + angle;
            matrix.setRotate(lastRotation, viewMyImage.getWidth() / 2f, viewMyImage.getHeight() / 2f);
            viewMyImage.setImageMatrix(matrix);
*/

         //   viewMyImage.setRotation(viewMyImage.getRotation()+90f);



             viewMyImage.setRotation(aa);

            Log.d("RotationGestureDetector", "Rotation: " + Float.toString(aa+angle)+" "+ Float.toString(angle));
            aa=angle;
            if(factor>0.2&&factor<3.0){
                viewMyImage.setScaleX(factor);
                viewMyImage.setScaleY(factor);
            }
            if(factor<0.2){
                factor= (float) 0.2;
            }
            if(factor>3.0){
                factor=(float) 3.0;
            }


            return true;

        }

    }




}