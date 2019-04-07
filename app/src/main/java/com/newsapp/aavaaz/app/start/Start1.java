package com.newsapp.aavaaz.app.start;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newsapp.aavaaz.app.R;
import com.newsapp.aavaaz.app.secondpage.NewsLifestyle;
import com.newsapp.aavaaz.app.secondpage.NewsPolitics;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import com.newsapp.aavaaz.app.Url;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import android.Manifest;

import com.newsapp.aavaaz.app.Home;


import com.newsapp.aavaaz.app.R;
import com.newsapp.aavaaz.app.thirdpage.NewsHomeisFull;
import com.newsapp.aavaaz.app.secondpage.NewsPolitics1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import maes.tech.intentanim.CustomIntent;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;
import static android.widget.Toast.LENGTH_SHORT;

import maes.tech.intentanim.CustomIntent;


public class Start1 extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private ImageView[] dots;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView next;
private GestureDetector gestureDetector;
 @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start1);
next=findViewById(R.id.next);next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             onSwipeLeft();
         }
     });
     gestureDetector = new GestureDetector(this);
ImageView image=findViewById(R.id.image);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Toast.makeText(getApplicationContext(),"Swipe Next ==>",Toast.LENGTH_SHORT).show();
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    @Override
    public boolean onFling(MotionEvent downevent, MotionEvent moveevent, float velocityX, float velocityY) {
        boolean result=false;
        float diffY=moveevent.getY() - downevent.getY();
        float diffX=moveevent.getX() - downevent.getX();
        if(Math.abs(diffX)>Math.abs(diffY)){
            //right or left swipe
            result=true;
            if(Math.abs(diffX)>SWIPE_THRESHOLD && Math.abs(velocityX)>SWIPE_VELOCITY_THRESHOLD ){
                if(diffX>0){onSwipeRight();}
                else {onSwipeLeft();}
            }
        }
        else{
            //up or down swipe
            result=true;
            if(Math.abs(diffY)>SWIPE_THRESHOLD && Math.abs(velocityY)>SWIPE_VELOCITY_THRESHOLD){
                if(diffY>0){onSwipeBottom();}
                else{onSwipeTop();}
            }
        }

        return result;
    }

    private void onSwipeRight() {
        ////Toast.makeText(getApplicationContext(),"Right swipe",//Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"Swipe Next ==>",Toast.LENGTH_SHORT).show();

   }


    private void onSwipeLeft() {
        ////Toast.makeText(getApplicationContext(),"Right swipe",//Toast.LENGTH_SHORT).show();
        Intent a=new Intent(getApplicationContext(),Start2.class);
        a.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  startActivity(a);
        CustomIntent.customType(this,"left-to-right");

    }

    private void onSwipeTop() {
        Toast.makeText(getApplicationContext(),"Swipe Next ==>",Toast.LENGTH_SHORT).show();
    }
    private void onSwipeBottom() {
        Toast.makeText(getApplicationContext(),"Swipe Next ==>",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(getApplicationContext(),"Swipe Next ==>",Toast.LENGTH_SHORT).show();

    }

}