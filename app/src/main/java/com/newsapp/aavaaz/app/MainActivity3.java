package com.newsapp.aavaaz.app;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.newsapp.aavaaz.app.secondpage.Homeis;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.newsapp.aavaaz.app.start.Start1;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.HashMap;
	
import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class MainActivity3 extends AppCompatActivity {
    String pass = "123456789";
    TextView textView;
    private DatabaseReference mDatabase,mi,usersref;
    private FirebaseAuth mAuth;
    Dialog dialog;
    TextView heading;
    int i = 0;
    public static String aid;
 ///////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Toast.makeText(getApplicationContext(),city,Toast.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();

        String id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID) + "@gmail.com";
        aid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

////////////////////////////////////////////////////////////////////////////////
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("MyNotifications","MyNotifications",NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("Aavaaz");
        ///////////////////////////////////////////////////////////////////////////////////
        loginUser(id,pass);

    }
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

    private void go(){

        Thread a=new Thread(){
            @Override
            public void run() {
                try{
                   sleep(100);
                }
                catch (Exception e){e.printStackTrace();}
                finally {
                    Intent a=new Intent(getApplicationContext(),Homeis.class);
                    startActivity(a);
                    CustomIntent.customType(MainActivity3.this,"fadein-to-fadeout");
                }
            }
        };
        a.start();

    }

    private void go2(){
        Thread a=new Thread(){
            @Override
            public void run() {
                try{
                    //sleep(10);
                }
                catch (Exception e){e.printStackTrace();}
                finally {
                    //Intent a=new Intent(getApplicationContext(),MainActivity2.class);
                    Intent a=new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(a);
                    CustomIntent.customType(MainActivity3.this,"fadein-to-fadeout");
                }
            }
        };
        a.start();

    }

    private void loginUser(final String id, String password) {

        mAuth.signInWithEmailAndPassword(id, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
                     go();
           }
           else{go2();
           }
            }
        }); }}