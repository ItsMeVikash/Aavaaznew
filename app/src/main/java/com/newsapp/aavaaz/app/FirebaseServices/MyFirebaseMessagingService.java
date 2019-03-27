package com.newsapp.aavaaz.app.FirebaseServices;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.util.Log;

import com.newsapp.aavaaz.app.MainActivity;

import com.newsapp.aavaaz.app.R;
import com.newsapp.aavaaz.app.secondpage.Homeis;
import com.newsapp.aavaaz.app.secondpage.NewsAgriculture;
import com.newsapp.aavaaz.app.secondpage.NewsBusiness;
import com.newsapp.aavaaz.app.secondpage.NewsEducation;
import com.newsapp.aavaaz.app.secondpage.NewsEntertainment;
import com.newsapp.aavaaz.app.secondpage.NewsGadgets;
import com.newsapp.aavaaz.app.secondpage.NewsInternational;
import com.newsapp.aavaaz.app.secondpage.NewsLifestyle;
import com.newsapp.aavaaz.app.secondpage.NewsPolitics;
import com.newsapp.aavaaz.app.secondpage.NewsSports;

import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static String category="0",index="0";
    public static Intent a;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("MESSAGE_DATA",remoteMessage.getData().toString());

        try
        {category = remoteMessage.getData().get("category").toString();
        }catch (Exception e){e.printStackTrace();}finally {
            Toast.makeText(getApplicationContext(),category,Toast.LENGTH_SHORT).show();
        }Toast.makeText(getApplicationContext(),category,Toast.LENGTH_SHORT).show();


        try
        {index = remoteMessage.getData().get("index").toString();
        }catch (Exception e){e.printStackTrace();}finally {
            Toast.makeText(getApplicationContext(),index,Toast.LENGTH_SHORT).show();
        }Toast.makeText(getApplicationContext(),"this"+index,Toast.LENGTH_SHORT).show();


        if(!category.equals("0")){
            switch (category){
                case "Home":a=new Intent(getApplicationContext(),Homeis.class);break;
                case "Sports":a=new Intent(getApplicationContext(),NewsSports.class);break;
                case "Politics":a=new Intent(getApplicationContext(),NewsPolitics.class);break;
                case "Technology":a=new Intent(getApplicationContext(),NewsEducation.class);break;
                case "Misc":a=new Intent(getApplicationContext(),NewsGadgets.class);break;
                case "Crime":a=new Intent(getApplicationContext(),NewsInternational.class);break;
                case "Viral":a=new Intent(getApplicationContext(),NewsAgriculture.class);break;
                case "Business":a=new Intent(getApplicationContext(),NewsBusiness.class);break;
                case "Lifestyle":a=new Intent(getApplicationContext(),NewsLifestyle.class);break;
                case "Entertainment":a=new Intent(getApplicationContext(),NewsEntertainment.class);break;
                default:a=new Intent(getApplicationContext(),NewsSports.class);index="1";break;
            }
        }
        if(!index.equals("0")){        showNotification(remoteMessage.getNotification());}

        ////////////////////////////////////////////
        Log.d("","From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("", "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                //handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        showNotification2(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),a);
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        //////////////////////////////////////////////
    }

    public void getindex(String index,String category){
    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = current_user.getUid();
    DatabaseReference mi = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Last").child(category);
    mi.setValue(index).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {

            }
        }
    });
}

    public void showNotification(RemoteMessage.Notification notification) {
        Intent a;
        getindex(index,category);
        switch (category){
            case "Home":a=new Intent(getApplicationContext(),Homeis.class);break;
            case "Sports":a=new Intent(getApplicationContext(),NewsSports.class);break;
            case "Politics":a=new Intent(getApplicationContext(),NewsPolitics.class);break;
            case "Technology":a=new Intent(getApplicationContext(),NewsEducation.class);break;
            case "Misc":a=new Intent(getApplicationContext(),NewsGadgets.class);break;
            case "Crime":a=new Intent(getApplicationContext(),NewsInternational.class);break;
            case "Viral":a=new Intent(getApplicationContext(),NewsAgriculture.class);break;
            case "Business":a=new Intent(getApplicationContext(),NewsBusiness.class);break;
            case "Lifestyle":a=new Intent(getApplicationContext(),NewsLifestyle.class);break;
            case "Entertainment":a=new Intent(getApplicationContext(),NewsEntertainment.class);break;
            default:a=new Intent(getApplicationContext(),NewsSports.class);break;
        }

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,a,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"MyNotifications").
                setSmallIcon(R.mipmap.ic_launcher_foreground).
                setContentTitle(notification.getTitle()).
                setContentText(notification.getBody()).
                setAutoCancel(true).
                setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());

    }

    public void showNotification2(String title,String body,Intent a) {

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,a,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"MyNotifications").
                setSmallIcon(R.mipmap.ic_launcher_foreground).
                setContentTitle(title).
                setContentText(body).
                setAutoCancel(true).
                setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());

    }
}
