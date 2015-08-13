package com.example.ServiceTest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by cl on 2015/8/13.
 */
public class MySerivce extends Service {
    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        //下载的真正耗时业务
        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //设置前台服务
        Notification notification = new Notification(R.drawable.ic_launcher,"Notification comes",System.currentTimeMillis());
        Intent notifactionIntent = new Intent(this,MyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notifactionIntent,0);
        notification.setLatestEventInfo(this,"This is title","This is content",pendingIntent);
        startForeground(1,notification);

        Log.d("MyService", "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //处理具体逻辑
            }
        }).start();
        Log.d("MyService", "onStartCommand executed");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onD executed");
    }

}
