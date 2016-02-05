package com.wordpress.zubeentolani;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class PlayMyMusic_Service extends Service {


    private final String BROADCAST_areYouAlive = "com.wordpress.zubeentolani.SERVICE_areYouAlive";
    private final String BROADCAST_iAmAlive = "com.wordpress.zubeentolani.SERVICE_iAmAlive";

    private String mSongName;
    final int mSdkVersion = Build.VERSION.SDK_INT;

    /**
     * Broadcast Receiver
     */

    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BROADCAST_areYouAlive)) {
                Log.d("PlayMyMusic_Service", "i was asked if i am alive");
                sendBroadcast(new Intent(BROADCAST_iAmAlive));

            }

        }
    };


    public PlayMyMusic_Service() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("PlayMusic_Service", "inside onCreate");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_areYouAlive);

        registerReceiver(mBroadcastReceiver, intentFilter);

        Log.d("PlayMusic_Service", "leaving onCreate");
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("PlayMyMusic_Service", "inside onStartCommand");

        mSongName = "Song name";

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                new Intent(getApplicationContext(), ActivityLevel0.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = null;

        if (mSdkVersion < Build.VERSION_CODES.HONEYCOMB) {
            Log.d("PlayMyMusic_Service", "build version less than HoneComb");
            notification = new Notification();
            notification.flags |= Notification.FLAG_ONGOING_EVENT;
            notification.setLatestEventInfo(
                    getApplicationContext(),
                    "Arjay",
                    "Playing " + mSongName,
                    pendingIntent
            );
        } else {
            Log.d("PlayMyMusic_Service", "build version greater than HoneComb");
            Notification.Builder builder = new Notification.Builder(getBaseContext());
            notification = builder
                    .setContentTitle("Playing" + mSongName)
                    .setSubText("subtext")
                    .setContentText("ContentText")
                    .setContentIntent(pendingIntent)
                    .build();
        }

        if (notification != null) {
            startForeground(1, notification);
        }

        Log.d("PlayMyMusic_Service", "inside onStartCommand");
        return START_REDELIVER_INTENT;
    }




    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }
}
