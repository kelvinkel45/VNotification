package com.vcode.vnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.vcode.vnotification.App.channel_1;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTitle, editPesan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);
        editTitle = findViewById(R.id.edit_title);
        editPesan = findViewById(R.id.edit_pesan);


    }

    public void sendOnChannel1(View v){
        String title = editTitle.getText().toString();
        String pesan = editPesan.getText().toString();


        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent =  PendingIntent.getActivity(this,0,mainIntent,0);

        Intent broadcastIntent = new Intent(this, NotifReceiver.class);
        broadcastIntent.putExtra("toastPesan", pesan);
        PendingIntent aksiIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        if (pesan.length() <=60 && pesan.length() > 0){
            Notification notification = new NotificationCompat.Builder(this, channel_1)
                    .setSmallIcon(R.drawable.tes)
                    .setContentTitle(title)
                    .setContentText(pesan)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.GREEN)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    .addAction(R.mipmap.ic_launcher,"Baca", aksiIntent)
                    .build();

            notificationManager.notify(null, 1, notification);
        }else if(pesan.length() >60){
            Notification notification = new NotificationCompat.Builder(this, channel_1)
                    .setSmallIcon(R.drawable.tes)
                    .setContentTitle(title)
                    .setContentText("Pesan Masuk")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(pesan)
                            .setBigContentTitle("Pesan Masuk"))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.GREEN)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    .addAction(R.mipmap.ic_launcher,"Baca", aksiIntent)
                    .build();

            notificationManager.notify(null, 1, notification);
        }else{
            Toast.makeText(getApplicationContext(), "Silahkan masukkan pesan", Toast.LENGTH_SHORT).show();
        }

    }

    public void hapusPesam(View view) {
        editTitle.setText("");
        editPesan.setText("");
    }
}
